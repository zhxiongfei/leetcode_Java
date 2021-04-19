package 动态规划;

public class _363_矩形区域不超过K的最大数值和 {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][]topLeft = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j];
                if (i == 0) {
                    topLeft[i][j] = num;
                    if (j > 0) topLeft[i][j] += topLeft[i][j-1];
                    continue;
                }
                if (j == 0){
                    topLeft[i][j] = topLeft[i-1][j] + num;
                    continue;
                }
                topLeft[i][j] = topLeft[i-1][j] + topLeft[i][j-1] - topLeft[i-1][j-1] + num;
            }
        }

        int res = Integer.MIN_VALUE;
        for (int startX = 0; startX < m; startX++) {
            for (int startY = 0; startY < n; startY++) {
                for (int endX = startX; endX < m; endX++) {
                    for (int endY = startY; endY < n; endY++) {

                        int tmp = topLeft[endX][endY] -
                                (startX > 0 ? topLeft[startX-1][endY] : 0) -
                                (startY > 0 ? topLeft[endX][startY-1] : 0)  +
                                (startX > 0 && startY > 0 ? topLeft[startX-1][startY-1] : 0);
                        if (tmp > k) continue;
                        if (tmp == k) return k;
                        if (res < tmp) res = tmp;
                    }
                }
            }
        }
        return res;
    }

    /**
        1,  0, 1, 4
        0, -2, 3, 5
        1,  0, 1, 6
        0, -2, 3, 7
    */
    public static void main(String[] args) {
        _363_矩形区域不超过K的最大数值和 cls = new _363_矩形区域不超过K的最大数值和();
//        int[][]matrix = {{1,0,1,4}, {0,-2,3,5}, {1,0,1,6}, {0,-2,3,7}};
        int[][]matrix = {{1,0,1},{0,-2,3}};
        int res = cls.maxSumSubmatrix(matrix, 3);
//        System.out.println(res);
    }

}
