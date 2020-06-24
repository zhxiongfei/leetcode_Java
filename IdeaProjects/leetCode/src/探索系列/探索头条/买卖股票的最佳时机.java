package 探索系列.探索头条;

public class 买卖股票的最佳时机 {

    /**
     *
     * 动态规划三步走
     * 1,定义 dp数组含义
     *       dp数组 表示 以第i天结尾的收益
     * 2,定义 初始值
     *       dp[0] = 0;
     * 3,定义 状态转移方程
     *       当 prices[i] >= prices[i -1] 时 , dp[i] = dp[i - 1] + 差值
     *       当 prices[i] < prices[i - 1] 时 , Math.max(dp[i] = dp[i - 1] + 差值 , 0)
     *
     * */
    // [7,1,5,3,6,4]
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int[] dp = new int[prices.length];
        dp[0] = 0;

        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            dp[i] = Math.max(dp[i - 1] + diff, 0);

            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     *
     * 空间复杂度的优化
     *
     * */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int current = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            current = Math.max(current + diff, 0);

            max = Math.max(max, current);
        }
        return max;
    }
}
