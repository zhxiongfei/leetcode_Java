package 动态规划;

/**
给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
        上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
        示例：

        给定 matrix = [
        [3, 0, 1, 4, 2],
        [5, 6, 3, 2, 1],
        [1, 2, 0, 1, 5],
        [4, 1, 0, 1, 7],
        [1, 0, 3, 0, 5]
        ]

        sumRegion(2, 1, 4, 3) -> 8
        sumRegion(1, 1, 2, 2) -> 11
        sumRegion(1, 2, 2, 4) -> 12
         

        提示：

        你可以假设矩阵不可变。
        会多次调用 sumRegion 方法。
        你可以假设 row1 ≤ row2 且 col1 ≤ col2 。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _304_二维区域和检索_矩阵不可变 {

    /**
     * dp[i][j]表示 第 i 行 第 j 列的总和
     * */
    int[][]dp = null;
    public _304_二维区域和检索_矩阵不可变(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int rows = matrix.length, cols = matrix[0].length;
        dp = new int[rows][cols];
        dp[0][0] = matrix[0][0];
        // 第 0 行 i 列
        for (int i = 1; i < cols; i++){
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        // 第 i 行 0 列
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0) return dp[row2][col2];
        if (row1 == 0) return dp[row2][col2] - dp[row2][col1-1];
        if (col1 == 0) return dp[row2][col2] - dp[row1-1][col2];

        return dp[row2][col2] - dp[row1-1][col2] - dp[row2][col1 - 1] + dp[row1-1][col1-1];
    }

    public static void main(String[] args) {
        int[][] matrix =
                {
                        {3, 0, 1, 4, 2},
                        {5, 6, 3, 2, 1},
                        {1, 2, 0, 1, 5},
                        {4, 1, 0, 1, 7},
                        {1, 0, 3, 0, 5}
                };
        _304_二维区域和检索_矩阵不可变 cls = new _304_二维区域和检索_矩阵不可变(matrix);
        int res = cls.sumRegion(1,1,2,2);
        System.out.println(res);
    }

}
