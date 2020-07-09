---
title: "享元模式与UITableViewCell复用机制"
date: 2020-07-08T12:50:06+08:00
draft: true
tags: ["iOS", "设计模式"]
---

# 享元模式(Flyweight)

## 概念

> 享元模式（Flyweight Pattern）主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
>
> 享元模式尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象。我们将通过创建 5 个对象来画出 20 个分布于不同位置的圆来演示这种模式。由于只有 5 种可用的颜色，所以 color 属性被用来检查现有的 *Circle* 对象。

## 介绍

> **意图：**运用共享技术有效地支持大量细粒度的对象。
>
> **主要解决：**在有大量对象时，有可能会造成内存溢出，我们把其中共同的部分抽象出来，如果有相同的业务请求，直接返回在内存中已有的对象，避免重新创建。
>
> **何时使用：** 1、系统中有大量对象。 2、这些对象消耗大量内存。 3、这些对象的状态大部分可以外部化。 4、这些对象可以按照内蕴状态分为很多组，当把外蕴对象从对象中剔除出来时，每一组对象都可以用一个对象来代替。 5、系统不依赖于这些对象身份，这些对象是不可分辨的。
>
> **如何解决：**用唯一标识码判断，如果在内存中有，则返回这个唯一标识码所标识的对象。
>
> **关键代码：**用 HashMap 存储这些对象。
>
> **应用实例：** 1、JAVA 中的 String，如果有则返回，如果没有则创建一个字符串保存在字符串缓存池里面。 2、数据库的数据池。
>
> **优点：**大大减少对象的创建，降低系统的内存，使效率提高。
>
> **缺点：**提高了系统的复杂度，需要分离出外部状态和内部状态，而且外部状态具有固有化的性质，不应该随着内部状态的变化而变化，否则会造成系统的混乱。
>
> **使用场景：** 1、系统有大量相似对象。 2、需要缓冲池的场景。
>
> **注意事项：** 1、注意划分外部状态和内部状态，否则可能会引起线程安全问题。 2、这些类必须有一个工厂对象加以控制。



## 应用

### Java String类

```java
    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "Hello";

        String s3 = new String("Hello");
        String s4 = new String("Hello");

        System.out.println(s1 == s2);						// true
        System.out.println(s1 == s3);						// false
	      System.out.println(s3 == s4);						// false
        System.out.println(s1 == s3.intern());	// true
    }
```

- == 比较的是在内存中的地址值是否一样
- 因为在 Java 中所有的字符串都放在一个常量池里边
  - 在创建 s2时，常量池里有 "Hello" 字符串， 所以直接把 池子中"Hello"的地址赋给 s2
    - 所以 s1 == s2
- s3, s4 是 new出来的， 其内存地址，在**堆上**，地址不一样
  - 所以 s1 != s3;   	s3 != s4
- 怎么解释 s1 == s3.intern()呢 ？
  - 接下来，我们看一下， intern() 函数做了什么

以下是 Java源码中对 **intern()** 的解释

```java
    /**
     * Returns a canonical representation for the string object.
     * <p>
     * A pool of strings, initially empty, is maintained privately by the
     * class {@code String}.
     * <p>
     * When the intern method is invoked, if the pool already contains a
     * string equal to this {@code String} object as determined by
     * the {@link #equals(Object)} method, then the string from the pool is
     * returned. Otherwise, this {@code String} object is added to the
     * pool and a reference to this {@code String} object is returned.
     * <p>
     * It follows that for any two strings {@code s} and {@code t},
     * {@code s.intern() == t.intern()} is {@code true}
     * if and only if {@code s.equals(t)} is {@code true}.
     * <p>
     * All literal strings and string-valued constant expressions are
     * interned. String literals are defined in section 3.10.5 of the
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @return  a string that has the same contents as this string, but is
     *          guaranteed to be from a pool of unique strings.
     * @jls 3.10.5 String Literals
     */
    public native String intern();
```

