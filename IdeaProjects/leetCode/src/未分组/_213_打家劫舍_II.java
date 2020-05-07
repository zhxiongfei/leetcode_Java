package 未分组;

/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

        给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

        示例 1:

        输入: [2,3,2]
        输出: 3
        解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
        示例 2:

        输入: [1,2,3,1]
        输出: 4
        解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
             偷窃到的最高金额 = 1 + 3 = 4 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/house-robber-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Arrays;

public class _213_打家劫舍_II {
    public static int rob(int[] nums) {

        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0],nums[1]);

        return Math.max(robRange(Arrays.copyOfRange(nums,0,nums.length - 1)),
                        robRange(Arrays.copyOfRange(nums,1,nums.length)));
    }

    public static int robRange(int[] nums){

        // 定义状态，dp[i] 代表抢i家 所得的最多总金额
        int[] dp = new int[nums.length];

        // 设置初始状态
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        // 确定状态转移方程
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public static int robRange1(int[] nums){

        // 定义状态 dp数组第i位代表房屋数量i时，可打劫的最高金额

        // 初始状态
        int first = nums[0];
        int second = Math.max(nums[0],nums[1]);

        int result = second;
        // 动态转移方程，即确定dp[i] 与 dp[i - 1]的关系
        for (int i = 2; i < nums.length; i++) {
            result = Math.max(first + nums[i], second);

            first = second;
            second = result;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int res = rob(nums);
        System.out.println(res);
    }
}
