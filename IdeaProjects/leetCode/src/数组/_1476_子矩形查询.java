package 数组;

import java.util.Arrays;

public class _1476_子矩形查询 {

    int[][] rect;
    public _1476_子矩形查询(int[][] rectangle) {
        rect = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++){

//            for (int j = col1; j <= col2; j++){
//                rect[i][j] = newValue;
//            }

            Arrays.fill(rect[i], col1, col2 + 1, newValue);
        }
    }

    public int getValue(int row, int col) {
        return rect[row][col];
    }

}
