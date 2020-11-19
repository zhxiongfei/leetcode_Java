## 为什么iOS App能一直响应事件？- RunLoop



### 什么是RunLoop？

- 顾名思义
  - 运行循环
  - 在程序运行过程中循环做一些事情
- 应用范畴
  - 定时器(Timer), PerformSelector
  - GCD Async Main Queue
  - 事件响应，手势识别，界面刷新
  - 网络请求
  - AutoReleasePoll



### 如果没有RunLoop?

```objc
int main(int argc, const char * argv[]) {
    @autoreleasepool {
        // insert code here...
        NSLog(@"Hello, World!");
    }
    return 0;
}
```

- 执行完 return 0后，会退出程序



### 如果有了RunLoop？

```objc
int main(int argc, char * argv[]) {
    NSString * appDelegateClassName;
    @autoreleasepool {
        // Setup code that might create autoreleased objects goes here.
        appDelegateClassName = NSStringFromClass([AppDelegate class]);
    }
    return UIApplicationMain(argc, argv, nil, NSStringFromClass([AppDelegate class]));
}
```

- 程序不会马上退出，而是保持运行状态
- RunLoop的基本作用
  - 保持程序的持续运行
  - 处理App中的各种事件(比如触摸事件，定时器事件等)
  - 节省CPU资源，提高程序性能：该做事时做事，该休息时休息

以下为猜想的RunLoop执行的伪代码: 

```objc
int main(int argc, char * argv[]) {
    @autoreleasepool {
        int retVal = 0;
        do {
            // 睡眠中等待消息
            int messagge = sleep_and_wait();
            // 处理消息
            retVal = process_message(messagge);
            
        } while (0 == retVal);
    }
    return 0;
}
```



### RunLoop对象

- iOS中有2套API来访问和使用RunLoop
- Foundation:NSRunLoop
- Core Foundation: CFRunLoopRef
- NSRunLoop 和 CFRunLoopRef 都代表着RunLoop对象
  - NSRunLoop 是基于 CFRunLoopRef的一层ObjC封装
  - CFRunLoopRef是开源的。



### RunLoop与线程

- 每条线程都有唯一的一个与之对应的RunLoop对象
- RunLoop保存在一个全局的Dictionary里，线程作为key，RunLoop作为value
- 线程刚创建时并没有RunLoop对象，RunLoop会在第一次获取它时创建
- RunLoop会在线程结束时销毁
- 主线程的RunLoop已经自动获取(创建),  子线程默认没有开启RunLoop



### 获取RunLoop对象

- Foundation

  ```objc
  [NSRunLoop currentRunLoop]; // 获取当前线程的RunLoop对象
  [NSRunLoop mainRunLoop];    // 获取主线线程的RunLoop对象
  ```

- Core Foundaation

  ```objc
  CFRunLoopGetCurrent();      // 获取当前线程的RunLoop对象
  CFRunLoopGetMain();         // 获取主线线程的RunLoop对象
  ```



### CFRunLoopModeRef

#### 定义

- CFRunLoopRef代表RunLoop的运行模式
- 一个RunLoop包含若干个Mode, 每个Mode又包含了若干个Source0/ Source1/ Timer/ Observer
- RunLoop启动时只能选择其中一个Mode，作为currentMode
- 如果需要切换Mode，只能退出当前Loop，再重新选择一个Mode进入
  - 不同组的Source0/Source1/Timer/Observer能分隔出来，互不影响
- 如果Mode里没有任何Source0/Source1/Timer/Observer, RunLoop会立马退出

#### 两种常见Mode

- KCFRunLoopDefaultMode(NSDefaultRunLoopMode):App的默认Mode，通常主线程是在这个Mode下运行
- UITrackingRunnLoopMode:界面跟踪Mode，用于ScrollView追踪滑动触摸，保证界面滑动时不受其他Mode影响



### CFRunLoopModeRef

```
/* Run Loop Observer Activities */
typedef CF_OPTIONS(CFOptionFlags, CFRunLoopActivity) {
    kCFRunLoopEntry = (1UL << 0),					// 即将进入Loop
    kCFRunLoopBeforeTimers = (1UL << 1),  // 即将处理Timer
    kCFRunLoopBeforeSources = (1UL << 2),	// 即将处理Source
    kCFRunLoopBeforeWaiting = (1UL << 5),	// 即将进入休眠
    kCFRunLoopAfterWaiting = (1UL << 6),	// 刚从休眠中唤醒
    kCFRunLoopExit = (1UL << 7),					// 即将推出Loop
    kCFRunLoopAllActivities = 0x0FFFFFFFU
};
```



