package 数组;

/**
给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
        如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。

        示例 1：
        输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
        输出：true
        解释：
        在上述矩阵中, 其对角线为:
        "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
        各条对角线上的所有元素均相同, 因此答案是 True 。

        示例 2：
        输入：matrix = [[1,2],[2,2]]
        输出：false
        解释：
        对角线 "[1, 2]" 上的元素不同。
         

        提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 20
        0 <= matrix[i][j] <= 99

        进阶：
        如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
        如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/toeplitz-matrix
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _766_托普利茨矩阵 {

    /**
    [1,2,3,4],
    [5,1,2,3],
    [9,5,1,2]
    */
    /**
     * 直白的方法
     * 直接按对角线遍历
     *
     * 时间复杂度 : O(m * n)
     * 空间复杂度 : O(1)
     * */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        if (m == 0 || n == 0) return true;

        int k = 1, count = m + n;
        while (k < count){
            int i = 0, j = 0;
            if (k <= m){
                i = m - k;
                j = 0;
            }else {
                i = 0;
                j = k - m;
            }
            int cmp = matrix[i][j];

            while (i <= m && j <= n){
                if (cmp != matrix[i][j]) return false;
                i ++;
                j ++;
            }
            k ++;
        }
        return true;
    }

    public boolean isToeplitzMatrix1(int[][] matrix){
        for (int i = 0; i < matrix.length - 1; ++i) {
            for (int j = 0; j < matrix[0].length - 1; ++j) {
                if (matrix[i][j] != matrix[i + 1][j + 1])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _766_托普利茨矩阵 cls = new _766_托普利茨矩阵();
        int[][]matrix = {{1,2,3,4},
                         {5,1,2,3},
                         {9,6,1,2}};
        System.out.println(cls.isToeplitzMatrix(matrix));
    }

}
