package 剑指offer;

/*
在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

         

        示例:

        现有矩阵 matrix 如下：

        [
        [1,   4,  7, 11, 15],
        [2,   5,  8, 12, 19],
        [3,   6,  9, 16, 22],
        [10, 13, 14, 17, 24],
        [18, 21, 23, 26, 30]
        ]
        给定 target = 5，返回 true。

        给定 target = 20，返回 false。

         

        限制：

        0 <= n <= 1000

        0 <= m <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _剑指_Offer_04_二维数组中的查找 {

    /**
     *
     * 暴力法
     *
     * 暴力查询每个节点
     * 当 值 == target时,  return true
     * 当 值 != target时,  继续遍历
     *
     * 当矩阵遍历完毕，都没发现与target相同的数字，retur false
     *
     * 时间复杂度 : O(m * n)  m,n分别为矩阵的行数，列数
     * 空间复杂度 : O(1)     没有用到额外的存储空间
     *
     * */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }

    /**
     *
     * 上述解法中，我们完全没用用到矩阵每一行，每一列均递增的性质
     * 如果用到这个性质，在每次遍历时，我们都可以排除数组的部分元素
     *
     * 我们从矩阵的右上角开始遍历, 当前元素用 current 代表
     *    当 target == current时，return true.
     *    当 target > current时，则往下一行查找
     *       因为矩阵从上到下递增，所以当 current < target时，往下找
     *    当 target < current时，则往前一列查找
     *       因为矩阵从左导游递增，所以当 current > target时，往左找
     *
     * 时间复杂度 : O(m + n)
     *    访问到的下标的行最多增加 n 次，列最多减少 m 次，因此循环体最多执行 n + m 次
     *
     * 空间复杂度 : O(1)
     *    没有用到额外的存储空间
     *
     * */
    public boolean findNumberIn2DArray1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int i = 0, j = cols - 1;
        while (i < rows && j >= 0){
            if (matrix[i][j] == target) return true;

            if (matrix[i][j] > target){
                j --;
            }else {
                i ++;
            }
        }
        return false;
    }

}
