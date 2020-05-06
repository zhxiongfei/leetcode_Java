package 动态规划;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

        问总共有多少条不同的路径？



        例如，上图是一个7 x 3 的网格。有多少可能的路径？

        说明：m 和 n 的值均不超过 100。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/unique-paths
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _62_不同路径 {

    // 动态规划 二维数组
    static int uniquePaths1(int m, int n) {
        // 异常情况判断
        if (m == 0 || n == 0) return 0;
        if (m == 1 || n == 1) return 1;

        // 定义存储二维数组
        // dp[i][j] 表示 从start 到第i行，第j列的不同路径和。
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1){
                    dp[i][j] = 1;
                    continue;
                }
                if (j == 1){
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m][n];
    }

    // 动态规划 一维数组
    static int uniquePaths(int m, int n) {
        // 异常情况判断
        if (m == 0 || n == 0) return 0;
        if (m == 1 || n == 1) return 1;

        // 定义存储二维数组
        // dp[i] 表示最后一行，第j列的不同路径和。
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1){
                    dp[j] = 1;
                    continue;
                }

                dp[j] = (i == 1) ? 0 : dp[j-1] + dp[j];
            }
        }

        return dp[n];
    }

    public static void main(String[] args){
        int res = uniquePaths(3,2);
        System.out.println(res);
    }
}
