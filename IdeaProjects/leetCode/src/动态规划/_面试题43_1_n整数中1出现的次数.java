package 动态规划;
/*
输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

        例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

         

        示例 1：

        输入：n = 12
        输出：5
        示例 2：

        输入：n = 13
        输出：6

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题43_1_n整数中1出现的次数 {

    public int countDigitOne(int n) {

        if(n <= 1) {
            return n;
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
