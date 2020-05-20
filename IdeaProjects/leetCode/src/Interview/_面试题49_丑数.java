package Interview;

/*
我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

         

        示例:

        输入: n = 10
        输出: 12
        解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
        说明:  

        1 是丑数。
        n 不超过1690。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/chou-shu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题49_丑数 {

    // 同 面试题17_09
    public int nthUglyNumber(int n) {

        int p2 = 0, p3 = 0, p5 = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[p2] * 2, dp[p3] * 3), dp[p5] * 5);

            if (dp[i] == dp[p2] * 2) p2 ++;
            if (dp[i] == dp[p3] * 3) p3 ++;
            if (dp[i] == dp[p5] * 5) p5 ++;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        _面试题49_丑数 cls = new _面试题49_丑数();
        cls.nthUglyNumber(20);
    }
}
