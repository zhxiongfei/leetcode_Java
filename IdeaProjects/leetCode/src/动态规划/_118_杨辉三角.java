package 动态规划;

/*
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。



        在杨辉三角中，每个数是它左上方和右上方的数的和。

        示例:

        输入: 5
        输出:
        [
            [1],
           [1,1],
          [1,2,1],
         [1,3,3,1],
        [1,4,6,4,1]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/pascals-triangle
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class _118_杨辉三角 {

    // 动态规划
    // 二维数组存储
    static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();

        int[][] dp = new int[numRows+1][numRows+1];
        for (int i = 1; i <= numRows; i++) {
            List<Integer> l = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1){
                    dp[i][j] = 1;
                }else if (j == i){
                    dp[i][j] = 1;
                }else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }

                if (dp[i][j] != 0){
                    l.add(dp[i][j]);
                }
            }

            list.add(l);
        }

        return list;
    }

    // 一维数组 优化内存占用
    static List<List<Integer>> generate1(int numRows) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();

        int[] dp = new int[numRows+1];
        for (int i = 1; i <= numRows; i++) {
            List<Integer> l = new ArrayList<>();
            for (int j = i; j >= 1; j--) {
                if (j == 1){
                    dp[j] = 1;
                }else if (j == i){
                    dp[j] = 1;
                }else {
                    dp[j] = dp[j-1] + dp[j];
                }

                if (dp[j] != 0){
                    l.add(dp[j]);
                }
            }

            list.add(l);
        }

        return list;
    }


    public static void main(String[] args){
        generate1(5);
    }
}
