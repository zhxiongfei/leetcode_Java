package 动态规划;

/*
有n个物品，和一个最大承重为W的背包。 每件物品的重量是wi, 价值是vi
在保证总重量不超过W的前提下，选择某些物品装入背包，背包的最大总价值是多少？
注意： 每件物品只有1件，也就是每个物品只能选择0件或者1件

假设 values 是价值数组，weights是重量数组
编号为k的物品，价值是values[k], 重量是weights[k], k 范围 [0,n)
 */

public class _0_1背包问题 {

    // 动态规划 二维数组
    // 时间复杂度 O(m * n)
    // 空间复杂度 O(m * n)
    static int solution1(int[] values, int[] weights, int maxWeight){

        if (values == null || values.length == 0 || weights == null || weights.length == 0 || maxWeight == 0) return 0;

        // dp[i][j] 表示  有前i件物品可选时， 最大承重为j
        int[][] dp = new int[values.length + 1][maxWeight + 1];

        // 初始值 dp(i,0) dp(0,j) 为 0
        int weight = 0;
        int value = 0;
        for (int i = 1; i <= weights.length; i++) {
            weight = weights[i-1];
            value = values[i-1];
            for (int j = 1; j <= maxWeight; j++) {
                if (weight > j) {
                    // 物品装不下
                    dp[i][j] = dp[i-1][j];
                }else {
                    // 物品装得下， 选择 或者 不选 最大值
                    dp[i][j] = Math.max(dp[i-1][j],value + dp[i-1][j-weight]);
                }
            }
        }
        return dp[values.length][maxWeight];
    }

    // 动态规划 优化 空间复杂度
    // 一维数组
    // 时间复杂度 O(m * n)
    // 空间复杂度 O(maxWeight)
    static int solution2(int[] values, int[] weights, int maxWeight){

        if (values == null || values.length == 0 || weights == null || weights.length == 0 || maxWeight <= 0) return 0;

        // dp[i][j] 表示  有前i件物品可选时， 最大承重为j
        int[] dp = new int[maxWeight + 1];

        // 初始值 dp(i,0) dp(0,j) 为 0
        int weight = 0;
        int value = 0;
        for (int i = 1; i <= weights.length; i++) {
            weight = weights[i-1];
            value = values[i-1];
            for (int j = maxWeight; j >= 1; j--) {
//                if (weight > j) {
//                    // 物品装不下 直接利用上边的值。所以没必要赋值。直接continue就可以。
//                    dp[j] = dp[j];
//                }else {
//                    // 物品装得下， 选择 或者 不选 最大值
//                    dp[j] = Math.max(dp[j-1],value + dp[j-weight]);
//                }

                if (weight > j) continue;
                // 物品装得下， 选择 或者 不选 最大值
                dp[j] = Math.max(dp[j-1],value + dp[j-weight]);
            }
        }
        return dp[maxWeight];
    }

    // 动态规划 优化 空间复杂度
    // 一维数组 优化循环次数
    // 时间复杂度 O(m * n)
    // 空间复杂度 O(maxWeight)
    // 最大承重 小于 当前重量时。 结束循环。
    static int solution(int[] values, int[] weights, int maxWeight){

        if (values == null || values.length == 0 || weights == null || weights.length == 0 || maxWeight <= 0) return 0;

        // dp[i][j] 表示  有前i件物品可选时， 最大承重为j
        int[] dp = new int[maxWeight + 1];

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
        return dp[maxWeight];
    }

    public static void main(String[] args){

        int[] values = {6,3,5,4,6};
        int[] weights = {2,2,6,5,4};
        int maxWeight = 10;
        int res = solution(values,weights,maxWeight);

        System.out.println(res);
    }
}
