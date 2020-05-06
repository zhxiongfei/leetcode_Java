package 动态规划;

public class _121_买卖股票的最佳时机 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        if (prices.length == 1) return 0;

        return max(prices,prices.length - 1);
    }

    // 暴力两层循环
    private int max1(int[] prices, int end){
        int max = 0;
        for (int i = 1; i <= end; i++) {

            for (int j = 0; j < i; j++) {
                if (prices[i] <= prices[j]) continue;
                max = Math.max(max,prices[i] - prices[j]);
            }
        }

        return max;
    }

    // 动态规划 记忆数组
    private int max2(int[] prices, int end){
        int[] dp = new int[end + 1];
        dp[0] = 0;
        dp[1] = prices[1] - prices[0];

        int max = dp[1];
        for (int i = 2; i <= end; i++) {
            int tmp = prices[i] - prices[i - 1];
            dp[i] = Math.max(dp[i - 1] + tmp,tmp);

            max = Math.max(dp[i],max);

            System.out.println(max);
        }
        return max < 0 ? 0 : max;
    }

    // 动态规划 记录变量
    private int max(int[] prices, int end){

        int cur = prices[0] - prices[1] > 0 ? prices[0] - prices[1] : 0;
        int max = cur;

        for (int i = 2; i <= end; i++) {
            int tmp = prices[i] - prices[i - 1];
            cur = Math.max(cur + tmp,tmp);

            max = Math.max(cur,max);

            System.out.println(max);
        }
        return max;
    }


    public int maxProfit1(int[] prices) {

        if (prices.length <= 1) return 0;

        int[] dp = new int[prices.length];
        dp[0] = 0;

        int max = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {

            if (min > prices[i]){
                dp[i] = 0;
                min = prices[i];

                continue;
            }

            max = Math.max(max, prices[i] - min);
        }

        return max;
    }
}
