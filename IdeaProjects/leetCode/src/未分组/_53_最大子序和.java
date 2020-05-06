package 未分组;

/*
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

        示例:

        输入: [-2,1,-3,4,-1,2,1,-5,4],
        输出: 6
        解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
        进阶:

        如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-subarray
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _53_最大子序和 {

    public int maxSubArray(int[] nums) {

        // 定义状态 dp[i] 是以i结尾的最大子序列和
        int[] dp = new int[nums.length];

        // 定义初始值 dp[0] 就是数组的第一个元素
        dp[0] = nums[0];

        // 用来存储最终的最大值
        int maxVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (dp[i - 1] > 0){
                // 当dp[i-1] > 0是时，则dp[i] 是前一个 加上 当前num
                dp[i] = dp[i - 1] + num;
            }else {
                // 当dp[i-1] <= 0时，则dp[i]就是当前的 num
                dp[i] = num;
            }
            // 记录dp数组的最大值
            maxVal = Math.max(dp[i], maxVal);
        }
        return maxVal;
    }



    public int maxSubArray1(int[] nums) {

        // 定义初始值 dp[0] 就是数组的第一个元素
        int prev = nums[0];

        // 用来存储最终的最大值
        int maxVal = prev;

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (prev > 0){
                // 当dp[i-1] > 0是时，则dp[i] 是前一个 加上 当前num
                prev += num;
            }else {
                // 当dp[i-1] <= 0时，则dp[i]就是当前的 num
                prev = num;
            }
            // 记录dp数组的最大值
            maxVal = Math.max(prev, maxVal);
        }
        return maxVal;
    }

    public int maxSubArray2(int[] nums) {

        // 用来存储最终的最大值
        int maxVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (nums[i - 1] > 0){
                // 当dp[i-1] > 0是时，则dp[i] 是前一个 加上 当前num
                nums[i] = nums[i - 1] + num;
            }else {
                // 当dp[i-1] <= 0时，则dp[i]就是当前的 num
                nums[i] = num;
            }
            // 记录dp数组的最大值
            maxVal = Math.max(nums[i], maxVal);
        }
        return maxVal;
    }



}
