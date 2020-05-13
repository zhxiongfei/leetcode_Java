package Interview;

/*
编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。

         

        示例 1：

        输入：
        [
        [1,1,1],
        [1,0,1],
        [1,1,1]
        ]
        输出：
        [
        [1,0,1],
        [0,0,0],
        [1,0,1]
        ]
        示例 2：

        输入：
        [
        [0,1,2,0],
        [3,4,5,2],
        [1,3,1,5]
        ]
        输出：
        [
        [0,0,0,0],
        [0,4,5,0],
        [0,3,1,0]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/zero-matrix-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_01_08_零矩阵 {

    // 开辟两个一维数组
    // line数组 用来存放 需要清零的行
    // column数组 用来存放 需要清零的列
    // 1，遍历矩阵
    // 把需要清空的line 和 column在数组中赋值为 true
    // 2，line数组中需要清空的行，清零
    // 3，column数组中需要清空的列，清零
    public void setZeroes(int[][] matrix) {

        // 需要清空的行数组
        boolean[] line  = new boolean[matrix.length];
        // 需要清空的列数组
        boolean[] column = new boolean[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0){
                    line[i] = true;
                    column[j] = true;
                }
            }
        }

        // 需要清零的行 置为零
        for (int i = 0; i < matrix.length; i++) {
            if (line[i] == true){
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 需要清零的列 置为零
        for (int i = 0; i < matrix[0].length; i++) {
            if (column[i] == true){
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

    }

}
