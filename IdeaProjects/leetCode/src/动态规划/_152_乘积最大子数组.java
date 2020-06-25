package 动态规划;

/*
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。

         

        示例 1:

        输入: [2,3,-2,4]
        输出: 6
        解释: 子数组 [2,3] 有最大乘积 6。
        示例 2:

        输入: [-2,0,-1]
        输出: 0
        解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-product-subarray
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;

public class _152_乘积最大子数组 {

    // [-2, 3, -4]
    public static int maxProduct(int[] nums) {

        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // 最终结果
        int res = nums[0];

        // 以i结尾的子数组的最大乘积
        int max = nums[0];
        // 以i结尾的子数组的组小乘积
        int min = nums[0];

        for (int i = 1; i < nums.length; i++){

            int tmp = max;
            max = Math.max(Math.max(max * nums[i],nums[i]),min * nums[i]);
            min = Math.min(Math.min(tmp * nums[i],nums[i]),min * nums[i]);

            res = Math.max(res,max);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        maxProduct(nums);
    }
}