### 添加Observer监听RunLoop的所有状态

```objc
// 创建Observer
    CFRunLoopObserverRef observer = CFRunLoopObserverCreateWithHandler(kCFAllocatorDefault, kCFRunLoopAllActivities, YES, 0, ^(CFRunLoopObserverRef observer, CFRunLoopActivity activity) {
      switch (activity) {
        case kCFRunLoopEntry:
            NSLog(@"kCFRunLoopEntry");
            break;
        case kCFRunLoopBeforeTimers:
            NSLog(@"kCFRunLoopBeforeTimers");
            break;
        case kCFRunLoopBeforeSources:
            NSLog(@"kCFRunLoopBeforeSources");
            break;
        case kCFRunLoopBeforeWaiting:
            NSLog(@"kCFRunLoopBeforeWaiting");
            break;
        case kCFRunLoopAfterWaiting:
            NSLog(@"kCFRunLoopAfterWaiting");
            break;
        default:
            break;
    }
    });
    // 添加Observer
    CFRunLoopAddObserver(CFRunLoopGetCurrent(), observer , kCFRunLoopCommonModes);
    // 释放observer
    CFRelease(observer);
```



### RunLoop Mode切换

- 同上述监控RunLoop状态一样，我们只监控 Entry 和 Exit

  ```objc
  switch (activity) {
              case kCFRunLoopEntry:{
                  CFRunLoopMode mode = CFRunLoopCopyCurrentMode(CFRunLoopGetCurrent());
                  NSLog(@"kCFRunLoopEntry - %@", mode);
                  CFRelease(mode);
                  break;
              }
              case kCFRunLoopExit:{
                  CFRunLoopMode mode = CFRunLoopCopyCurrentMode(CFRunLoopGetCurrent());
                  NSLog(@"kCFRunLoopExit - %@", mode);
                  CFRelease(mode);
                  break;
              }
              default:
                  break;
          }
  ```

- 我们在页面上拖入一个 UITextView 

- 观察在拖动 UITextView 前后，监控 Mode 切换

- 输出如下:

  ```objc
  // 滑动开始
  // 先退出defaultMode
  kCFRunLoopExit - kCFRunLoopDefaultMode
  // 再进入 trackingMode
  kCFRunLoopEntry - UITrackingRunLoopMode
  
  // 滑动结束
  // 先推出 trackingMode
  kCFRunLoopExit - UITrackingRunLoopMode
  // 再进入 defaultMode
  kCFRunLoopEntry - kCFRunLoopDefaultMode
  ```



### RunLoop的运行逻辑

以下图片来自苹果官网

![image-20201119113520110](https://tva1.sinaimg.cn/large/0081Kckwly1gkubtrmpu8j30zu0iqaii.jpg)



- Source0
  - 触摸事件处理
  - performSelector: onThread:
- Source1
  - 基于Port的线程间通信
  - 系统事件捕捉
- Timers
  - NSTimer
  - performSelector: withObject: afrerDelay:
- Observers
  - 用于监听RunLoop的状态
  - UI刷新(beforeWaiting)
  - Autorelease pll(Before Waiting)

![屏幕快照 2020-11-19 上午11.37.35](https://tva1.sinaimg.cn/large/0081Kckwly1gkubw98p02j31fg0pskcl.jpg)

- 01, 通知 Observers: 进入Loop
- 02, 通知 Observers: 即将处理 Timers
- 03, 通知 Observers: 即将处理 Sources
- 04, 处理Blocks
- 05, 处理Source0 (有可能会再次处理 Blocks)
- 06, 如果存在Source1，就跳转第8步
- 07, 通知 Observers: 开始休眠(等待消息唤醒)
- 08, 通知 Observers: 结束休眠(被某个消息唤醒)
  1. 处理Timer
  2. 处理 GCD Async To Main Queue
  3. 处理 Source1
- 09, 处理 Blocks
- 10, 根据前面的执行结果,决定如何操作
  1. 回到第 02步
  2. 退出 Loop
- 11, 通知Observers: 退出Loop