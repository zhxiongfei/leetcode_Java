package 动态规划;

/*
给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。

        示例:

        输入: 13
        输出: 6
        解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/number-of-digit-one
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _233_数字1的个数 {

    /**
     *
     * 题解
     * 参考 面试_17_6_2出现的次数
     *
     * */
    public int countDigitOne(int n) {

        if(n <= 0) {
            return 0;
        }
        int digit = (int)Math.log10(n) + 1;
        int[][] dp = new int[digit+1][2];
        // dp[i][0] = numberOf2sInRange(n % pow(10, i)) 保存0~n的1-i位组成的数包含2的个数
        // dp[i][1] = numberOf2sInRange(99..9) 保存i位均为9包含2的个数
        dp[1][0] = n % 10 >= 1 ? 1:0;
        dp[1][1] = 1;
        for(int i = 2; i <= digit; i++) {
            int k = n/ ((int)Math.pow(10, i-1)) % 10;
            dp[i][0] = k * dp[i-1][1] + dp[i-1][0];
            if(k == 1) {
                dp[i][0] += n % (int)Math.pow(10, i-1) +1;
            } else if(k > 1){
                dp[i][0] +=  (int)Math.pow(10, i-1);
            }
            dp[i][1] = 10 * dp[i-1][1] + (int)Math.pow(10, i-1); //计算1-i位均为9的值包含2的个数
        }
        return dp[digit][0];
    }

}
