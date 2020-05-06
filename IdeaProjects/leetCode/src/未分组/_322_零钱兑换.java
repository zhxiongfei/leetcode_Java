package 未分组;

/*
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

         

        示例 1:

        输入: coins = [1, 2, 5], amount = 11
        输出: 3
        解释: 11 = 5 + 5 + 1
        示例 2:

        输入: coins = [2], amount = 3
        输出: -1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/coin-change
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Arrays;

public class _322_零钱兑换 {

    public static int coinChange(int[] coins, int amount) {

        if (amount < 1) return 0;

        // 定义状态 凑到 amount 硬币需要的最少硬币个数
        int[] dp = new int[amount + 1];

        // 定义初始值
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;

        // 定义状态转移方程
        for (int i = 1; i <= amount; i++) {

            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) dp[i] = Math.min(dp[i - coins[j]] + 1,dp[i]);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {

        int[] nums = {1,2,5};
        int res = coinChange(nums,11);
        System.out.println(res);
    }
}
