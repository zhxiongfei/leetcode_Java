---
title: "策略模式在iOS中的应用"
date: 2020-07-02T17:10:23+08:00
draft: true
tags: ["设计模式"]
catagory: "设计模式"
---

# 什么是策略模式?

接下来，我们通过之前复习过的**排序算法** 来谈谈**策略模式**。

以**选择排序**为例，之前我们写的排序算法， 数据类型写的 Integer 类型的数组.

###  代码如下 : 

```java
// 交换元素
public void swap(int[] nums, int i1, int i2){
    int tmp = nums[i1];
    nums[i1] = nums[i2];
    nums[i2] = nums[i1];
}

// 选择排序
public void selectionSort(int[] nums){
    for (int i = nums.length - 1; i > 0; i--){
        int maxIdx = 0;
        for (int j = 1; j <= i; j++) {
            if (nums[j] > nums[maxIdx]){
                maxIdx = j;
            }
        }
        swap(nums, maxIdx, i);
    }
}
```





## 需求变化

这时，如果我们要排序 double 类型的数组呢？ 

这有啥难的? 直接把上述代码拷贝一份，类型换成**double**呗。反正Java支持**函数重载**，确实可以做得到。

但是有个问题，如果有大量的需要比较的类型，甚至包括**自定义对象**， 岂不是每一种对象都得**copy**一份方法, 明显不符合**开闭原则** (**对扩展开放，对修改闭合**)，因为我们没增加一个比较类，就需要对 **selectionSort**类修改。



## 改进

如何解决上边提到的问题？

我们**声明**一个**Comparable**接口，接口中有一个 **compareTo(T o)** 方法,为了能兼容各种类型的对象，我们使用**范型**.

并要求使用 **selectionSort**  排序的对象，必须实现 **compareTo(T o)** 方法.

这时，我们实现多种对象的排序上， 就会方便很多了.

### 代码如下 :

接口代码 : 

```java
// 接口
public interface Comparable<T>{
    int compareTo(T o);
}
```

选择排序代码 : 

```java
// 交换元素
public void swap(Comparable[] nums, int i1, int i2){
    Comparable tmp = nums[i1];
    nums[i1] = nums[i2];
    nums[i2] = tmp;
}

// 选择排序
public void selectionSort(Comparable[] nums){
    for (int i = nums.length - 1; i > 0; i--){
        int maxIdx = 0;
        for (int j = 1; j <= i; j++) {
            if (nums[j].compareTo(nums[maxIdx]) > 0){
                maxIdx = j;
            }
        }
        swap(nums,maxIdx, i);
    }
}
```

需要排序的对象类

```java
public class Dog implements Comparable<Dog>{
    int weight;
    public Dog(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Dog o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                '}';
    }
}
```

这样我们就限制了，传入的需要排序的类，必须实现 **Comparable**接口，并且使用 **Comparable**接口中的 **int compareTo(T o);** 方法来比较排序对象。



## 仍然不够灵活

但是上边这种做法，依然**不够灵活**. **Why?** 因为比如 Cat 对象中，有两个属性 **height,  age**， 我们有时候需要用 **height** 排序，有时候使用 **age** 来排序.

```java
public class Cat implements Comparable<Cat>{

    int height;
    int age;

    public Cat(int height, int age) {
        this.height = height;
        this.age = age;
    }

    @Override
    public int compareTo(Cat o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", age=" + age +
                '}';
    }
}
```

如果采用上述 **Comparable** 方法, 我们没办法实现使用 **height** 排序。



## 继续改进

接下来正式引入我们的主角，**策略模式**，**Comparator接口**

**策略模式**符合设计模式的**开闭原则 : 对修改关闭，对扩展开放**

我们依然定义一个接口 **Comparator**接口，并且要求比较的类实现**compare方法**

### 代码如下 : 

接口代码 :

```java
public interface Comparator <T>{
    int compare(T o1, T o2);
}
```

比较代码 : 

