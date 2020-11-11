package 动态规划;

/*
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

         

        示例 1:

        输入:
        [
          [1,3,1],
          [1,5,1],
          [4,2,1]
        ]
        输出: 12
        解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _面试题47_礼物的最大价值 {

    public static int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0){
                    if (j > 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
                    continue;
                }
                if (j == 0){
                    if (i > 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int res = maxValue(grid);
        System.out.println(res);
    }
}
