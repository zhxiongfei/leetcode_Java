package 数组;

/**
给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。

        矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
        示例 1：

        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[[1,4,7],[2,5,8],[3,6,9]]
        示例 2：

        输入：matrix = [[1,2,3],[4,5,6]]
        输出：[[1,4],[2,5],[3,6]]
         

        提示：

        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 1000
        1 <= m * n <= 105
        -109 <= matrix[i][j] <= 109

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/transpose-matrix
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _867_转置矩阵 {

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                res[i][j] = matrix[j][i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][]matrix = {{1,2,3},{4,5,6}};
        transpose(matrix);
    }
}
