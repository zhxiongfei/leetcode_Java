package 探索系列.探索头条;

public class 买卖股票的最佳时机II {

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;

        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0)
                res += diff;
        }
        return res;
    }

}
