---
title: "模拟OjbC中抽象类的使用"
date: 2020-07-04T17:57:39+08:00
draft: true
tags: ["ios"]
category: "iOS"
---

# 抽象类

什么是抽象类?

> - 在面向对象的概念中，所有的对象都是通过类来描绘的，但是反过来，并不是所有的类都是用来描绘对象的，如果一个类中没有包含足够的信息来描绘一个具体的对象，这样的类就是抽象类。
> - 抽象类除了**不能实例化对象**之外，类的其它功能依然存在，成员变量、成员方法和构造方法的访问方式和普通类一样。
> - 由于抽象类不能实例化对象，所以**抽象类必须被继承，才能被使用**。也是因为这个原因，通常在设计阶段决定要不要设计抽象类。
> - 父类包含了子类集合的常见的方法，但是由于父类本身是抽象的，所以不能使用这些方法。
>
> ------来自菜鸟教程



## Java和C++的抽象类

###  Java抽象类

- 在Java中抽象类表示的是一种继承关系，**一个类只能继承一个抽象类**，而一个类却可以实现多个接口.
- Java中的抽象类是用 **abstract class**关键词来实现的.
  - 例如我们在[简单了解三种工厂模式](https://zhangxiongfeiv.github.io)中的 **AbstractFactory**抽象工厂
  - 此工厂不能被直接实例化
    - 实例化的话，必须实现**抽象方法**
    - 或者子类继承**抽象类**，子类中实现**抽象方法**.再使用子类

### C++抽象类

- 如果类中至少有一个函数被声明为纯虚函数，则这个类就是抽象类。
- 纯虚函数是通过在声明中使用 "= 0" 来指定的
- 此类不可以被实例化
- 抽象类也可以包含非纯虚函数，成员变量
- 如果父类是抽象类，子类没有完全重写纯虚函数， 那么子类也是抽象类

```java
class Box{
   public:
      // 纯虚函数
      virtual double getVolume() = 0;
   private:
      double length;      // 长度
      double breadth;     // 宽度
      double height;      // 高度
};
```



## ObjC模拟抽象类

在ObjC中是**没有抽象类**的概念的, 但是我们可以通过**协议Protocol**来间接实现**抽象类**.

- 创建一个抽象类 **AbstractAnimal**

- 创建一个协议 **AnimalProtocol** 协议中有需要实现的方法 run(), eat().

  - **AbstractAnimal** 遵守 **AnimalProtocol**协议
  - 而由于每个动物**跑步 和 吃饭**是不一样的
  - 所以让 **AbstractAnimal** 实现协议方法，明显不合适

- 所以我们再创建继承 **AbstractAnimal** 的子类，比如**Dog**

  - **Dog**中实现具体的 **Dog** 自己的 **run() , eat()** 方法

- 因为 **AbstractAnimal** 不能实例化，所以在**初始化方法**中，如果判断初始化的类不是抽象类的子类，直接抛出异常

  ```objc
      NSAssert(![self isMemberOfClass:[AbstractAnimal class]], @"AbstractDownloader is an abstract class, you should not instantiate it directly.");
  ```

- 在调用 **AbstractAnimal** 中的 **run(), eat()** 方法时，直接抛出必须覆写方法的异常

  ```objective-c
  //  在.m文件中最好做如下处理
  #define MethodNotImplemented() \
      @throw \
      [NSException exceptionWithName:NSInternalInconsistencyException \
                              reason:[NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] \
                            userInfo:nil]
  
  - (void)run{
      MethodNotImplemented();
  }
  
  - (void)eat{
      MethodNotImplemented();
  }
  ```

  

### 代码如下 : 

AnimalProtocol协议:

```objective-c
@protocol AnimalProtocol <NSObject>

- (void)run;
- (void)eat;

@end
```

AbstractAnimal实现文件:

```java
@interface AbstractAnimal : NSObject<AnimalProtocol>

@end

#import "AbstractAnimal.h"

//  在.m文件中最好做如下处理
#define MethodNotImplemented() \
    @throw \
    [NSException exceptionWithName:NSInternalInconsistencyException \
                            reason:[NSString stringWithFormat:@"You must override %@ in a subclass", NSStringFromSelector(_cmd)] \
                          userInfo:nil]

@implementation AbstractAnimal

- (instancetype)init{
    NSAssert(![self isMemberOfClass:[AbstractAnimal class]], @"AbstractDownloader is an abstract class, you should not instantiate it directly.");
    return [super init];
}

- (void)run{
    MethodNotImplemented();
}

- (void)eat{
    MethodNotImplemented();
}

@end
```



Dog类 继承于 

```objective-c
@interface Dog : AbstractAnimal

@end

@implementation Dog

- (void)run{
    NSLog(@"%s",__func__);
}

- (void)eat{
    NSLog(@"%s",__func__);
}

@end
```



### 总结： 

- 以上，我们就完成了 **ObjC模拟抽象类** 的实现.
- 当有多种动物，比如**Cat**时
  - 我们需要创建**Cat**,使 **Cat**继承于 **AbstractAnimal**
  - 并实现 run() 和 eat() 方法
  - 完成以上两个步骤，我们就完成了, 新类的定制



## ObjC中模拟抽象类的使用

#### Masonry

**Masonry**中的**MASConstraint**就是一个抽象类。

拿出一段代码举例 : 

```objective-c
#pragma mark - Abstract
- (MASConstraint * (^)(CGFloat multiplier))multipliedBy { MASMethodNotImplemented(); }
```

multipliedBy方法的具体实现却在 

**MASViewConstraint** 类中, 查看 继承关系

```objective-c
- (MASConstraint * (^)(CGFloat))multipliedBy {
    return ^id(CGFloat multiplier) {
        NSAssert(!self.hasBeenInstalled,
                 @"Cannot modify constraint multiplier after it has been installed");
        
        self.layoutMultiplier = multiplier;
        return self;
    };
}
```

查看 **MASViewConstraint**  继承关系, 果然是继承于 **MASConstraint**的

```
/**
 *  A single constraint.
 *  Contains the attributes neccessary for creating a NSLayoutConstraint and adding it to the appropriate view
 */
@interface MASViewConstraint : MASConstraint <NSCopying>
```



参考文献:

[用Objective-C实现抽象类](http://www.veryitman.com/2019/04/21/用Objective-C实现抽象类/)

[Objective-C中的抽象类](https://www.jianshu.com/p/fa3705fac11f)

[Java 抽象类](https://www.runoob.com/java/java-abstraction.html)