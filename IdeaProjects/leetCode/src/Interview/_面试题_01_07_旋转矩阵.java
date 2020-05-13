package Interview;

/*
给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。

        不占用额外内存空间能否做到？

         

        示例 1:

        给定 matrix =
        [
        [1,2,3],
        [4,5,6],
        [7,8,9]
        ],

        原地旋转输入矩阵，使其变为:
        [
        [7,4,1],
        [8,5,2],
        [9,6,3]
        ]
        示例 2:

        给定 matrix =
        [
        [ 5, 1, 9,11],
        [ 2, 4, 8,10],
        [13, 3, 6, 7],
        [15,14,12,16]
        ],

        原地旋转输入矩阵，使其变为:
        [
        [15,13, 2, 5],
        [14, 3, 4, 1],
        [12, 6, 8, 9],
        [16, 7,10,11]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_01_07_旋转矩阵 {


    /*
            0,0 -> 0,3 -> 3,3 -> 3,0 -> 0,0
            0,1 -> 1,3 -> 3,2 -> 2,0 -> 0,1
            0,2 -> 2,3 -> 3,1 -> 1,0 -> 0,2
            0,3 -> 3,3 -> 3,0 -> 0,0 -> 0,3
     */

    public void rotate(int[][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < matrix.length >> 1; i++) {
            for (int j = i; j < matrix.length - 1 - i; j++) {

                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] martix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
                };

        _面试题_01_07_旋转矩阵 cls = new _面试题_01_07_旋转矩阵();
        cls.rotate(martix);

        if (martix != null){

        }
    }
}
