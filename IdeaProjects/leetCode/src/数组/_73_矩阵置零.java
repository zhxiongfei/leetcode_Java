package 数组;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

        示例 1:

        输入:
        [
          [1,1,1],
          [1,0,1],
          [1,1,1]
        ]
        输出:
        [
          [1,0,1],
          [0,0,0],
          [1,0,1]
        ]
        示例 2:

        输入:
        [
          [0,1,2,0],
          [3,4,5,2],
          [1,3,1,5]
        ]
        输出:
        [
          [0,0,0,0],
          [0,4,5,0],
          [0,3,1,0]
        ]
        进阶:

        一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
        一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
        你能想出一个常数空间的解决方案吗？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/set-matrix-zeroes
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _73_矩阵置零 {

    /**
     *
     * 额外的存储空间
     * 空间复杂度 : O(m + n)
     * 时间复杂度 : O(m * n)
     *
     * 思路 :
     * 1, 遍历一遍矩阵， 记录为0的 行 和 列
     * 2, 再次遍历矩阵， 当保存的行 或 列信息， 包含当前遍历的行 或 列信息时
     * 3, 将当前位置的元素置为0
     *
     * */
    public void setZeroes2(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer>cols = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int num = matrix[i][j];
                if (num == 0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rows.contains(i) || cols.contains(j))
                    matrix[i][j] = 0;
            }
        }
    }

    /**
     * 空间复杂度 O(1) 的暴力解法
     * 遍历矩阵，当遍历元素为0时
     * 把该元素同行同列置为 0
     *
     * 时间复杂度 : O(m * n) * (m * n)
     * 空间复杂度 : O(1)
     * */
    public void setZeroes1(int[][] matrix) {
        
    }

    /**
     * 巧妙解法
     * 时间复杂度 : O(m * n)
     * 空间复杂度 : O(1)
     *
     * 解法一需要额外的 存储空间存储需要置为0的行 和 列
     * 此解法很巧妙的将需要置为0的行 和 列存储在 原矩阵的0行 或者 0列
     * 不需要额外的存储空间
     *
     * 1, 用 rowFlag 和 colFlag 分别记录 第 0 行 和 第 0 列是否置 0
     * 2, 二次遍历中不可以修改首行 和 首列， 因为会影响其他数据
     * 3, 遍历矩阵，将 matrix[i][j] == 0的位置的。 第0行，第0列置为0
     * 4, 用 rowFlag 和 colFlag来判断， 首行或者首列是否置0
     *
     * */
    public static void setZeroes(int[][] matrix) {
        boolean rowFlag = false;
        boolean colFlag = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int num = matrix[i][j];
                if (num == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0) rowFlag = true;
                    if (j == 0) colFlag = true;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (rowFlag){
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (colFlag){
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][0] = 0;
            }
        }
        System.out.println(matrix);
    }

    public static void main(String[] args) {

//        int[][]nums = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        int[][]nums = {{1,0,3}};
        setZeroes(nums);
    }
}
