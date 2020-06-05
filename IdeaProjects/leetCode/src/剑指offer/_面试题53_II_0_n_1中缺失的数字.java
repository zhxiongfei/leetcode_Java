package 剑指offer;

/*
一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

         

        示例 1:

        输入: [0,1,3]
        输出: 2
        示例 2:

        输入: [0,1,2,3,4,5,6,7,9]
        输出: 8

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题53_II_0_n_1中缺失的数字 {

    /*
    *
    * 有序的数组，查询某个元素
    * 首先想到二分搜索
    *
    * 如果第一个元素不是0，说明缺失0
    * 如果最后一个元素与其下标相等，说明数组连续，缺少最后一个元素，返回数组长度
    *
    * 写一个函数，找到[begain, mid] 区间的 缺失的数字
    *
    * 求出mid
    *   如果mid == nums[mid] 说明，mid左边的所有元素都不缺失。 begain = mid + 1. 继续查找
    *   如果mid != nums[mid] 说明，缺失的元素在右边. end = mid;
    *   找到 begain == end. 则返回
    *
    * */

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


    /*
    *
    * 题解中看到一个思路
    *
    * 相加求差值 就是缺失的元素
    *
    * 思路比较清奇，但是效率不如二分
    * 因为相加求和的过程，需要O(N)的时间复杂度
    *
    * 等差数列求和 ，之后求差值，O(1)
    *
    * 整体时间复杂度 O(N) + O(1) = O(N)
    * 可以看出来， 跟二分比较， 效率还是不行的
    *
    * 下边我们写一下代码， 验证一下
    * 从截图可以看出，二分时间空间双百，而求和时间复杂度就比较差了. 也证明了我们上边的分析
    * */

    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int totalSum = (1 + nums.length) * nums.length / 2;
        return totalSum - sum;
    }

    public static void main(String[] args) {
        _面试题53_II_0_n_1中缺失的数字 cls = new _面试题53_II_0_n_1中缺失的数字();
        int[] nums = {0,1};
        cls.missingNumber(nums);
    }

}
