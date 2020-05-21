#### [面试题 08.03. 魔术索引](https://leetcode-cn.com/problems/magic-index-lcci/)

> 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
>
> 示例1:
>
>  输入：nums = [0, 2, 3, 4, 5]
>  输出：0
>  说明: 0下标的元素为0
> 示例2:
>
>  输入：nums = [1, 1, 1]
>  输出：1
> 提示:
>
> nums长度在[1, 1000000]之间
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/magic-index-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

###### 思路一：

暴力法：

```java
    public int findMagicIndex1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) return i;
        }
        return -1;
    }
```



###### 思路二：

二分搜索

```java
// 二分法
    // 先找到mid的下标
    // 如果mid == nums[mid]，res赋值，继续在mid左边查找
    // 否则在mid的左边查找结果，如果找不到则在右边查找
    int res = -1;
    public void search(int[] nums, int left , int right){

        if (left >= right) return;
        int mid = (left + right) >> 1;

        if (nums[mid] == mid){
            // 找到相同的
            res = mid;
            // 在左边继续找较小的结果
            search(nums, left, mid);
        }else{
            // 没有相同的，先找左边
            search(nums, left, mid);
            if (res == -1){
                // 左边没找到，在右边找
                search(nums, mid + 1, right);
            }
        }
    }

    // 二分查找
    public int findMagicIndex(int[] nums){

        search(nums,0,nums.length);
        return res;
    }
```



***面试题总结***

***二叉堆相关知识(top K问题)***

***排序(冒泡，选择排序，快排，堆排序,插入排序)***

