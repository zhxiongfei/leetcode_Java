

## ObjC block



### 从C++看block本质

- 首先来看一个最简单的block, 没有返回值，没有参数

  ```objc
  int a = 10;
  void (^block)(void) = ^{
     NSLog(@"a is %d", a);
  };
  block();
  ```

- 我们编写的 OC代码，底层实现其实都是 C/C++代码

- 所以要理解 block 的本质， 我们将下边代码转换为 C++ 代码

  ```
  xcrun -sdk iphoneos clang -arch arm64 -rewrite-objc main.m -o main.cpp
  ```

- 转为C++代码以后, 我们创建和调用 block 代码如下 : 

  ```cpp
  // block的赋值 
  void (*block)(void) = &__main_block_impl_0(__main_block_func_0, &__main_block_desc_0_DATA, a));
  // block的调用
  block->FuncPtr();
  ```

  ```cpp
  // block结构体
  struct __main_block_impl_0 {
    struct __block_impl impl;					// 实现
    struct __main_block_desc_0* Desc; // 描述
    int a;														// 捕获的变量
  };
  ```

  ```cpp
  // block 的实现结构体
  struct __block_impl {
    void *isa;
    int Flags;
    int Reserved;
    void *FuncPtr;	// FuncPtr指向函数的具体实现
  };
  ```

  ```java
  // block 的描述信息
  static struct __main_block_desc_0 {
    size_t reserved;
    size_t Block_size;
  }
  ```

  ```cpp
  // __block_impl 的 funcPtr指向的函数具体实现
  // 就是，我们编写的 ObjC 的 打印 "a is %d", a
  static void __main_block_func_0(struct __main_block_impl_0 *__cself) {
    	int a = __cself->a; // bound by copy
  		NSLog(***, a);
  }
  ```

- 经过转为 C++ 代码，分析后发现，我们在 ObjC 中编写的 block 代码，在 C++中转为了**结构体**

- 这个结构体中，包含 block 函数的具体实现，block 的size等等

- 并且在 block结构体中，有我们访问的**外部局部变量 int a**



### block 的变量捕获 capture

#### 举例分析

- 上边的分析中，我们得知 block 访问的外部局部变量，被**捕获**到了block结构体内部

- 为什么要捕获这个变量呢？ 

- 举个🌰，查看下边代码 : 

  ```objc
  int main(int argc, const char * argv[]) {
      @autoreleasepool {
          void (^block)(void) = nil;
          {
              int a = 10;
              block = ^{
                  NSLog(@"a is %d", a);
              };
          }
          block();
      }
      return 0;
  }
  ```

- int a 是局部变量, 在花括号结束后， a就释放了
- 既然它释放了，我们在花括号外调用block() 如何访问 a 呢
- 这就用到了**变量捕获**, 要**保证我们在 block 函数体中能访问需要的局部变量，所以需要变量捕获。**



#### C++分析

- 从上述 C++ 代码可以看出，在构建 block 的 结构体时，将 变量 a 传入了 结构体的**构造函数**， 使用**C++中初始化列表**给结构体中的 a 赋值

- 在**__main_block_func_0** 中，访问 a 时。 直接取出来 **int a = __cself->a;** ,再进行访问

  

#### 值引用？地址引用？

##### 值引用

先看如下代码。

```c
 int a = 10;
 void (^block)(void)  = ^{
     NSLog(@"a is %d", a);
 };
 a = 20;
 block();
```

- 问题： 在 block 中 输出的 a 是10 还是 20 ？
- 答案是 **10**
- 为什么是10呢？ 从上述 C++ 代码分析可以看出来， 我们是直接拿 a 的值直接传入构造函数，为结构体中 a 赋值
- 所以这里是**值引用**， 相当于这里直接把 10 捕获到结构体中， 再与 变量 a 没有一点关系。
- 这也是为什么，在block中不能给 a 赋值的原因，block中能访问的 a 相当于是常量 10



##### 地址引用

那么，如何能实现, 外部 a 变化以后，block内部的 a 也发生变化？以及，如何才能在 block 中修改 a 的值？

- 答案就是使用，**引用传递**
- 通过前边 C++的学习，C++的lambda表达式非常方便改为引用传递，直接在捕获列表中 把 a 变为 &a 即可
- 而在 ObjC中不可以显式的设置捕获列表。OC为我们提供了 **__block** 关键字
  - 使用 __block 修饰的变量，可以在 block函数体内部赋值，并且外部修改后，也可以值同步
- 我们猜想，使用 __block修饰后的变量，从**值引用遍历了地址引用**
- 接下来，我们通过C++代码来验证我们的猜想



##### 从C++代码验证__block关键字

- 同样，我们把 main.m 转化为 main.cpp
- 转化为 cpp后，发现代码变得特别复杂，我们这里着重看重点代码，没必要去分析晦涩难懂的大量 C++代码

```cpp
struct __main_block_impl_0 {
  struct __block_impl impl;
  struct __main_block_desc_0* Desc;
  __Block_byref_a_0 *a; // by ref
};
```

```objc
__Block_byref_a_0 a = {(void*)0,(__Block_byref_a_0 *)&a, 0, sizeof(__Block_byref_a_0), 10};

block = &__main_block_impl_0(__main_block_func_0, &__main_block_desc_0_DATA, (__Block_byref_a_0 *)&a, 570425344));

(a.__forwarding->a) = 20;

block->FuncPtr)();
```

```cpp
struct __Block_byref_a_0 {
  void *__isa;
__Block_byref_a_0 *__forwarding;
 int __flags;
 int __size;
 int a;
};
```

```cpp
static void __main_block_func_0(struct __main_block_impl_0 *__cself) {
  		__Block_byref_a_0 *a = __cself->a; // bound by ref
  		(a->__forwarding->a) = 30;
}
```

- 重点代码是以上三段主要代码

- 可以看出，使用 __block修饰的变量a, 在定义时，就被封装成了一个 结构体

  - **__Block_byref_a_0**
    - 此结构体中，isa地址是 变量 a 的地址
    - 有一个指向自己的 **__forwarding**指针指向 
    - 还有 变量 a 的值

- 在 构造 **__main_block_impl_0**  时，传入的是 **__Block_byref_a_0** 的地址值

- 当 访问 a  时，直接取到的 __Block_byref_a_0结构体 的 (a.__forwarding->a) = 20; 地址赋值

- 在取值时，仍然是 通过 

  