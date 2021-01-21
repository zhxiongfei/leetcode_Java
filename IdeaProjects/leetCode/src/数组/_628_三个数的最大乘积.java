package 数组;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-product-of-three-numbers/
 * */
public class _628_三个数的最大乘积 {

    /**
     * 全排序
     * 如果数组全非负数 :   则最大的三个数的乘积 为最终结果
     * 如果数组全负数 :     则最大的三个数的乘积 仍为最终结果
     * 如果数组包含非负数 和 负数 : 则最大的三个数的乘积， 和 最小的两个数的乘积和最大的数乘积的较大者。 为最终结果
     *
     * 时间复杂度 : O(N * log N)
     * 空间复杂度 : O(1)
     * */
    public int maximumProduct1(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int res = Math.max(nums[len - 1] * nums[0] * nums[1], nums[len - 1] * nums[len - 2] * nums[len - 3]);
        return res;
    }

    /**
     * 线性扫描
     * 记录最大的三个数 以及最小的两个数
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(1)
     * */
    public int maximumProduct(int[] nums) {
        int max0 = Integer.MIN_VALUE, max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min0 = Integer.MAX_VALUE, min1 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > max0){
                max2 = max1;
                max1 = max0;
                max0 = num;
            }else if (num > max1){
                max2 = max1;
                max1 = num;
            }else if (num > max2){
                max2 = num;
            }
            if (num < min0){
                min1 = min0;
                min0 = num;
            }else if (num < min1){
                min1 = num;
            }
        }
        return Math.max(max0 * max1 * max2, min0 * min1 * max0);
    }

    public static void main(String[] args) {
        int[] nums = {5,2,3,1,4,6,-1,8,9,-5,10};
        _628_三个数的最大乘积 cls = new _628_三个数的最大乘积();
        cls.maximumProduct(nums);
    }
}
