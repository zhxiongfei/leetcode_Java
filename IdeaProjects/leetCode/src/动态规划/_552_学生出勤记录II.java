package 动态规划;

/**
给定一个正整数 n，返回长度为 n 的所有可被视为可奖励的出勤记录的数量。 答案可能非常大，你只需返回结果mod 109 + 7的值。

        学生出勤记录是只包含以下三个字符的字符串：

        'A' : Absent，缺勤
        'L' : Late，迟到
        'P' : Present，到场
        如果记录不包含多于一个'A'（缺勤）或超过两个连续的'L'（迟到），则该记录被视为可奖励的。

        示例 1:

        输入: n = 2
        输出: 8
        解释：
        有8个长度为2的记录将被视为可奖励：
        "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
        只有"AA"不会被视为可奖励，因为缺勤次数超过一次。
        注意：n 的值不会超过100000。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/student-attendance-record-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _552_学生出勤记录II {

    /**
     * 大神题解
     * https://leetcode-cn.com/problems/student-attendance-record-ii/solution/javadong-tai-gui-hua-ru-he-yi-bu-bu-si-kao-yu-shi-/
     * */
    public int checkRecord(int n) {
        int _MOD = 1000000007;
        long[][][] dp = new long[n + 1][2][3]; // i, A:{0:non, 1:contains}, L:{0,1,2}
        // dp[1][..][..]
        dp[1][0][0] = 1;    // 第一层, 0A 0L
        dp[1][1][0] = 1;    // 第一层, 1A 0L
        dp[1][0][1] = 1;    // 第一层, 0A 1L
        for (int i = 2; i <= n; i++) {

            // 当前这末尾+P
            // 无A末尾无L 可来自上一层  无A末尾无L + 无A末尾1L + 无A末尾2L
            // 0A0L = 0A0L + 0A1L + 0A2L
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % _MOD;
            // 同理 接着推 1A0L
            // 1A0L = 1A0L + 1A1L + 1A2L
            dp[i][1][0] = (dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % _MOD;

            // +L
            // 0A1L
            dp[i][0][1] = dp[i-1][0][0];

            // 0A2L
            dp[i][0][2] = dp[i-1][0][1];

            // 1A1L
            dp[i][1][1] = dp[i-1][1][0];

            // 1A2L
            dp[i][1][2] = dp[i-1][1][1];

            // +A
            // 此处的 dp[i][1][0] 在上面 +P 时已经开始出现，故此处应 +=
            // 1A0L = 0A0L + 0A1L + 0A2L
            dp[i][1][0] += (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % _MOD;
        }

        // 返回第 n 层的六种情况
        return (int)((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] +
                dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % _MOD);
    }

}
