package 动态规划;

import java.util.Stack;

public class _121_买卖股票的最佳时机 {

    /**

     暴力法
     遍历每一种可能
     时间复杂度 : O(n ^ 2)
     空间复杂度 : O(1)

     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int max = 0;
        for (int i = 1; i < prices.length; i++) {

            for (int j = 0; j < i; j++) {
                if (prices[i] <= prices[j]) continue;
                max = Math.max(max,prices[i] - prices[j]);
            }
        }

        return max;
    }

    /**

     动态规划
     记录最小价值 min 为数组首元素
     记录最大获利润 res

     从 index为1 元素开始遍历
     当最小值 > prices[i] 时, 说明当前下标没有利润。 将 minprice = prices[i] 即可
     当最小值 < prices[i] 时, maxprofit = max(maxprofit,prices[i] - minprice);

     时间复杂度 : O(n)
     空间复杂度 : O(1)

     */
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1) return 0;
        int maxprofit = 0;
        int minprice = prices[0];

        for (int i = 1; i < prices.length; i++) {

            if (minprice > prices[i]){
                minprice = prices[i];
                continue;
            }
            maxprofit = Math.max(maxprofit, prices[i] - minprice);
        }
        return maxprofit;
    }

    /**
     *
     * next greater number问题
     * 使用单调栈
     *
     * 可以解决问题，顺便复习一下单调栈的用法也不错
     * 但是个人感觉，用的比较牵强。
     * 本可以使用一个 minprice 解决的问题
     * 何必使用一个栈来解决呢？ 并且栈上，貌似最多存放的元素也是 1 个
     * 相当于 用 stack 来存放 minprice
     *
     * */

    public int maxProfit2(int[] prices){
        if (prices == null || prices.length <= 1) return 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0]);

        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];

            while (!stack.isEmpty() && price < stack.peek()){
                stack.pop();
            }

            if (stack.isEmpty()) stack.push(price);

            maxprofit = Math.max(maxprofit, price - stack.peek());
        }

        return maxprofit;
    }
}
