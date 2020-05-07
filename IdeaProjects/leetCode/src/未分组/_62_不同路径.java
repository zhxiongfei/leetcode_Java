package 未分组;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

        问总共有多少条不同的路径？



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/unique-paths
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _62_不同路径 {

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (m == 1 || n == 1) return 1;

        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + 1;
            }
        }

        return dp[m][n];
    }

}
