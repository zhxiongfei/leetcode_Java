## Fabrique监控并优化卡顿

(此文章转自老谭博客, [传送门](http://www.tanhao.me/code/151113.html))

### 一 : RunLoop 监控分析

[关于RunLoop](https://zhxiongfei.github.io/post/为什么ios-app能一直响应事件-runloop/)

- 简而言之, RunLoop是一个重复接收端口信号和事件源的死循环, 它不断的唤醒沉睡, 主线程的RunLoop在应用跑起来就启动了.
- 以为CFRunloop是开源的, 我们可以看一下 CFRunLoop.c 的代码,猜想其执行流程, 以下为伪代码 : 

```c
/// 1. 通知Observers，即将进入RunLoop
/// 此处有Observer会创建AutoreleasePool: _objc_autoreleasePoolPush();
__CFRUNLOOP_IS_CALLING_OUT_TO_AN_OBSERVER_CALLBACK_FUNCTION__(kCFRunLoopEntry);

do{

/// 2. 通知 Observers: 即将触发 Timer 回调。
__CFRUNLOOP_IS_CALLING_OUT_TO_AN_OBSERVER_CALLBACK_FUNCTION__(kCFRunLoopBeforeTimers);

/// 3. 通知 Observers: 即将触发 Source (非基于port的,Source0) 回调。
__CFRUNLOOP_IS_CALLING_OUT_TO_AN_OBSERVER_CALLBACK_FUNCTION__(kCFRunLoopBeforeSources);

__CFRUNLOOP_IS_CALLING_OUT_TO_A_BLOCK__(block);

/// 4. 触发 Source0 (非基于port的) 回调。
__CFRUNLOOP_IS_CALLING_OUT_TO_A_SOURCE0_PERFORM_FUNCTION__(source0);
__CFRUNLOOP_IS_CALLING_OUT_TO_A_BLOCK__(block);

/// 6. 通知Observers，即将进入休眠
/// 此处有Observer释放并新建AutoreleasePool: _objc_autoreleasePoolPop(); _objc_autoreleasePoolPush();
__CFRUNLOOP_IS_CALLING_OUT_TO_AN_OBSERVER_CALLBACK_FUNCTION__(kCFRunLoopBeforeWaiting);

/// 7. sleep to wait msg.
mach_msg()-> mach_msg_trap();

/// 8. 通知Observers，线程被唤醒
__CFRUNLOOP_IS_CALLING_OUT_TO_AN_OBSERVER_CALLBACK_FUNCTION__(kCFRunLoopAfterWaiting);

/// 9. 如果是被Timer唤醒的，回调Timer
__CFRUNLOOP_IS_CALLING_OUT_TO_A_TIMER_CALLBACK_FUNCTION__(timer);

/// 9. 如果是被dispatch唤醒的，执行所有调用 dispatch_async 等方法放入main queue 的 block
__CFRUNLOOP_IS_SERVICING_THE_MAIN_DISPATCH_QUEUE__(dispatched_block);

/// 9. 如果如果Runloop是被 Source1 (基于port的) 的事件唤醒了，处理这个事件
__CFRUNLOOP_IS_CALLING_OUT_TO_A_SOURCE1_PERFORM_FUNCTION__(source1);

}while(...);

/// 10. 通知Observers，即将退出RunLoop
/// 此处有Observer释放AutoreleasePool: _objc_autoreleasePoolPop();
__CFRUNLOOP_IS_CALLING_OUT_TO_AN_OBSERVER_CALLBACK_FUNCTION__(kCFRunLoopExit);
```



通过源码发现, RunLoop 处理事件的时间主要在两个阶段 : 

- **kCFRunLoopBeforeTimers** 到 **kCFRunLoopBeforeWaiting** 之间.
- **kCFRunLoopAfterWaiting** 之后.



#### 监控 RunnLoop 状态检测卡顿

通过以上分析,  我们知道了主线程处理事件的时间, 那么如何检测卡顿呢? 

为主线程 RunLoop 添加贯彻着,  监听状态的变化, 代码如下 : 

```objc
static void fdhRunLoopObserverCallback(CFRunLoopObserverRef observer,CFRunLoopActivity activity, void * info){
    RUNLOOPMONITOR.currentActivity = activity;
    dispatch_semaphore_signal(RUNLOOPMONITOR.semphore);
    switch (activity) {
        case kCFRunLoopEntry:
            NSLog(@"runloop entry");
            break;
        case kCFRunLoopExit:
            NSLog(@"runloop exit");
            break;
        case kCFRunLoopAfterWaiting:
            NSLog(@"runloop after waiting");
            break;
        case kCFRunLoopBeforeTimers:
            NSLog(@"runloop before timers");
            break;
        case kCFRunLoopBeforeSources:
            NSLog(@"runloop before sources");
            break;
        case kCFRunLoopBeforeWaiting:
            NSLog(@"runloop before waiting");
            break;
        default:
            break;
    }
}
```

运行之后输出的结果是滚动引发的 Sources 事件总是被快速的执行, 然后进入 **kCFRunLoopBeforeWaiting** 状态. 

如果发生了卡顿, 那么 RunLoop 必然会保持在 **kCFRunLoopAfterWaiting** 或者 **kCFRunLoopBeforeSources** 这两个状态. 所以大致流程如下 : 

- 首先需要注册 RunLoop 的监听回调, 获取 Runloop的状态.
- 通过创建子线程循环监听主线程 RunLoop 的状态来检测是否存在卡顿现象. 
- 分别监控 RunLoop 的 **[kCFRunLoopAfterWaiting  到  kCFRunLoopBeforeSources]** 之间  以及 状态为 **kCFRunLoopBeforeWaiting** 的状态.
  - **kCFRunLoopBeforeWaiting**  监控方案
    - 设置一个标志 **timeout =  true** .
    - 在主线程将 **timeout = false**.
    - 在阈值期间, 如果**timeout == true**, 则说明发生了卡顿.
    - 获取主线程堆栈上传.
  - **kCFRunLoopAfterWaiting  到  kCFRunLoopBeforeSources 之间**
    - **dispatch_semaphore_wait** 等待 **阈值** 的时间,  如果在 **阈值*** 时间内
    - 如果信号量没有被释放, 并且 RunLoop 的状态没有切换, 还是 afterWaiting 或 beforeSources
    - 则说明可能发生了超时, 如果超时时间 >= 5, 则认为发生了卡顿
    - 获取主线程堆栈并上传.

主要代码 : 

```objc
dispatch_async(fdh_event_monitor_queue(), ^{
        while (RUNLOOPMONITOR.isMonitoring) {
            if (RUNLOOPMONITOR.currentActivity == kCFRunLoopBeforeWaiting) {
                __block BOOL timeOut = true;
                dispatch_async(dispatch_get_main_queue(), ^{
                    timeOut = false;
                    dispatch_semaphore_signal(RUNLOOPMONITOR.eventSemphore);
                });
                [NSThread sleepForTimeInterval:fdh_time_out_interval];
                if (timeOut) {
                    BSLOG_MAIN;
                }
                dispatch_wait(RUNLOOPMONITOR.eventSemphore, DISPATCH_TIME_FOREVER);
            }
        }
    });
    
    dispatch_async(fdh_fluecy_monitor_queue(), ^{
        while (RUNLOOPMONITOR.isMonitoring) {
            long waitTime = dispatch_semaphore_wait(self.semphore, dispatch_time(DISPATCH_TIME_NOW, fdh_wait_interval));
            if (waitTime != 0) {
                if (RUNLOOPMONITOR.currentActivity == kCFRunLoopBeforeSources || RUNLOOPMONITOR.currentActivity == kCFRunLoopAfterWaiting) {
                    if (++RUNLOOPMONITOR.timeOut < 5) {
                        continue;
                    }
                    // 卡顿 打印堆栈
                    BSLOG_MAIN;
                    [NSThread sleepForTimeInterval:fdh_restore_interval];
                }
            }
        }
    });
```





### 二 : 获取线程调用堆栈

主要参考 [获取任意线程调用栈的那些事](https://www.cnblogs.com/LiLihongqiang/p/7645987.html)

