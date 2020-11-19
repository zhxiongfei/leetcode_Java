### 利用RunLoop控制线程生命周期

### 需求

- 子线程**频繁**的做任务，此时如果每次做任务时创建子线程，任务完成销毁子线程，频繁得创建销毁线程对系统的开销非常大
- 所以创建一条可以长期不会销毁的子线程，在需要做任务时开启，在不需要做任务时休眠来优化性能



### 尝试一

- 新创建一个 **NSThread** 的子类，**ZXFThread**,以便于监控线程的**销毁 dealloc**

- 在控制器 viewDidLoad 中创建子线程, 并调用 run 方法

- 在 run 方法中, 给当前 RunLoop 添加一个 Observer, 并开启当前 RunLoop

- 在  touchesBegan: withEvent: 方法中在子线程调用 test 方法

  ```objc
  - (void)viewDidLoad {
      [super viewDidLoad];
      // 创建子线程，并调用 run 方法，开启当前线程的 RunLoop
      ZXFThread *thread = [[ZXFThread alloc] initWithTarget:self selector:@selector(run) object:nil];
      [thread start];
      self.thread = thread;
  }
  
  // 线程保活
  - (void)run{
      NSLog(@"%s %@", __func__, [NSThread currentThread]);
      [[NSRunLoop currentRunLoop] addPort:[NSPort new] forMode:NSRunLoopCommonModes];
      [[NSRunLoop currentRunLoop] run];
  }
  
  // 点击方法中在 子线程执行 test 任务
  - (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{
      [self performSelector:@selector(test) onThread:self.thread withObject:nil waitUntilDone:false];
  }
  
  // 子线程执行任务
  - (void)test{
      NSLog(@"%s %@", __func__, [NSThread currentThread]);
  }
  ```

- 这样看起来我们大致实现了**不销毁，可重复做任务**的子线程



### 问题一? 

- 经测试发现，虽然子线程可以正常执行任务，且不销毁，但是控制器 和 子线程 dealloc方法都没有调用
- 带来了内存泄漏问题
- 猜想是因为, **initWithTarget**: 方法导致 thread对象强引用了 self, 并且self 通过 strong属性强引用了 thread ，造成了循环引用.
- 所以把 **initWithTarget**: 方法替换为 **initWithBlock**
- 经尝试，**控制器**正常释放了，但是 **thread**仍然没有释放
- 因为子线程的 runloop 开启，所以代码一直卡在 [[NSRunLoop currentRunLoop] run] 的位置
- 所以要想**thread**释放，就需要停止 thread 的 runLoop

尝试改进？

- 在控制器的  dealloc 方法中 调用 在thread 线程 stop，停止 thread runLoop？

  ```objc
  - (void)stop{
      CFRunLoopStop(CFRunLoopGetCurrent());
      NSLog(@"%s %@", __func__, [NSThread currentThread]);
  }
  ```

  - 测试现象 : stop成功调用，但是 thread 仍然没有释放。

- 可能调用时机太晚？ 于是在控制器添加 button，点击时在子线程调用 stop?

  - 测试现象 : thread 仍然没有释放。



### dealloc 中调用 stop 

#### 为什么出现坏内存访问？

- 子线程的stop 和 主线程的 dealloc 并行执行
- 可能主线程的 dealloc 已经执行完毕，也就是控制器已经完全释放
- 而子线程仍然在访问控制器的属性
- 就会出现坏内存访问

#### 解决坏内存访问?

- 让子线程 和 主线程，同步执行
- 并且判断 weakSelf 存在.



### 问题二?

