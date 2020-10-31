package 二分查找;

import 回溯算法._40_组合总和II;

/**
给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。

        进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

         

        示例 1：

        输入：nums1 = [1,3], nums2 = [2]
        输出：2.00000
        解释：合并数组 = [1,2,3] ，中位数 2
        示例 2：

        输入：nums1 = [1,2], nums2 = [3,4]
        输出：2.50000
        解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
        示例 3：

        输入：nums1 = [0,0], nums2 = [0,0]
        输出：0.00000
        示例 4：

        输入：nums1 = [], nums2 = [1]
        输出：1.00000
        示例 5：

        输入：nums1 = [2], nums2 = []
        输出：2.00000
         

        提示：

        nums1.length == m
        nums2.length == n
        0 <= m <= 1000
        0 <= n <= 1000
        1 <= m + n <= 2000
        -106 <= nums1[i], nums2[i] <= 106

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _4_寻找两个正序数组的中位数 {

    /**
     * 合并两个升序数组成一个升序数组
     * 时间复杂度O(m + n)
     * 不符合题目要求,
     *
     * */
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length){
            if (i == nums1.length){
                nums[i + j] = nums2[j ++];
                continue;
            }
            if (j == nums2.length){
                nums[i + j] = nums1[i ++];
                continue;
            }
            if (nums1[i] < nums2[j]){
                nums[i + j] = nums1[i ++];
            }else {
                nums[i + j] = nums2[j ++];
            }
        }

        if (nums.length % 2 == 1) return (double)nums[nums.length >> 1];
        return ((double)nums[nums.length>>1] + (double)nums[(nums.length>>1) - 1]) / 2;
    }

    /**
     * 题目中有对时间复杂度的要求 O(log(m + n))
     * 明显提示要使用的是 : 二分查找
     *
     * 具体思路是 :
     * 把两个有序数组分为两部分
     * 需要满足
     *     nums1分割点左边元素 < nums2分割点右边元素
     *     nums2分割点左边元素 < nums1分割点右边元素
     *
     * 当两个数组个数之和为偶数时, 为分割点左右两边 四个元素中间两个元素的和 / 2
     * 当两个数组个数之和为奇数时, 为奇数数组左边元素 和 偶数数组分割点左右两边排序的中间值
     *
     * */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int count = nums1.length + nums2.length;
        return divide(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, count);
    }

    public static double divide(int[] nums1, int[] nums2, int start1,int end1, int start2, int end2, int n){
        int div1 = (start1 + end1) >> 1;
        int div2 = (start2 + end2) >> 1;

        int l1 = nums1[div1], r1 = nums1[div1 + 1], l2 = nums2[div2], r2 = nums2[div2 + 1];
        if (l1 < r2 && r1 < l2){
            double res = ((double)Math.max(l1, l2) + (double)Math.min(r1, r2)) / 2;
            return res;
        }

        if (r2 < l1){
            // nums1 区域变为 [start1, div1]; nums2 区域变为 [div2, end2]
            return divide(nums1, nums2, start1, div1, div2, end2, n);
        }

        // nums1 区域变为 [start1, div1]; nums2 区域变为 [div2, end2]
        return divide(nums1, nums2, div1, end1, start2, div2, n);
    }

    public static void main(String[] args) {
//        int[] nums1 = {3,8,9,10};
//        int[] nums2 = {2,4,6,12,18,20};
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {5,6,7,8,9,10};
        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
}
