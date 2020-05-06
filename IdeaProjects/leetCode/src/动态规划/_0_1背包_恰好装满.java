package 动态规划;

/*
有n个物品，和一个最大承重为W的背包。 每件物品的重量是wi, 价值是vi
在保证总重量恰好等于W的前提下，选择某些物品装入背包，背包的最大总价值是多少？
注意： 每件物品只有1件，也就是每个物品只能选择0件或者1件

假设 values 是价值数组，weights是重量数组
编号为k的物品，价值是values[k], 重量是weights[k], k 范围 [0,n)
 */

public class _0_1背包_恰好装满 {

    static int solution(int[] values, int[] weights, int maxWeight){

        if (values == null || values.length == 0 || weights == null || weights.length == 0 || maxWeight <= 0) return 0;

        // dp[i][j] 表示  有前i件物品可选时， 最大承重为j
        int[] dp = new int[maxWeight + 1];

        for (int j = 1; j <= maxWeight; j++) {
            dp[j] = Integer.MIN_VALUE;
        }

        // 初始值 dp(i,0) dp(0,j) 为 0
        int weight = 0;
        int value = 0;
        for (int i = 1; i <= weights.length; i++) {
            weight = weights[i-1];
            value = values[i-1];
            for (int j = maxWeight; j >= weight; j--) {
                // 物品装得下， 选择 或者 不选 最大值
                dp[j] = Math.max(dp[j-1],value + dp[j-weight]);
            }
        }
        return dp[maxWeight] < 0 ? -1 : dp[maxWeight];
    }
}
