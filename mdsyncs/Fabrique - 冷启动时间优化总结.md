## Fabrique - 冷启动时间优化总结



### App 启动时都做了什么?

一般情况下, App的启动分为冷启动和热启动.

- **冷启动**是指, App点击启动前, 它的进程不在系统里, 需要系统新创建一个进程分配给它启动的情况. 这是一次完整的启动过程.
- **热启动**是指, App在冷启动后用户将App退到后台,在App的进程还在系统里的情况下, 用户重新启动进入App的过程, 这个过程做的事情非常少.

所以,这篇文章, 主要展开App冷启动的优化.

用户能感知到启动慢, 其实都发生在主线程上. 而主线程慢的原因有很多, 比如在主线程上执行了大文件读写操作, 在渲染周期中执行了大量计算等.

如何把启动时的所有耗时都找出来呢? 解决这个问题, 首先要弄弄清楚App在启动时都做了什么事.

#### 冷启动的三个阶段

1. main() 函数执行前
2. main() 函数执行后 (从 main 函数执行, 到设置 self.window.rootViewController)
3. 首屏渲染完成后 (从 self.window.rootViewController 到 didFinishLaunchingWithOptions方法作用域结束)

##### main() 函数执行前

在main() 函数执行前, 系统主要会做下面几件事:

1. 加载可执行文件 (App 的 .o 文件的集合)
2. 加载动态链接库, 进行 rebase指针调整和bind符号绑定
3. ObjC运行时的初始化处理, 包括ObjC相关类的注册, category注册, selector唯一性检查等
4. 初始化,包括了执行 +load() 方法, attribute(constructor) 修饰的函数的调用, 创建C++静态全局变量.

相应的, 这个阶段对于启动速度优化来说, 可以做的事情包括:

1. **减少动态库加载**. 每个库本身都有依赖关系, 苹果公司建议使用更少的动态库, 并且建议在使用动态库的数量较多时, 尽量将多个动态库进行合并. 数量上, 苹果公司建议最多使用 6 个非系统动态库.
2. **减少**加载启动后不去使用的**类** 或者 方法.
3. **+load()** 方法里的内容可以放在首屏渲染完成后再执行. 或者使用 +initialize() 方法替换掉. 因为在一个 +load() 方法里, 进行运行时方法替换操作回带来4ms的消耗. 
4. 控制 C++ 全局变量的数量.



#### 注解

- App启动后, 首次先加载**可执行文件**, 再加载 **dyld**, 然后加载所有**依赖库**, 然后调用所有 **+load()** 方法, 然后调用 **main()**, 调用 **UIApplicationMain()** , 然后调用 AppDelegate 的 代理 **didFinishLaunchWithOptions**.
- 可执行文件是指**Mach-O**文件, 也就是App中所有 .o的集合体, 从这里可以获取 **dyld** 的路径,加载 dyld.
- **dyld** 是指苹果的动态链接器, 加载dyld后, 就会去初始化运行环境, 开启缓存策略, 加载依赖库, 并且会调用每个依赖库的初始化方法, 包括 **Runtime** 也是在这里初始化的. 当所有的依赖库都被初始化完成后, Runtime会对项目中所有的类初始化, 调用 **+load()** 方法, 最后 dyld 会返回 main 函数地址, 然后 main函数会被调用.
- **动态库**是指可以共享的代码文件, 资源文件, 头文件等的打包集合体. 在 Xcode -> Targets -> General -> Link Binary With Libraries可以检查自己的库.



- main() 函数调用前

![1674389-764f8bc8a4f7889d](https://tva1.sinaimg.cn/large/0081Kckwly1glbz6godbej30ff0fkwgc.jpg)

​				

- main() 函数调用后

![1674389-7711222b5e39d7ac](https://tva1.sinaimg.cn/large/0081Kckwly1glbz8mjgzoj30mc0ckdl3.jpg)



##### main()函数执行后

main()函数执行后的阶段, 指的是从 main() 函数执行开始, 到 appDelegate 的 didFinishlaunchingWithOptions 方法里首屏渲染相关方法执行完成.

首页的业务代码都要在这个阶段, 也就是首屏渲染前执行的. 主要包括了

1. 首屏初始化配置文件的读写操作
2. 首屏列表数据的读取
3. 首屏渲染的大量操作

这一步的优化是 : 梳理出哪些是首屏渲染必要的初始化功能, 哪些是app启动必要的初始化功能, 将不必要放在启动时必要的功能放在其他合适的阶段执行.



##### 首屏渲染完成后

首屏渲染后的这个阶段, 指的是从设置了 self.window.rootViewController开始 到didFinishLaunchWithOptions 方法作用域结束.

首屏渲染完成后, 用户就可以看到App的首页信息了.





#### 功能级别的启动优化

功能级别的启动优化, 就是要从 main() 函数执行后这个阶段下手.

优化的思路是 : main()函数开始执行后到首屏渲染完成前只处理首屏相关的业务, 其他非首屏的初始化, 监听注册, 配置文件读取等都放到首屏渲染完成后去做.



#### 方法级别的启动优化

经过功能级别的启动优化, 也就是将非首屏业务所需的功能滞后以后, 从用户点击App到看到首屏的时间将会有很大程度的缩短, 也就达到了优化 App启动速度的目的. 

在这之后, 我们需要进一步做的, 是检查首屏渲染完成前主线程上有哪些耗时方法, 将没必要的耗时方法滞后或者异步执行.通常情况下, 耗时较长的方法主要发生在计算大量数据的情况下, 具体的表现就是 加载, 编辑, 存储图片 和 文件等资源.

所以, 下一步要做的是 : **优化对资源的操作.** 

我们还需要其他的方法:

就像 +load() 方法, 一个耗时 4ms, 100个就是 400ms, 这种用户也是能明显感知的. 

类似这个单个方法耗时不多, 但是由于堆积导致App启动速度变慢的方法数不胜数.所以, 需要一个**能够对启动方法耗时进行全面,精确检查的手段.**



#### 查看耗时

- 查看 Main() 调用前后花费的总时间

  在Product->Scheme->Edit Scheme->Run->Arguments->Environment Variables->`DYLD_PRINT_STATISTICS`设置为YES，就可以在控制台中查看main函数执行前总共花费的多长时间

![截屏2020-12-04 下午6.05.37](https://tva1.sinaimg.cn/large/0081Kckwly1glbzeilj8tj30jr08gwey.jpg)

- 控制台打印结果见下图 

  ![截屏2020-12-04 下午6.06.04](https://tva1.sinaimg.cn/large/0081Kckwly1glbzfdz848j30o306swgc.jpg)



##### 监控 App 启动耗时, 准确找出时间花在哪里,  方便逐一优化. 

- 监控方法有两种
  - 定时抓取主线程的方法调用堆栈, 计算一段时间里各个方法的耗时. Xcode工具套件里自带的 Time Prifiler 就是这种方式. 
  - 对 objc_msgSend方法进行hook来获取所有方法的执行耗时. 



##### 实现第一种监控方法 :

1. 通过定时器, 每个 0.01s, 获取一次主线程的函数堆栈, 将函数名称, 函数地址, 函数耗时模型化为  **FDHTimeMonitorModel**, 保存在 **backTraceDict** 中, 其中key 为 **函数地址**, value 为 **TimeModel**
2. 定时执行的回调后, 每次都判断函数地址是否存在, 如果已经存在此函数地址, 就将对应的timeModel 的耗时增加 0.01s; 如果不存在该函数地址, 就初始化一个 **TimeModel**, 并将时间设置为 0.01s.
3. 打印**backTraceDict**, 即可查看主线程中每个方法的耗时.

点击文章链接查看详情  [iOS监测方法执行耗时的简单实现]() 



#### 参考文章

[App 启动速度怎么做优化与监控？](https://time.geekbang.org/column/article/85331?utm_campaign=guanwang&utm_source=baidu-ad&utm_medium=ppzq-pc&utm_content=title&utm_term=baidu-ad-ppzq-title)

[iOS - 优化App冷启动速度](https://www.jianshu.com/p/f26c4f16692a)