- 问题就出在  **[[NSRunLoop currentRunLoop] run]** 方法上，以下是官方文档对此方法的解释

  > If no input sources or timers are attached to the run loop, this method exits immediately; otherwise, it runs the receiver in the `NSDefaultRunLoopMode` by repeatedly invoking [runMode:beforeDate:](apple-reference-documentation://hcGlc34FMW). In other words, this method effectively begins an infinite loop that processes data from the run loop’s input sources and timers.

  - 意思为：如果没有向当前 runLoop中添加 sources,timers 等，runloop会立即退出循环
  - 否则，会在 NSDefaultRunLoopMode模式下，重复调用 runMode:beforeDate:
  - 换句话说，这个方法开启了一个无限的循环在 sources，timers等输入源中处理数据.

- 可以看出，如果调用 run, 我们就无法停止 当前 RunLoop了

- 而调用停止，也只是停止那一次 RunLoop循环

- 专门用于开启一个永不销毁的线程



### 解决

- 以runMode:beforeDate: 代替 [[NSRunLoop currentRunLoop] run] 启动 runLoop.
- 并在 控制器中增加 **isStop** 字段，用来判断是否开启 runLoop.
- 点击按钮时，调用 stop 方法，把 isStop 置为 YES，并且停止当次 runLoop.
- 测试后，发现 控制器 和 thread都释放了.
- 但是必须点击 stop 按钮，不点击就没有停止 runLoop，thread不释放.
- 继续优化为在控制器的 dealloc 方法中停止子线程 RunLoop, 但是要注意坏内存访问，以及weak被清空的问题.



### 线程封装

为了方便的使用可以控制生命周期的子线程，自定了一个 ZXFPermenantThread

```objc
ZXFPermenantThread.h

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef void(^ZXFPermenantThreadTask)(void);
@interface ZXFPermenantThread : NSObject

/**
 在当前子线程执行一个任务
 */
- (void)executeTask:(ZXFPermenantThreadTask)task;

/**
 结束线程
 */
- (void)stop;

@end

NS_ASSUME_NONNULL_END
  
  #import "ZXFPermenantThread.h"

@interface ZXFThread : NSThread
@end

@implementation ZXFThread

- (void)dealloc{
    NSLog(@"%s",__func__);
}

@end

@interface ZXFPermenantThread ()

@property (nonatomic, strong) ZXFThread *innerThread;
@property (nonatomic, assign, getter=isStopped) BOOL stopped;

@end

@implementation ZXFPermenantThread

- (instancetype)init{
    self = [super init];
    if (self) {
        __weak typeof(self) weakSelf = self;
        self.innerThread = [[ZXFThread alloc] initWithBlock:^{
            [[NSRunLoop currentRunLoop] addPort:[NSPort new] forMode:NSDefaultRunLoopMode];
            while (weakSelf && !weakSelf.isStopped) {
                [[NSRunLoop currentRunLoop] runMode:NSDefaultRunLoopMode beforeDate:[NSDate distantFuture]];
            }
        }];;
        [self.innerThread start];
    }
    return self;
}

- (void)executeTask:(ZXFPermenantThreadTask)task{
    if (!self.innerThread || !task) return;
    
    [self performSelector:@selector(__executeTask:) onThread:self.innerThread withObject:task waitUntilDone:NO];
}

- (void)stop{
    if (!self.innerThread) return;
    [self performSelector:@selector(__stop) onThread:self.innerThread withObject:nil waitUntilDone:YES];
}

- (void)dealloc{
    NSLog(@"%s",__func__);
    [self stop];
}

// MARK: private methods
- (void)__stop{
    self.stopped = YES;
    CFRunLoopStop(CFRunLoopGetCurrent());
    self.innerThread = nil;
}

- (void)__executeTask:(ZXFPermenantThreadTask)task{
    task();
}

@end
```



外部就可以很方便的使用，如下

```objc
- (void)viewDidLoad{
    self.view.backgroundColor = UIColor.lightGrayColor;
    
    ZXFPermenantThread *thread = [[ZXFPermenantThread alloc] init];
    self.thread = thread;
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{
    [self.thread executeTask:^{
        NSLog(@"%@", [NSThread currentThread]);
    }];
}

- (void)stopThread{
    [self.thread stop];
}
```

