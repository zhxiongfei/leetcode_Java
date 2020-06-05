---
title: "剑指offer53.0-n-1中缺失的数字"
date: 2020-06-05T15:45:25+08:00
draft: true
tags: ["数组","算法","二分搜索"]
---

# [面试题53 - II. 0～n-1中缺失的数字](https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/)

> 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
>
>  
>
> 示例 1:
>
> 输入: [0,1,3]
> 输出: 2
> 示例 2:
>
> 输入: [0,1,2,3,4,5,6,7,9]
> 输出: 8
>
>
> 限制：
>
> 1 <= 数组长度 <= 10000
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 题解：

- ### **有序数组，查询某个元素，首先应该想到二分查找**

  1. 如果第一个元素不为0， 说明缺失首元素0
  2. 如果最后一个元素与其下标相等，说明数组连续不缺少数组，则缺少最后一个数字，返回数组长度
  3. 写一个函数, 找到 [begain, mid] 区间缺失的数字
  4. 求出mid
     1. 如果mid == nums[mid] 说明，mid左边的所有元素都不缺失。查找mid 右边 ，begain = mid + 1，继续查找
     2. 如果mid != nums[mid] 寿命，缺失的元素在右边，end = mid
     3. 直到 begain == end， 则返回begain



### 思路一:

递归

```java
// 递归
    public int missingNumber1(int[] nums) {
        // 如果第一个元素不是0， 说明缺少0
        if (nums[0] != 0) return 0;
        // 最后一个元素， 跟其下标相等。 说明数组是连续的。 缺少最后一个元素，返回数组的长度
        if (nums[nums.length - 1] == nums.length - 1) return nums.length;

        return binarySearch(nums, 0, nums.length - 1);
    }

    // 左右均闭合
    public int binarySearch(int[] nums, int begain, int end){
        if (begain == end) return begain;

        int mid = (begain + end) >> 1;
        if (mid == nums[mid]) return binarySearch(nums, mid + 1, end);
        return binarySearch(nums, begain, mid);
    }

```



### 思路二：

迭代

```java
    // 迭代
    public int missingNumber2(int[] nums) {
        // 如果第一个元素不是0， 说明缺少0
        if (nums[0] != 0) return 0;
        // 最后一个元素， 跟其下标相等。 说明数组是连续的。 缺少最后一个元素，返回数组的长度
        if (nums[nums.length - 1] == nums.length - 1) return nums.length;

        int begain = 0, end = nums.length - 1;
        while (begain < end){
            int mid = (begain + end) >> 1;

            if (mid == nums[mid]){
                begain = mid + 1;
            }else{
                end = mid;
            }
        }
        return begain;
    }
```



### 思路三：

- 在题解中看到一个思路
- **相加求差值** ， 差值就是缺失的元素
- 思路比较清奇，但是效率不如二分
- 因为相加求和的过程，需要O(N) 的时间复杂度
- 等差数列求和，之后的球差值, O(1)
- 整体时间复杂度 O(N) + O(1) = O(N)
- 可以看出来，跟二分比较，效率相差还是非常大的
- 下边我们写一下**相加求差值**的代码，验证一下

```java
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int totalSum = (1 + nums.length) * nums.length / 2;
        return totalSum - sum;
    }

```



### 效率对比

二分搜索：双百操作 

![屏幕快照 2020-06-05 下午3.26.44](https://tva1.sinaimg.cn/large/007S8ZIlly1gfhgwgd45xj30s6070js6.jpg)

相加求差值： 执行耗时就非常差了





![屏幕快照 2020-06-05 下午3.43.32](https://tva1.sinaimg.cn/large/007S8ZIlly1gfhh083j75j30oi06ggmd.jpg)



以上两张图也验证了我们上边复杂度的分析

相加求差值的代码，**完全没有利用到数组是升序的条件**

所以以后做题过程中，碰到**有序数组查找元素**，首先就应该想到**二分搜索**。

