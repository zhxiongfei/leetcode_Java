package 数组;

/**
给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

        示例:

        输入: 3
        输出:
        [
        [ 1, 2, 3 ],
        [ 8, 9, 4 ],
        [ 7, 6, 5 ]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/spiral-matrix-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _59_螺旋矩阵_II {

    public int[][] generateMatrix(int n) {
        int [][] res = new int[n][n];

        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int size = 1, length = n * n;
        while (size <= length){
            // 从左到右
            for (int i = left; i <= right; i++) res[top][i] = size ++;
            top ++;

            // 从上到下
            for (int i = top; i <= bottom; i++) res[i][right] = size ++;
            right --;

            // 从右到左
            for (int i = right; i >= left; i--) res[bottom][i] = size ++;
            bottom --;

            // 从下到上
            for (int i = bottom; i >= top; i--) res[i][left] = size ++;
            left ++;
        }
        return res;
    }

}
