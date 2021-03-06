package 动态规划;

/*
给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

        示例 1:

        输入:
        A: [1,2,3,2,1]
        B: [3,2,1,4,7]
        输出: 3
        解释:
        长度最长的公共子数组是 [3, 2, 1]。
        说明:

        1 <= len(A), len(B) <= 1000
        0 <= A[i], B[i] < 100

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _718_最长重复子数组 {

    /**
     *
     * 暴力法
     * 穷举每一种情况
     *
     * 暴力法时间复杂度 : O(N ^ 3)
     *
     * */
    public static int findLength1(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) return 0;

        int m = A.length;
        int n = B.length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i] == B[j]) { // 遇到相同项
                    int subLen = 1;   // 公共子序列长度至少为1
                    while (A[i + subLen] == B[j + subLen] &&
                                    i + subLen < m &&
                                    j + subLen < n){
                        // 它们下一项也相同
                        // 并且没有越界
                        subLen++; // 既像指针，每次考察一项，又是公共子序列长度
                    }
                    res = Math.max(subLen, res);
                }
            }
        }
        return res;
    }

        /**
         *
         * 此题目 跟 最长重复子序列 很像
         * 不同之处再由，子序列可以不连续，子数组一定连续
         *
         * 子序列 与 1143题一样。 最长公共子序列
         *
         * 动态规划 三步走
         *
         * 定义 dp 数组含义 dp[i][j] 表示 数组A截止第 i 位的子数组 和 数组B截止 第 j 位的子数组的公共子数组的长度
         *
         * 定义初始值 因 Java中初始化的数组，其中默认值为0， 不需要再次定义
         *
         * 状态转移方程
         * 当 n1 == n2时, 则 dp[i][j] = dp[i - 1][j - 1] + 1
         * 当 n1 != n2时，dp[i][j] = 0
         *
         * */
    public static int findLength(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) return 0;

        // dp[i][j] 表示 数组A截止第 i 位的子数组 和 数组B截止 第 j 位的子数组 的公共子数组的长度
        int[][] dp = new int[A.length + 1][B.length + 1];

        int result = 0;
        for (int i = 1; i <= A.length; i++) {
            int n1 = A[i - 1];
            for (int j = 1; j <= A.length; j++) {
                int n2 = B[j - 1];

                if (n1 == n2){
                    // 如果 n1 == n2
                    // 则 dp[i][j] = dp[i-1][j-1] + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
                // 如果 n1 != n2
                // 则 dp[i][j] 默认为 0， 认为他们以 i，j结尾的子数组， 没有公共子数组
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] A = {1,0,1,0,1};
        int[] B = {0,1,1,1,1};

        findLength(B,A);
    }
}
