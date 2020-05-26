---
stitle: "一个iOS小需求中对算法的优化"
date: 2020-05-26T10:49:40+08:00
draft: true
tags: ["iOS","动态规划","算法"]
catagory: "iOS"
---

### 需求

最近接到一个小需求, 在一个feed流里, 展示商品列表，商品列表中数据都是一样的，只是展示上有主推的商品，需要大图展示, 其余商品小图展示。 

产品给出了排列顺序 1 - 2 - 1 - 4 - 1 - 2 - 1 -4...

也就是数组下标第 0 3 8 11 16 19 24...

观察发现,  dp[1] = dp[0] + 3; dp[2] = dp[1] + 5; dp[3] = dp[2] + 3;...

依次 +3, +5, +3, +5 ......


![屏幕快照 2020-05-26 下午3.29.16](https://tva1.sinaimg.cn/large/007S8ZIlly1gf5vwqqxegj304e0y20zo.jpg)

### 思路

在后端看来，所有的商品都是一个样的，需要前端来判断，数组中哪些下标的商品，需要展示大图。 

所以我们需要计算出，所有大图的位置。



初步想法，整体用collectionview，先判断出大图的下标，下标为大图时，用一个格式的cell。 小图时，用另一种格式的cell。

那么我们就需要知道，某个下标是大图？还是小图？



所以我们需要一个算法，此算法传入index代表当前cell的下标， 返回boolean代表是否是大图。



#### 方法一：

递归

如果当前index是大图， 则index - 8位置定是大图， 且 index - 3 或 index - 5位置为大图。满足以上条件，则此下标是大图。 否则是小图

```java
    // 递归
    private static boolean isright(int index){
        if (index < 3 && index != 0) return false;
        if (index == 0 || index == 3) return true;
        return isright(index- 8) && (isright(index - 3) || isright(index - 5));
    }
```

但是递归，就像 **斐波那契数列** 一样，存在大量重复的调用。 

时间复杂度为指数级别， 时间复杂度特别高。 经过使用时间测试，当传入的index 为 195时，函数消耗时间就会达到 87.424s。 明显不能用, 当我们商品量稍微大一些时，计算会卡死。 甚至商品量足够大时， 直接会 **stackoverflow** 栈溢出。

测试时间代码如下:

```java
        TimeTool.check("递归", new TimeTool.Task() {
            @Override
            public void execute() {
                isright(195);
            }
        });
```

最终输出结果:

```java
【递归】
开始：13:36:34.258
结束：13:38:01.689
耗时：87.424秒
```



#### 方法二：

迭代：

上边分析我们得出，只要知道上一个元素，以及 +3 ,还是+5，就能**递推**出当前元素. 所以我们使用flag变量记录是 +3 还是 +5. prev变量记录上一个元素的值。

代码如下:

```java
    // 迭代
    private static boolean isright1(int num){
        if (num < 3 && num != 0) return false;
        if (num == 0 || num == 3) return true;

        int prev = 8;
        boolean flag = true;
        while (prev < num){

            prev += flag ? 3 : 5;
            flag = !flag;
        }
        return prev == num;
    }
```

上边**递推**过程，有一遍while循环, 时间复杂度为 O(N)。 时间复杂度为 O(1). 就比**递归**算法优秀很多了。经测试，也说明了这个问题。

测试代码如下：

```java
       int num = (int) 2000000000;
        TimeTool.check("迭代", new TimeTool.Task() {
            @Override
            public void execute() {
                isright1( num);
            }
        });
```

测试结果如下：

```java
【迭代】
开始：16:05:57.510
结束：16:05:58.344
耗时：0.828秒
```

可看出，当我们的计算值 达到 2000000000时，耗时 0.828s。相比递归195都要计算一分多钟，确实优秀了很多。



#### 思路三：

观察数组发现，0，3，8，11，16，19，24，27...

数组中元素，要么能被8整除，要么除8余3.  所以想到第三种算法: 传入数字对8取余, 余数为0，或者3. 则为大图，否则为小图.

```java
    // 取余
    private static boolean isright2(int num){
        return num%8 == 0 || num%8 == 3;
    }
```

如果思路二算法计算过程比较优秀，那第三种就是瞬间完成，非常优秀了。

时间复杂度 : O(1)

空间复杂度: O(1)

经测试，也说明了，我们这个问题.

测试代码如下：

```java
        int num = (int) 2000000000;
        TimeTool.check("取余", new TimeTool.Task() {
            @Override
            public void execute() {
                isright2(num);
            }
        });
```

测试结果 ：

```Java
【取余】
开始：16:05:58.358
结束：16:05:58.358
耗时：0.0秒
```



### 解决

所以此需求，最终的解决方案是，一个函数用来判断是否是大图。此函数，用上述方法中第三个，O(1)时间复杂度的算法。

```c
bool isMajorGoods(long index){
    return index%8 == 0 || index%8 == 3;
}
```

计算出是否是大图的bool值， 在**UICollectionView** 代理方法中, 判断cell的size，以及那一种cell。以下是伪代码:

```objective-c
- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
    if (isMajorGoods(indexPath.row)) {
        return 大图size;
    }
    return 小图size;
}

- (__kindof UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    if (isMajorGoods(indexPath.row)) {
        return 大图cell;
    }
    return 小图cell;
}
```



### 彩蛋

**动态规划**, **丑数**

看到这想到**丑数**动态规划的计算流程，三个指针指向起始位置指向0, 比较三个指针指向的元素 * 指针的值, 较小者就是下一个丑数。 

但是这种规律貌似比丑数简单很多？ 确实是，只要知道上一个元素以及当前是+3还是+5就能解决问题。



所以想到计算数组中哪一位是数组时，使用动态规划。

动态规划，计算前N个大图的index. 代码如下 :

```java
    // 动态规划
    public static void getIndex(int num){

        int[] dp = new int[num];
        boolean flag = true;
        dp[0] = 0;
        for (int i = 1; i < num; i++) {
            dp[i] = dp[i - 1] + (flag ? 3 : 5);
            flag = !flag;
        }
    }
```

经过计算过后，dp数组中存储的就是，前num-1个，大图的下标。

