package 动态规划;

/**
给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵
 找出只包含 1 的最大矩形，并返回其面积。
*/

import java.util.ArrayDeque;
import java.util.Deque;

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
        // 遍历每一层,记录每一层的最大矩形
        // 每一层的就变为，84题的柱状图的最大矩形

        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1'){
                    heights[j] += 1;
                }else {
                    heights[j] = 0;
                }
            }
            int area = largestRectangleArea(heights);
            res = Math.max(res, area);
        }
        return res;
    }

    private int largestRectangleArea(int[] heights) {
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights,0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<Integer>();
        int res = 0;
        for (int i = 0; i < tmp.length; i++) {
            while (!stack.isEmpty() && tmp[stack.peek()] > tmp[i]){
                int height = tmp[stack.pop()];
                int l = stack.peek();
                int r = i;
                int area = height * (r - l - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        _85_最大矩形 cls = new _85_最大矩形();

        /**
        {'1','0','1','0','0'}
        {'1','0','1','1','1'}
        {'1','0','0','1','0'}
        */

        char[][]matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(cls.maximalRectangle(matrix));
    }
}
