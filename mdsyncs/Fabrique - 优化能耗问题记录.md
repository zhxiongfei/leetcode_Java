## Fabrique - 优化问题记录



### 1, 更优的数据结构和算法

- 列表去重复, 做feed流,又一个无法避免的问题, 就是列表去重
  - 比如用户在拉去第二页数据时,这时又有一条新信息插入, 这时用户拿到的第二页数据的第一条,就与第一页数据的最后一条重复了.
  - 客户端肯定希望服务端做到去重, 但是与服务端沟通无果,只能客户端去重复
  - 客户端的做法 : 记录一个 内容ID 的数组, 拿到新的数据后,挨个判断是否在 ID数组中, 如果包含在 ID数组中, 则证明重复了,丢弃此条数据, 否则将 此条内容的id加入 ID数组
    - 又一个明显的问题,当数据量大时,客户端就废了
      - 比如,一页20条数据,用户拉取了 500页数据, 此时 ID 数组中就存放着 10000条数据
      - 当我们再次拉下一页的20条时, 就需要进行 20 * 10000次的运算才能做到去重操作
      - 客户端的耗时是非常大的, 如何优化呢?
  - 优化一?
    - 使用 Set 代替 动态数组
    - 如上边的例子, 当我们判断 第 501页的数据是否重复时,只要 20(pageSize) 次的计算就课知道数据是否重复了.
    - 时间复杂度上提高了很多
  - 但是还有问题
    - O(N) 空间复杂度的 Set, 本身占据内存空间就特别大, 我们知道 Set 底层就是数组, 通过 **哈希运算** 出下标, 当哈希碰撞时还会转换为链表 和 红黑树. 其本身比数组占据的空间就大(因为元素不连续)
      - 这也是一种 **以空间换时间的思想** 体现
    - 所以当 用户拉取列表页数多时,非常消耗设备内存.
  - 优化二?
    - **滚动数组**.
    - 我们允许一定的误差, 比如第1页的数据 和 第 4 页的数据重复.
    - 数组中固定保存 **40条(两页)** 的数据,当拉取第三页时, 第三页的ID存入**滚动数组**后,我们把第一页的数据删除.
      - 再拉取第四页时
      - 第四页的数据与第二三页的数据 的 ID比较去除重复.
      - 仍然把 第四页的ID放入 滚动数组,移除第二页的 ID
    - 这样我们不管用户拉取多少页, **滚动数组中元素数量恒 <= 40**,  而每次判断时计算的次数恒 <= 800(40 * 20)
  - Fabrique采取的优化二的方案
    - 我们允许一定误差的存在, 比如,跨度好几页的数据如果出现重复我们可以接受
    - 我们不希望,出现用户滑动列表因为去重出现 **卡顿(算法耗时)**, 或者 **大量占用内存**的情况
    - 所以我们使用 优化二种 **滚动数组** 的方法,避免绝大多数情况的数据重复, 也不会引起**卡顿, 内存消耗大** 等问题.
    - 这也是我们在两者之间找到的一种平衡.
  - 总结 : 
    - Feed流重复问题最好由服务端解决, 因为我们服务端没有方案,只好客户端去处理.
    - 处理时, 要在所用的数据结构和算法, 以及用户体验之间找到一个平衡点.

### 2, 从服务端获取数据,尽量减少需要在客户端进行的处理.

- 物流列表,一个节点可能对应好几个地点 (比如运输中: 北京 - 天津 - 河北 等等), 原本服务端返回的N条数据中都有 **节点信息**, 客户端需要 使用 **双指针算法**,把返回的数据的**节点信息**进行 **O(N)** 次的计算**清空**,再进行渲染

  

  伪代码 

```objc
        NSArray *array = 服务端返回的数组;
        int i = 0, j = 1;
        while (j < array.count) {
            物流Model *m1 = array[i];
            物流Model *m2 = array[j];
            if (![m1.status isEqualToString:m2.status]) {
                i = j ++;
                continue;
            }
            m2.status = @"";
            j ++;
        }
        detailModel.details = array;
```

风险:

- 虽然复杂度为 O(N) 但是在物流节点非常多(极限情况),  也是很耗时, 消耗客户端性能.
- 且iOS端, 安卓端, h5端 都需要写一遍此逻辑, 工作内耗严重

解决:

- 经跟服务端沟通, 服务端来把重复的节点信息清空, 省去了各端上重复的工作, 提高端上性能.



### 3,设计模式-工厂模式的使用

