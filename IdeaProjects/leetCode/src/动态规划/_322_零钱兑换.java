package 动态规划;

/*
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

        示例 1:

        输入: coins = [1, 2, 5], amount = 11
        输出: 3
        解释: 11 = 5 + 5 + 1
        示例 2:

        输入: coins = [2], amount = 3
        输出: -1
        说明:
        你可以认为每种硬币的数量是无限的。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/coin-change
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _322_零钱兑换 {

    // 暴力递归
    private int coins(int amount){
        if (amount < 1) return Integer.MAX_VALUE;
        if (amount == 25 || amount == 20 || amount == 5 || amount == 1) return 1;

        int a = coins(amount - 25);
        int b = coins(amount - 20);
        int c = coins(amount - 5);
        int d = coins(amount - 1);

        return Math.min(Math.min(a, b),Math.min(c,d)) + 1;
    }

    // 记忆化搜索
    public static int coinChange1(int[] coins, int amount) {

        if (amount < 1) return -1;
        int[] dp = new int[amount + 1];

        for (int n : coins) {
            dp[n] = 1;
        }

        return coins(amount,dp,coins);
    }

    static int coins(int amount, int[] dp, int[] coins){
        if (amount < 1) return Integer.MAX_VALUE;
        if (dp[amount] == 0){

            int min = coins(amount - coins[0],dp,coins);
            for (int i = 1; i < coins.length; i++) {
                min = Math.min(min,coins(amount - coins[i],dp,coins));
            }

            dp[amount] = min + 1;
        }
        return dp[amount];
    }

    // 递推
    static int coinChange2(int[] coins, int amount){
        if (amount < 1) return 0;
        int[] dp = new int[amount + 1];

        for (int i = 0; i <= amount; i++){

            // dp[i] = min {dp[i - 25],dp[i - 20],dp[i - 5],dp[i - 1]};
            int min = Integer.MAX_VALUE;
            if (i >= 1) dp[i] = Math.min(dp[i - 1],min);
            if (i >= 5) dp[i] = Math.min(dp[i - 5],min);
            if (i >= 20) dp[i] = Math.min(dp[i - 20],min);
            if (i >= 25) dp[i] = Math.min(dp[i - 25],min);

            dp[i] = min + 1;
        }

        return dp[amount];
    }

    static int coinChange(int[] coins, int amount){
        if (amount < 1) return 0;
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++){

            int min = Integer.MAX_VALUE;
            for (int coin :coins) {
                if (i < coin) continue;
                int v = dp[i - coin];
                if (v < 0 || v >= min) continue;
                min = v;
            }

            if (min == Integer.MAX_VALUE){
                dp[i] = -1;
            }else {
                dp[i] = min + 1;
            }
        }

        return dp[amount];
    }

    public static void main(String[] args){

        int[] coins = {1,5,20,25};
        System.out.println(coinChange(coins,97));
    }
}