- 我们看关键的一句话 :
  -  **@return  a string that has the same contents as this string, but is guaranteed to be from a pool of unique strings.**
  - 返回与该字符串具有相同内容的字符串，但是保证来自唯一的字符串池。
- 这就解释了为什么 s1 == s3.intern() 了。 因为调用 s3调用 intern() 后，也返回了常量池中和s3内容相同的字符串
  - 所以 s1 == s3.intern();



### iOS - UITableViewCell复用机制

#### 简单的测试代码

- 在 vc中, 创建了一个 taleview，并加到 vc.view 上
- 为了方便统计cell创建个数，以及复用个数。 自定义了 MineCell
- 查看官方文档后，发现：
  - tableViewCell创建时, 会执行 [`initWithStyle:reuseIdentifier:`](apple-reference-documentation://hcFRJi2-7u) 方法
  - tableviewCell复用时,会执行 [`prepareForReuse`](apple-reference-documentation://hccjldTnGo) 方法
- 所以我们在 MineCell 中覆写以上两个方法来统计  **createCnt** 和 **reuseCnt**

```objc
- (void)viewDidLoad {
    [super viewDidLoad];
    
    UITableView *tableView = [[UITableView alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain];
    tableView.delegate = self;
    tableView.dataSource = self;
    tableView.rowHeight = 200;
    [tableView registerClass:[MineCell class] forCellReuseIdentifier:@"cell"];
    [self.view addSubview:tableView];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 10000;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    MineCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cell"];
    cell.textLabel.text = [NSString stringWithFormat:@"%ld",indexPath.row];
    return cell;
}

```



```java
@implementation MineCell

int createCnt = 0;
int reuseCnt = 0;
- (void)prepareForReuse{
    NSLog(@"复用了 %d 次",++reuseCnt);
    [super prepareForReuse];
}

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    
    NSLog(@"创建了 %d 次",++createCnt);
    return [super initWithStyle:style reuseIdentifier:reuseIdentifier];
}

@end
```



#### 执行结果:

```shell
创建了 1 次
创建了 2 次
创建了 3 次
创建了 4 次
创建了 5 次
创建了 6 次
创建了 7 次
创建了 8 次
创建了 9 次
创建了 10 次
创建了 11 次
创建了 12 次
创建了 13 次
创建了 14 次
创建了 15 次
创建了 16 次
创建了 17 次
创建了 18 次
创建了 19 次
创建了 20 次
复用了 1 次
复用了 2 次
复用了 3 次
复用了 4 次
复用了 5 次
复用了 6 次
复用了 7 次
复用了 8 次
复用了 9 次
复用了 10 次
复用了 11 次
复用了 12 次
```



#### 执行结果分析 : 

- 发现tableviewcell创建时，开始会创建 **至少20 个cell** 
  - 前提是 numberOfRows >= 20, 如果cell总共不超过20个，那肯定不会创建20个. (后续假设 cell 超过20 个)
  - 当 height 特别小, 第一屏的cell很多时，屏幕上每个cell都会创建一个, 总创建数 >= 20个
  - 当 height 很大，比如1000，第一屏只需要1个cell时，tableview加载时，也会创建 20 个
  - 所以， tableView**首次创建cell >= 20**.
- 继续滑动屏幕，没有再创建 cell, 而是调用 **prepareForReuse** 方法复用cell.



#### 疑问 ？

- 为什么首次创建**至少 20** 个cell ？ 
- cell复用时, 调用 **dequeueReusableCellWithIdentifier:** 方法，是如何实现复用的？
  - 复用的cell **从哪里取**？ 
  - **复用池**是用什么数据结构存储的？
  - **复用池**又是如何维护的?



### 官方文档对复用方法的解释 : 

> ###### Instance MethoddequeueReusableCellWithIdentifier:
>
> Returns a reusable table-view cell object located by its identifier.
>
> ###### Declaration
>
> ```
>- (__kindof UITableViewCell *)dequeueReusableCellWithIdentifier:(NSString *)identifier;
> ```
> 
> ###### Parameters
>
> - `identifier`
>
>   A string identifying the cell object to be reused. This parameter must not be `nil`.
>
> ###### Return Value
>
> A [`UITableViewCell`](apple-reference-documentation://hcl8do-H6H) object with the associated `identifier` or `nil` if no such object exists in the reusable-cell queue.
>
> ###### Discussion
>
> For performance reasons, a table view’s data source should generally reuse [`UITableViewCell`](apple-reference-documentation://hcl8do-H6H) objects when it assigns cells to rows in its [`tableView:cellForRowAtIndexPath:`](apple-reference-documentation://hcipQqZAji) method. A table view maintains a queue or list of [`UITableViewCell`](apple-reference-documentation://hcl8do-H6H) objects that the data source has marked for reuse. Call this method from your data source object when asked to provide a new cell for the table view. This method dequeues an existing cell if one is available or creates a new one using the class or nib file you previously registered. If no cell is available for reuse and you did not register a class or nib file, this method returns `nil`. 
>
> If you registered a class for the specified `identifier` and a new cell must be created, this method initializes the cell by calling its [`initWithStyle:reuseIdentifier:`](apple-reference-documentation://hcFRJi2-7u) method. For nib-based cells, this method loads the cell object from the provided nib file. If an existing cell was available for reuse, this method calls the cell’s [`prepareForReuse`](apple-reference-documentation://hccjldTnGo) method instead.
>
> ------ 来自苹果官方文档



### 尝试找答案

- 如何找呢？ 我们知道 ObjC并没有开源，不能通过读源码来找答案。
- 尝试在网上查找答案，没有找到满意的，但是发现了一个库 **Chameleon**
  - Chameleon 是一个移植 iOS 的 UIKit 框架到 Mac OS X 下的开源项目。该项目的目的在于尽可能给出 UIKit 的可替代方案，并且让 Mac OS 的开发者尽可能的开发出类似 iOS 的 UI 界面。
- 姑且认为，Chameleon中实现了 UITableView 复用原理
- 通过查看 Chameleon源码，试图窥探 UITableView复用机制



### Chameleon源码解读

代码较多，这里只贴跟**复用相关** 的主要代码

- _cachedCells;  // 保存正在屏幕显示的cell
- _reuseableCells; // 保存未显示在屏幕上，可以复用的cell

```objc
    NSMutableDictionary *_cachedCells;      // 保存正在屏幕显示的cell
    NSMutableSet *_reusableCells;           // 保存未显示在屏幕上，可以复用的cell
```

- 接下来，我们从目的出发来看源码， 我们的目的是看 **cell复用机制** 

  - 所以从 \- **(UITableViewCell *)dequeueReusableCellWithIdentifier:(NSString *)identifier** 方法开始看起

  ```objc
  - (UITableViewCell *)dequeueReusableCellWithIdentifier:(NSString *)identifier
  {
      for (UITableViewCell *cell in _reusableCells) {
          if ([cell.reuseIdentifier isEqualToString:identifier]) {
              UITableViewCell *strongCell = cell;
              
              // the above strongCell reference seems totally unnecessary, but without it ARC apparently
              // ends up releasing the cell when it's removed on this line even though we're referencing it
              // later in this method by way of the cell variable. I do not like this.
              [_reusableCells removeObject:cell];
  
              [strongCell prepareForReuse];
              return strongCell;
          }
      }
      
      return nil;
  }
  ```

- 从源码中可以看出，复用的cell 是从 _reusableCells set中，取出 reuseIdentifier 一致的cell

  - 找到后 调用 prerareForReuse, 并返回 cell

- 接下来， _reuseableCells 中的数据是什么时候添加的呢？

- 好了，第一天读此源码，没读明白。 明天继续了。







参考文献 : 

[UITableView的Cell复用原理和源码分析](https://www.jianshu.com/p/5b0e1ca9b673)

[Chameleon](https://github.com/BigZaphod/Chameleon)