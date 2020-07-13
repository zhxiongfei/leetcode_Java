---
title: "装饰器模式与ObjC的Category"
date: 2020-07-13T14:17:18+08:00
draft: true
tags: ["设计模式","ios"]
category: "技术"
---

## 装饰器模式

> 装饰器模式(Decorator Pattern) 允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
>
> 这种模式创建了一个装饰类，用来包装原有的类，并在保持方法签名完整性的前提下，提供了额外的功能。
>
> 我们通过下面的实例来演示装饰器模式的用法。其中，我们将把一个形状装饰上不同的颜色，同时又不改变形状类。

## 介绍

> **意图：**动态地给一个对象添加一s些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。
>
> **主要解决：**一般的，我们为了扩展一个类经常使用继承方式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀。
>
> **何时使用：**在不想增加很多子类的情况下扩展类。
>
> **如何解决：**将具体功能职责划分，同时继承装饰者模式。
>
> **关键代码：** 1、Component 类充当抽象角色，不应该具体实现。 2、修饰类引用和继承 Component 类，具体扩展类重写父类方法。
>
> **应用实例：** 1、孙悟空有 72 变，当他变成"庙宇"后，他的根本还是一只猴子，但是他又有了庙宇的功能。 2、不论一幅画有没有画框都可以挂在墙上，但是通常都是有画框的，并且实际上是画框被挂在墙上。在挂在墙上之前，画可以被蒙上玻璃，装到框子里；这时画、玻璃和画框形成了一个物体。
>
> **优点：**装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。
>
> **缺点：**多层装饰比较复杂。
>
> **使用场景：** 1、扩展一个类的功能。 2、动态增加功能，动态撤销。
>
> **注意事项：**可代替继承。

## 实现

> 我们将创建一个 *Shape* 接口和实现了 *Shape* 接口的实体类。然后我们创建一个实现了 *Shape* 接口的抽象装饰类 *ShapeDecorator*，并把 *Shape* 对象作为它的实例变量。
>
> *RedShapeDecorator* 是实现了 *ShapeDecorator* 的实体类。
>
> *DecoratorPatternDemo*，我们的演示类使用 *RedShapeDecorator* 来装饰 *Shape* 对象。![<u>decorator_pattern_uml_diagram</u>](https://tva1.sinaimg.cn/large/007S8ZIlly1ggpcaqkensj30g70adq42.jpg)



- 定义 **Shape** 接口, 接口定义 **void draw();** 方法

  ```java
  public interface Shape {
      void draw();
  }
  ```

- 定义 **Circle**类，实现 draw方法，实现简单的画圆操作

  ```java
  public class Circle implements Shape {
  
      @Override
      public void draw() {
          System.out.println("Shape : Circle");
      }
  }
  ```

- 定义 **Rectangle**类，实现 draw方法，实现简单的画矩形操作

  ```java
  public class Rectangle implements Shape{
      @Override
      public void draw() {
          System.out.println("Shape: Rectangle");
      }
  }
  ```

- 这时，我们有一个需求， 给 **Rectangle** 和 **Circle** 描红色的边

- 如何实现这个需求呢  ?

- 一 : **继承** 

  - 我们可以通过**继承**来实现，RedCircle 继承于 Circle, RedCircle类中实现描边功能
  - 但是, 这时又有一个加阴影的功能呢？ 我们在搞一个 **ShaddowCircle**?
  - 既有阴影，又有红色边的呢？ 再搞一个 ShaddowRedCircle子类？
  - 很明显，通过继承完成此需求，会随着业务逻辑复杂，变得难以维护。
  - 该如何解决这个问题呢？ 答案是，装饰器.

- 二 : **装饰器** 

  - 这就到了，我们今天的设计模式， **装饰器**

  - 定义 **ShapeDecorator** 抽象类，其中聚合了一个 实现了 Shape 接口的对象

    - 其自身也实现 Shape 接口
    - 并在接口实现中，用其聚合的 对象 来调用 draw() 方法

  - 定义 **RedShapDecorator** 实体类，继承于**ShapeDecorator**

    - 覆盖父类 draw() 方法，并在 覆写的方法中，描上红色的边框

    ```java
    @Override
    public void draw() {
        decoratedShape.draw();
    
        setRedBorder(decoratedShape);
    }
    
    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color : Red");
    }
    ```

  - 在调用时, 就可以这样写 : 

    ```java
    public static void main(String[] args) {
    
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(circle);
    
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();
    
        System.out.println("\nCircle with red border");
        redCircle.draw();
    
        System.out.println("\nRectangle with red border");
        redRectangle.draw();
    }
    ```

    - 使用装饰器，我们实现了方便地对 Circle类 和 Rectangle类，增加 红色描边的功能
    - 后续如果有新的装饰，比如 加阴影，可以增加一个 **ShadowShapeDecorator** 类
      - 同 **RedShapeDecorator**一样, 继承 ShapeDecorator，覆写 **draw()** 方法, 做对应的操作即可.



## 装饰器模式在ObjC中应用

### Category

- ObjC 的 分类，可以很方便的对已有类添加方法，也可以通过 runtime属性关联的方式，添加实例变量
- 不仅可以给自己的类添加方法， 也可以给 Cocoa 类中扩展方法以及属性
- 分类相当于给 类，增加了装饰，起到了装饰器的作用

### Protocol

- **Protocol** 相当于 Java **Interface**

- 比如，我们有一个 Human 类，想给他增加 **dressUp** 打扮的能力

  ```objc
  #import "DressUpProtocol.h"
  
  NS_ASSUME_NONNULL_BEGIN
  
  @interface Human : NSObject<DressUpProtocol>
  
  @end
  ```

  ```objc
  
  @protocol DressUpProtocol <NSObject>
  
  @required;
  - (void)dressUp;
  
  @end
  ```

- 这时，我们就可以为 Human 创建一个 装饰器 **HumanDecorator**

  ```objc
  @interface HumanDecorator ()
  
  // 被装饰者
  @property (nonatomic, weak) id<DressUpProtocol> beDecorator;
  
  @end
  
  @implementation HumanDecorator
  
  - (instancetype)initWithBeDecorator:(id<DressUpProtocol>)beDecorator{
      if (self = [super init]) {
          self.beDecorator = beDecorator;
      }
      return self;
  }
  
  - (void)dressUp{
      
      [self beforeDressUp];
      [self.beDecorator dressUp];
      [self afterDressUp];
  }
  
  -(void)beforeDressUp{
      NSLog(@"我是设计师：穿衣前,先选化个妆");
  }
  
  -(void)afterDressUp{
      NSLog(@"我是设计师：穿衣后，再弄个发型");
  }
  
  @end
  ```

- 通过装饰器，我们就可以在 dressUp 方法做一些特殊的处理，将 Human 打扮的更漂亮~



参考文章 :

 [菜鸟教程](https://www.runoob.com/design-pattern/decorator-pattern.html)

[(四)大话设计模式 - 装饰器模式（iOS版](https://www.jianshu.com/p/57254e3df17f)

