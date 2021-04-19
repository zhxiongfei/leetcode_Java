package 动态规划;

/**
给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵
 找出只包含 1 的最大矩形，并返回其面积。
*/

/**
[
 ["1","0","1","0","0"],
 ["1","0","1","1","1"],
 ["1","1","1","1","1"],
 ["1","0","0","1","0"]]
]
*/

public class _85_最大矩形 {

    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length, res = 0;

        // 1, 定义dp数组
        int[][]dp = new int[rows][cols];
        dp[0][0] = matrix[0][0] - '0';

        // 2, dp[i][j] 表示 以 i,j 结尾的最大矩阵
        for (int i = 1; i < cols; i++) {
            dp[0][i] = (matrix[0][i]-'0') == 1 ? dp[0][i-1] + 1 : 0;
            res = Math.max(res, dp[0][i]);
        }
        for (int i = 1; i < rows; i++) {
            dp[i][0] = (matrix[i][0]-'0') == 1 ? dp[i-1][0] + 1 : 0;
            res = Math.max(res, dp[0][i]);
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] - '0' == 0) continue;

                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + 1;
                if (dp[i-1][j] > 0 && dp[i][j-1] > 0 && dp[i-1][j-1] > 0){
                    dp[i][j] = dp[i-1][j-1];
                }
                res = Math.max(res, dp[0][i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _85_最大矩形 cls = new _85_最大矩形();

//        {'1','0','1','1'}
//        {'1','0','1','1'}
//        {'1','0','1','1'}

        char[][]matrix = {{'1','0','1','1'},{'1','0','1','1'},{'1','0','1','1'}};
        System.out.println(cls.maximalRectangle(matrix));
    }
}
