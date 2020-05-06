package 动态规划;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

        现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/unique-paths-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

        网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

        示例 1:

        输入:
        [
          [0,0,0],
          [0,1,0],
          [0,0,0]
        ]
        输出: 2
        解释:
        3x3 网格的正中间有一个障碍物。
        从左上角到右下角一共有 2 条不同的路径：
        1. 向右 -> 向右 -> 向下 -> 向下
        2. 向下 -> 向下 -> 向右 -> 向右

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/unique-paths-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _63_不同路径II {

    // 动态规划 二维数组
    static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        // 异常情况判断
        // 如果矩阵为空 返回0。
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;

        // 如果矩阵只有一层。 遍历第0层，有1则返回0。 没有1则返回1。
        if (obstacleGrid.length == 1){
            for (int i = 0; i < obstacleGrid[0].length; i++) {
                if (obstacleGrid[0][i] == 1) return 0;
            }
            return 1;
        }

        // 矩阵只有1列。 遍历第0列，有1则返回0。 没有1则返回1。
        if (obstacleGrid[0] == null || obstacleGrid[0].length == 0) return 0;
        if (obstacleGrid[0].length == 1){
            for (int i = 0; i < obstacleGrid.length; i++) {
                if (obstacleGrid[i][0] == 1) return 0;
            }
            return 1;
        }

        // 动态规划 定义dp二维数组
        // dp[i][j] 表示第i层， 第j列格子的路径
        int[][] dp = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
        for (int i = 1; i <= obstacleGrid.length; i++) {
            for (int j = 1; j <= obstacleGrid[0].length; j++) {

                // 第一层
                if (i == 1){
                    // 如果等于1。 则后边都是0。
                    if(obstacleGrid[i-1][j-1] == 1){
                        break;
                    }
                    dp[i][j] = 1;
                    continue;
                }

                // 非第一层 如果值为1， 则dp为0。
                if (obstacleGrid[i-1][j-1] == 1){
                    dp[i][j] = 0;
                }else {
                    // 值不为1
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[obstacleGrid.length][obstacleGrid[0].length];
    }

    // 待优化 使用obstacleGrid作为dp数组。 不需要额外存储空间。

    public static void main(String[] args){

        int[][] list = {{0,1,0},{1,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(list));
    }
}
