package 数组;

import java.util.ArrayList;
import java.util.List;

/**
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

         

        示例 1：


        输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        输出：[1,2,3,6,9,8,7,4,5]
        示例 2：


        输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        输出：[1,2,3,4,8,12,11,10,9,5,6,7]
         

        提示：

        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 10
        -100 <= matrix[i][j] <= 100

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/spiral-matrix
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _54_螺旋矩阵 {

    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix.length == 0) return new ArrayList<>();
        int l = 0,r = matrix[0].length - 1, t = 0, b = matrix.length - 1,x = 0;
        List<Integer> res = new ArrayList<>();
        while (true){
            for (int i = l; i <= r; i ++)  res.add(matrix[t][i]);
            if (++t > b) break;
            for (int i = t; i <= b; i ++) res.add(matrix[i][r]);
            if (l > --r) break;
            for (int i = r; i >= l; i --) res.add(matrix[b][i]);
            if (t > --b) break;
            for (int i = b; i >= t; i --) res.add(matrix[i][l]);
            if (++l > r) break;
        }
        return res;
    }

}
