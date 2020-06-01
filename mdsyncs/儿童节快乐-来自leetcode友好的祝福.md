---
title: "儿童节快乐-来自leetcode友好的祝福"
date: 2020-06-01T17:47:30+08:00
draft: true
---

# 6.1儿童节

今天是6.1儿童节, 今日份 leetcode 非常友好 , 可以说是儿童节福利题. 

#### [1431. 拥有最多糖果的孩子](https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/)

> 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
>
> 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
>
>  
>
> 示例 1：
>
> 输入：candies = [2,3,5,1,3], extraCandies = 3
> 输出：[true,true,true,false,true] 
> 解释：
> 孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
> 孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
> 孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
> 孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
> 孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
> 示例 2：
>
> 输入：candies = [4,2,1,1,2], extraCandies = 1
> 输出：[true,false,false,false,false] 
> 解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
> 示例 3：
>
> 输入：candies = [12,1,12], extraCandies = 10
> 输出：[true,false,true]
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 题解：

- 两遍循环
- 第一遍找出最大值max
- 第二遍循环，当前数组 + extraCandies >= max，则为true，否则false
- 初始化boolean 数组，第二遍遍历时，依次将结果加入数组中，返回即可
- 时间复杂度 : O(N)
- 空间复杂度 : O(1)

代码如下:

```java
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }

        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < candies.length; i++) {
            list.add(candies[i] + extraCandies >= max);
        }
        return list;
    }
```



题目没有什么弯弯绕, 直接两遍循环解决问题. 第一次碰到这么简单的题目, 送分题，可以说是儿童节福利了。



网友的评论也非常有才, 贴出来, 乐一乐.

![屏幕快照 2020-06-01 下午4.10.52](https://tva1.sinaimg.cn/large/007S8ZIlly1gfcxz36iy3j316e0ms0vt.jpg)

