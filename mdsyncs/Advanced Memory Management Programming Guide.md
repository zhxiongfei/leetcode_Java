## Advanced Memory Management Programming Guide

(高级内存管理编程指南)

[原文地址](https://developer.apple.com/library/archive/documentation/Cocoa/Conceptual/MemoryMgmt/Articles/MemoryMgmt.html#//apple_ref/doc/uid/10000011i)

> Application memory management is the process of allocating memory during your program’s runtime, using it, and freeing it when you are done with it. A well-written program uses as little memory as possible. In Objective-C, it can also be seen as a way of distributing ownership of limited memory resources among many pieces of data and code. When you have finished working through this guide, you will have the knowledge you need to manage your application’s memory by explicitly managing the life cycle of objects and freeing them when they are no longer needed.
>
> Although memory management is typically considered at the level of an individual object, your goal is actually to manage object graphs. You want to make sure that you have no more objects in memory than you actually need.

> 应用程序内存管理是在程序运行时分配内存，使用它并在完成后释放它的过程。
>
> 编写良好的程序将使用尽可能少的内存。
>
> 在ObjC中，它也可以看作是在许多数据和代码之间分配有限内存资源所有权的一种方法。
>
> 完成本指南的学习后，你将拥有显式管理内存对象生命周期并在不再需要他们时释放他们的知识，从而可以管理应用程序的内存。
>
> 尽管通常在单个对象级别上考虑内存管理，但实际上你的目标是管理对象图。你要确保内存中没有超出实际需要的对象。

​		![屏幕快照 2020-11-04 下午3.55.01](https://tva1.sinaimg.cn/large/0081Kckwly1gkd71enhkoj30kv0d8gn2.jpg)



## At a Glance

> Objective-C provides two methods of application memory management.
>
> 1. In the method described in this guide, referred to as “manual retain-release” or *MRR*, you explicitly manage memory by keeping track of objects you own. This is implemented using a model, known as reference counting, that the Foundation class `NSObject` provides in conjunction with the runtime environment. 
> 2. In Automatic Reference Counting, or *ARC*, the system uses the same reference counting system as MRR, but it inserts the appropriate memory management method calls for you at compile-time. You are strongly encouraged to use ARC for new projects. If you use ARC, there is typically no need to understand the underlying implementation described in this document, although it may in some situations be helpful. For more about ARC, see *[Transitioning to ARC Release Notes](https://developer.apple.com/library/archive/releasenotes/ObjectiveC/RN-TransitioningToARC/Introduction/Introduction.html#//apple_ref/doc/uid/TP40011226)*.

> ObjC提供了两种应用程序内存管理的方法
>
> 1. 在本指南中的描述中,称为"手动保留释放" 或者 **MRR**, 你可以通过跟踪自己拥有的对象来显式的管理内存。这是使用Foundation类 NSObject 与运行时环境一起提供的模型(引用计数)来实现的
> 2. 在自动引用计数，或者 **ARC**,系统使用与MRR相同的引用计数系统，但是会在**编译时**为您插入适当的内存管理方法调用，强烈建议您将ARC用于新项目。 如果使用ARC，尽管在某些情况下可能会有所帮助，但通常无需了解本文档中描述的基本实现。



### Good Practices Prevent Memory-Related Problems

良好的做法可以防止和内存相关的问题

> There are two main kinds of problem that result from incorrect memory management:
>
> - Freeing or overwriting data that is still in use
>
>   This causes memory corruption, and typically results in your application crashing, or worse, corrupted user data.
>
> - Not freeing data that is no longer in use causes memory leaks
>
>   A memory leak is where allocated memory is not freed, even though it is never used again. Leaks cause your application to use ever-increasing amounts of memory, which in turn may result in poor system performance or your application being terminated.
>
> Thinking about memory management from the perspective of reference counting, however, is frequently counterproductive, because you tend to consider memory management in terms of the implementation details rather than in terms of your actual goals. Instead, you should think of memory management from the perspective of object ownership and object graphs.
>
> Cocoa uses a straightforward naming convention to indicate when you own an object returned by a method. 
>
> 

> 错误的内存管理导致两类主要问题：
>
> - 释放或覆盖正在使用的数据
>
>   这会导致内存损坏，通常会导致您的应用崩溃，或者更糟糕的是，破坏用户数据
>
> - 不释放不再使用的数据会导致内存泄露
>
>   内存泄漏时分配的内存不会被释放的地方，即使它不再使用。泄漏会导致您的应用程序使用不断增加的内存量，进而可能导致系统性能下降或应用程序被终止。
>
> 但是从引用计数的角度考虑内存通常会适得其反，因为你忽略根据实现细节而不是实际目标来考虑内存。 相反，你应该从对象所有权和对象图的角度考虑内存管理。



## Memory Management Policy

> The basic model used for memory management in a reference-counted environment is provided by a combination of methods defined in the `NSObject` protocol and a standard method naming convention. The `NSObject` class also defines a method, `dealloc`, that is invoked automatically when an object is deallocated. This article describes all the basic rules you need to know to manage memory correctly in a Cocoa program, and provides some examples of correct usage.

> NSObject 协议中定义的方法和标准方法命名约定的组合提供了在引用计数环境中用于内存管理的基本模型。
>
> NSObject也定义了一种方法，dealloc，当对象被释放时自动调用。
>
> 本文介绍了Cocoa程序中正确的内存所需的基本规则，并提供了一些正确用法的示例。

## Basic Memory Management Rules

(内存管理基本规则)

> The memory management model is based on object ownership. Any object may have one or more owners. As long as an object has at least one owner, it continues to exist. If an object has no owners, the runtime system destroys it automatically. To make sure it is clear when you own an object and when you do not, Cocoa sets the following policy:
>
> - **You own any object you create**
>
>   You create an object using a method whose name begins with “alloc”, “new”, “copy”, or “mutableCopy” (for example, `alloc`, `newObject`, or `mutableCopy`). 
>
> - **You can take ownership of an object using retain**
>
>   A received object is normally guaranteed to remain valid within the method it was received in, and that method may also safely return the object to its invoker. You use `retain` in two situations: (1) In the implementation of an accessor method or an `init` method, to take ownership of an object you want to store as a property value; and (2) To prevent an object from being invalidated as a side-effect of some other operation (as explained in [Avoid Causing Deallocation of Objects You’re Using](https://developer.apple.com/library/archive/documentation/Cocoa/Conceptual/MemoryMgmt/Articles/mmPractical.html#//apple_ref/doc/uid/20000043-1000922)).
>
> - **When you no longer need it, you must relinquish ownership of an object you own**
>
>   You relinquish ownership of an object by sending it a `release` message or an `autorelease` message. In Cocoa terminology, relinquishing ownership of an object is therefore typically referred to as “releasing” an object. 
>
> - **You must not relinquish ownership of an object you do not own**
>
>   This is just corollary of the previous policy rules, stated explicitly.

> 内存管理模型基于对象所有权。任何对象都可以具有一个或多个所有者。 只要一个对象至少具有一个所有者，它将会继续存在。如果对象没有所有者，则运行时系统会自动销毁它。 为了确保您清楚拥有和不拥有某个对象，Cocoa设置了以下策略。

> - 你将拥有自己创建的任何对象
>   - 你创建对象使用方法 "alloc","new","copy" 或者 "mutableCopy"(举例，alloc, newObject 或者 mutableCopy)
> - 你可以使用 retain 获取对象的所有权
>   - 通常可以保证接收到的对象在接收该对象的方法中保持有效，并且该方法也可以安全的将该对象返回给其调用者。
>   - 你在以下两种情况下用到 retain
>     1. 在访问器方法或者init方法中，将要存储的对象的所有权作为属性值。
>     2. 防止对象因其他操作的副作用而失效(如避免使用中的对象引起分配问题)
> - 当你不再需要它时，必须放弃对您拥有的对象的所有权
>   - 通过发送一条 release 消息或者一条 autorelease 消息来放弃对象的所有权。 因此，在Cocoa中，放弃对象的所有权称为释放对象
> - 你不能放弃你步拥有的对象的所有权



### Use autorelease to Send a Deferred release

(使用 autorelease 发送一个延迟释放消息)

> You use `autorelease` when you need to send a deferred `release` message—typically when returning an object from a method. For example, you could implement the `fullName` method like this:

> 当你需要发送延迟release从方法返回一个对象时，使用 autorelease。例如，你可以实现如下fullName方法

```objc
- (NSString *)fullName {
    NSString *string = [[[NSString alloc] initWithFormat:@"%@ %@",self.firstName, self.lastName] autorelease];
    return string;
}
```

> You own the string returned by `alloc`. To abide by the memory management rules, you must relinquish ownership of the string before you lose the reference to it. If you use `release`, however, the string will be deallocated before it is returned (and the method would return an invalid object). Using `autorelease`, you signify that you want to relinquish ownership, but you allow the caller of the method to use the returned string before it is deallocated
>
> You could also implement the `fullName` method like this:

> 你使用了alloc拥有所返回的字符串。为了遵守内存管理规则，必须放弃对字符串的所有权，然后再丢失对该字符串的引用。如果使用release，则字符串会在返回之前被释放(该方法返回无效的对象)。
>
> 使用autorelease，表示您将要放弃所有权，但允许方法调用者在释放之前使用它。
>
> 你还可以实现fullName方法

```objc
- (NSString *)fullName {
    NSString *string = [[NSString alloc] initWithFormat:@"%@ %@",self.firstName, self.lastName];
    return string;
}
```

> Following the basic rules, you don’t own the string returned by `stringWithFormat:`, so you can safely return the string from the method.
>
> By way of contrast, *the following implementation is wrong*:

> 根据基本规则，你不拥有所返回的字符串stringWithFormat:， 因此可以安全地从该方法返回该字符串。
>
> 相比之下，以下是错误的

```objc
- (NSString *)fullName {
    NSString *string = [[NSString alloc] initWithFormat:@"%@ %@",self.firstName, self.lastName];
    return string;
}
```

> According to the naming convention, there is nothing to denote that the caller of the `fullName` method owns the returned string. The caller therefore has no reason to release the returned string, and it will thus be leaked.

> 根据命名规则，没有什么可以表示该fullName方法的调用者拥有返回的字符串。 因此，调用者没有理由释放返回的字符串，因此它将被泄漏。

> Some methods in Cocoa specify that an object is returned by reference (that is, they take an argument of type `ClassName **` or `id *`). A common pattern is to use an `NSError` object that contains information about an error if one occurs, as illustrated by `initWithContentsOfURL:options:error:` (`NSData`) and `initWithContentsOfFile:encoding:error:` (`NSString`).
>
> In these cases, the same rules apply as have already been described. When you invoke any of these methods, you do not create the `NSError` object, so you do not own it. There is therefore no need to release it, as illustrated in this example:
>
> ```objc
> NSString *fileName = <#Get a file name#>;
> NSError *error;
> NSString *string = [[NSString alloc] initWithContentsOfFile:fileName
>                         encoding:NSUTF8StringEncoding error:&error];
> if (string == nil) {
>     // Deal with error...
> }
> // ...
> [string release];
> 
> ```

> Cocoa中的某些方法指定对象是通过引用返回的(即，它们采用类型ClassName ** 或者 id *). 
>
> 一个常见的模式是使用一个NSError*对象，该对象包含了发生错误的信息。
>
> 在这些情况下，适用相同的规则。 当您调用这些方法中的任何一个时，您不会创建该NSError对象，因此您不拥有该对象。 因此无需释放它



## Implement dealloc to Relinquish Ownership of Objects

(实现 dealloc 放弃对象的所有权)

> The `NSObject` class defines a method, `dealloc`, that is invoked automatically when an object has no owners and its memory is reclaimed—in Cocoa terminology it is “freed” or “deallocated.”. The role of the `dealloc` method is to free the object's own memory, and to dispose of any resources it holds, including ownership of any object instance variables.
>
> The following example illustrates how you might implement a `dealloc` method for a Person class:
>
> ```objc
> @interface Person : NSObject
> @property (retain) NSString *firstName;
> @property (retain) NSString *lastName;
> @property (assign, readonly) NSString *fullName;
> @end
>  
> @implementation Person
> // ...
> - (void)dealloc
>     [_firstName release];
>     [_lastName release];
>     [super dealloc];
> }
> @end
> ```

> 在NSObject类定义的方法中，dealloc是当一个对象不具有拥有者它的内存被回收时自动调用，在Cocoa术语中称为，释放。
>
> 该dealloc方法的作用是释放对象自己的内存呢，并处理其拥有的任何资源，包括任何对象实例变量的所有权。

> **Important:** Never invoke another object’s `dealloc` method directly.
>
> You must invoke the superclass’s implementation at the *end* of your implementation.
>
> You should not tie management of system resources to object lifetimes; see [Don’t Use dealloc to Manage Scarce Resources](https://developer.apple.com/library/archive/documentation/Cocoa/Conceptual/MemoryMgmt/Articles/mmPractical.html#//apple_ref/doc/uid/TP40004447-SW13).
>
> When an application terminates, objects may not be sent a `dealloc` message. Because the process’s memory is automatically cleared on exit, it is more efficient simply to allow the operating system to clean up resources than to invoke all the memory management methods. 

> 重要 : 切勿直接调用一个对象的 dealloc 方法
>
> 你必须在实现结束时调用超类的实现。
>
> 你不应将系统资源的管理和对象生存期联系在一起。
>
> 当应用程序终止时，可能不会向对象发送dealloc消息。因为在退出时会自动清除进程的内存，所以仅允许操作系统清理资源比调用所有内存管理方法更为有效。