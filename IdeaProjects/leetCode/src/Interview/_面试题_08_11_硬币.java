package Interview;

/*
硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)

        示例1:

        输入: n = 5
        输出：2
        解释: 有两种方式可以凑成总金额:
        5=5
        5=1+1+1+1+1
        示例2:

        输入: n = 10
        输出：4
        解释: 有四种方式可以凑成总金额:
        10=10
        10=5+5
        10=5+1+1+1+1+1
        10=1+1+1+1+1+1+1+1+1+1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/coin-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_08_11_硬币 {

    public int waysToChange(int n) {

        int[] dp = new int[n + 1];
        int[] coins = new int[]{1,5,10,25};
        dp[0] = 1;

        for(int coin : coins) {
            for(int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        _面试题_08_11_硬币 cls = new _面试题_08_11_硬币();
        cls.waysToChange(44);
    }
}