#### 需求: 

- Fabrique项目中, 有很多的**信息流**, 比如 我的Fabs, 商品详情晒单, 订单列表晒单, 消息中心晒单, 设计师标记晒单等等.
- 其中这些信息流中大部分UI 和 逻辑重复, 不同之处也有,比如页面 Title不一样, 请求的接口名以及接口的传参不一样,以及埋点上报的参数不一样等等



#### 初步实现 : 

- 有大部分的逻辑重叠, 很容易想到使用统一类, 逻辑不同的地方加上各种 **Switch Case** 判断即可. 
- 所以,  我写了 **FBFabsListVC** 类, 因为信息流类别众多, 造成 **FBFabsListVC** 类中有大量恶心的 **Switch Case**, 而且如果我们新增一类信息流, 又会导致 此类 代码逻辑增多, 导致代码混乱,难以维护.



#### 优化一:

- 很明显, 我们把所有逻辑,包括公共逻辑以及私有逻辑全部写在一类中, 是不明智的行为. 
  - 会导致 此类 代码逻辑复杂, 代码量巨大,难以维护.
- 所以想到, 每类信息流都创建各自的类, 且统一都继承  **FBFabsListVC**
  - 公共的逻辑,我们依然放在 **FBFabsListVC** 中,避免代码重复
  - 私有的逻辑,我们交给 **子类** 自己去实现
- 优化过后, 代码逻辑清晰了, 新增一类信息流时, 不必要去修改 **FBFabsListVC** 而造成其逻辑膨胀.
  - 只需要增加子类,单独处理特殊逻辑,并继承**FBFabsListVC**即可.



#### 优化二:

- 经过优化一, 我们的代码逻辑看起来比之前清晰了很多.
  - 但是如果想隐藏子类实现细节, 外部调用时, 根据传入的值的类型,判断生成哪种类型的子类. 
  - 调用者不需要知道子类, 只需要传入响应的参数得到对应的类即可.
- 接下来,就是这次使用的主角, **简单工厂模式**.
- 创建了 **FabsListFactory** 工厂类
  - 工厂中定义了枚举值
  - 根据枚举值生产对应的 子类对象
- 外部使用信息流时,  使用工厂,传入对应的枚举值, 获取对应的子类即可
- 用父类指针**FBFabsListVC**指向 子类对象
- 这样外部调用不需要知道具体是哪种子类, 只要使用工厂生产即可.



#### ObjC抽象类:

