## iOS监测方法执行耗时的简单实现



### 插件化

因为最近在做一个性能检测的工具, **FPSDropHunter**, 主要用来检测**App帧率FPS**, **卡顿检测**, **内存**, **CPU**等等,  正好最近也在做 Fabrique 的 **冷启动优化**, 寻思着在**FPSDropHunter** 中添加一个插件,  **监测启动速度**;

Xcode在启动过程中可以大致监测冷启动各个阶段的耗时, 所以此插件想要做的是**监控方法耗时**. 

首先,增加一个插件,**FDHTimeMonitorPlugin**, 需要遵守 **FDHPluginProtocol**协议, 并且实现协议方法 : 

```objc
- (void)runWithParameters:(NSDictionary *)paramas
```

在协议方法中, 启动监控. 



###  监控 : 

#### FDHTimeMonitorModel

我们需要一个模型来存储方法信息,  包括 方法名称, 方法地址, 调用时长

```objc
@interface FDHTimeMonitorModel : NSObject

@property (nonatomic, copy) NSString *funcName; // 方法名
@property (nonatomic, copy) NSString *funcAddr; // 方法地址
@property (nonatomic, assign) CGFloat duration; // 调用时长

@end
```



#### FDHTimeMonitorMgr

监控的主要逻辑在  FDHTimeMonitorMgr 中. 类的设计 : 

- 首先次类的实例只应该存在一个实例, 所以设计为单例模式

- 以下实例方法为必须项

  - 开启监控
  - 停止监控
  - 获取调用栈耗时
  - 打印调用栈耗时

-  **API 设计**如下 : 

  ```objc
  /// 单例
  + (instancetype)shareInstance;
  
  /// 开始监控
  - (void)startMonitorTimeWithWhiteList:(NSArray *)whiteList;
  
  /// 停止监控
  - (void)stopMonitorTime;
  
  // 获取方法耗时
  - (NSString *)getAllCallStack;
  
  /// 打印方法耗时
  - (void)logAllCallStack;
  ```

- 需要注意的是, 因为次类设计为单例, 所以禁止外部调用 alloc init 初始化

- 所以 init 方法设置为不可用, 保证类实例在应用程序生命周期仅仅存在一个.

  ```objc
  - (instancetype)init NS_UNAVAILABLE;
  ```

  

### 获取堆栈信息

使用了获取堆栈信息的三方代码  [BSBacktraceLogger](https://github.com/bestswifter/BSBacktraceLogger)

[获取任意线程调用栈的那些事(转)]()



### 核心原理 : 

1. 通过定时器, 每个 0.01s, 获取一次主线程的函数堆栈, 将函数名称, 函数地址, 函数耗时模型化为  **FDHTimeMonitorModel**, 保存在 **backTraceDict** 中, 其中key 为 **函数地址**, value 为 **TimeModel**
2. 定时执行的回调后, 每次都判断函数地址是否存在, 如果已经存在此函数地址, 就将对应的timeModel 的耗时增加 0.01s; 如果不存在该函数地址, 就初始化一个 **TimeModel**, 并将时间设置为 0.01s.
3. 打印**backTraceDict**, 即可查看主线程中每个方法的耗时.

s

### 应用: