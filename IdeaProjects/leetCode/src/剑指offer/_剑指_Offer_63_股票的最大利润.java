package 剑指offer;

/*
假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

         

        示例 1:

        输入: [7,1,5,3,6,4]
        输出: 5
        解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
        注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
        示例 2:

        输入: [7,6,4,3,1]
        输出: 0
        解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _剑指_Offer_63_股票的最大利润 {

    /**
     *
     * 初级动态规划算法题目
     *
     * 定义 dp数组含义
     *  dp[i] 表示，截止第 i 天，股票的利润
     *
     * 定义初始值
     *  dp[0] = 0. 第一天 利润为 0
     *
     * 定义状态转移方程
     *  dp[i] 为 当 dp[i - 1] >=0时， 则dp[i] = dp[i - 1] + diff
     *           当 dp[i - 1] < 0时， 则dp[i] = diff;
     *
     * */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int res = 0;

        // dp数组表示，截止 第 i 天的股票利润
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            dp[i] = Math.max(dp[i - 1], 0) + diff;

            res = Math.max(res, dp[i]);
        }

        return res;
    }

}