- 上述类别中, **FBFabsListVC** 是基类, 但是其本身不能够被直接实力化使用.
- 因为私有逻辑并没有在 **FBFabsListVC** 中实现.
- 在Java 中, 这种类称为 **抽象类**,并且有 **abstract** 关键字修饰.
- 但是在 ObjC 中并没有 **abstract** 关键字.
- 所以我们需要,模拟一下抽象类, 具体做法 [模拟ojbc中抽象类的使用][https://zhxiongfei.github.io/post/模拟ojbc中抽象类的使用/]





### 4, 小Tips

- 接口字段不管无论多么简单, 永远不要直接在最晚层中直接返回.

  ```json
  {
  	"code": 2000,
  	"data": 1,
  	"msg": "OK"
  }
  ```

- 开发中碰到一个小问题,  这是我们一个刷新购物车数量的接口, 当时后端定义如上. 

- 因为返回简单, 所以直接将数量值放在了最外层的 **data** 字段.

- 当时没想到, 这种做法极其不易扩展, 相当于写死了,在发出去的app版本中, 这个接口只能解析一个 data 整型字段. 

- 这时有一个需求, 想要 **你将拥有 %d 件单品** 字符串可以由服务端灵活配置.

- 本想着, 把刷新数量接口增加一个 字段, 用来返回拼接好的字符串.

- 但是, 上述做法老版本App无法解析.

- 所以只能**新增接口** 来满足需求.

- ⚠️⚠️ 教训是 : 不管多么简单的数据,都不在最外层返回, 在data 中再包装一层, 封到另外的字段中. 

- 这样即使新增字段, 也不影响老版本app.





### 5, 运行时监控卡顿, FPS等开发

- **卡顿** 等现象,  总是难以避免, 虽然 **Instruments** 工具足够优秀, 可以**检测并定位** 卡顿.
- 同样也有**限制**,只能获取到我们连接到电脑并运行的, 对于普通用户手机上发生的 **偶现的卡顿**, 我们仍然一无所知.
- 所以, 我们希望开发一个监控卡顿的工具. 核心功能为 : 
  - **监控卡顿**
  - **主线程堆栈**
  - **符号解析**
  - **发送到服务端** 
- 使用到的技术以及难点 : 
  - 监控卡顿:  我们主要通过**监控主线程 RunLoop** 来实现
  - 获取主线程堆栈信息 以及符号解析 : 需要用到非常底层的 API, 主要参考了 **[BSBacktraceLogger](https://github.com/bestswifter/BSBacktraceLogger)**
  - 发送服务端 : 通过发送到服务端堆栈信息,来分析卡顿发生位置, 继而优化
- 开发的详细过程,以及碰到的难点信息,写在这里以下两篇文中
  -  **[Fabrique优化 RunLoop监控卡顿]()**
  - **[Fabrique优化-获取主线程调用栈]()**



### 6, 基于 response chain 的交互方式

一般在做对象之间的交互模式, 有几种方法 delegate, KVO, block, protocol, notification 等等, 在做 Fabrique 过程中, 我们了解到一种全新的交互方式 : 基于 **response chain** 的交互.

其代码非常简单, 为 **UIResponder** 写一个分类, 使得**事件 和 参数**可以通过**响应者链条**一直传递.

代码如下 :

UIResponder+Router.h

```objc
@interface UIResponder (Router)

- (void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo;

@end
```

UIResponder+Router.m

```objc
- (void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo
{
    [[self nextResponder] routerEventWithName:eventName userInfo:userInfo];
}
```

触发事件时 : 

```objc
[self routerEventWithName:@"touchAction" userInfo:@{@"row":rowStr,@"section":sectionStr}];
```

处理事件时 :

```objc
- (void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo{
    if ([eventName isEqualToString:@"touchAction"]) {
       // 对事件作处理
    }else{
       // 将事件向下传递
       [self.nextResponder routerEventWithName:eventName userInfo:userInfo];
    }
}
```



#### 使用 Strategy策略模式进行优化

- 在上述事件处理中, 如果 eventName 有多个,  就无法避免使用很多 if-else 语句来判断对应事件做出处理. 
- 所以可以使用,策略模式来解决这个问题.
  - 使用字典保存 {eventName : invocation} 
  - 在 routerWithEventWithName: userInfo: 中,直接取到 invocation
  - 设置好参数后, 直接调用 invoke 方法
- 可避免大量 if-else语句.

```objc
- (NSDictionary *)eventStrategy{
    if (!_eventStrategy) {
        _eventStrategy = @{@"designerDetail":[self getInvocationFromSel:@selector(gotoDeaignerDetail:)],
                           @"campaign":[self getInvocationFromSel:@selector(gotoCampaign:)],
                           @"goodsDetail":[self getInvocationFromSel:@selector(gotoGoodsDetail:)]
        };
    }
    return _eventStrategy;
}

- (NSInvocation *)getInvocationFromSel:(SEL)selector{
    // 1. 根据方法创建签名对象sig
    NSMethodSignature *sig = [[self class] instanceMethodSignatureForSelector:selector];
    // 2. 根据签名对象创建调用对象invocation
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    invocation.target = self;
    invocation.selector = selector;
    return invocation;
}

//子页面要进行的页面跳转 集中在这里处理
-(void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo{
    NSInvocation *invocation = self.eventStrategy[eventName];
    [invocation setArgument:&userInfo atIndex:2];
    [invocation invoke];
}
```



#### 使用 Decorator 装饰器模式

在事件层向上传递的时候, 每一层都可以在 userInfo 这个字典中, 添加数据. 到了最终处理事件的时候, 就能收集到格层综合得到的数据.完成最终的事件处理.



#### 单独处理事件对象,减轻 controller 负担

```objc
- (void)routerEventWithName:(NSString *)eventName userInfo:(NSDictionary *)userInfo
{
    [self.eventProxy handleEvent:eventName userInfo:userInfo];
}
```



#### 优缺点分析 : 

- 缺点
  - 只能对存在于 Responder Chain 上的 UIResponder 对象起作用
- 优点
  - 在复杂的 UI层级的页面上, 避免无谓的 deleggate 声明
  - 众多自定义事件的处理逻辑的到归拢, 方便下断点调试所有的事件处理
  - 在使用 **策略模式**后, 事件响应逻辑得到很好的管理, 响应逻辑不会分散
  - 持用 **装饰器模式** 后, 能够有序的收集,归拢数据.

