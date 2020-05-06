package 动态规划;

/*
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。



        在杨辉三角中，每个数是它左上方和右上方的数的和。

        示例:

        输入: 3
        输出: [1,3,3,1]

        [
            [1],
           [1,1],
          [1,2,1],
         [1,3,3,1],
        [1,4,6,4,1]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/pascals-triangle-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

public class _119_杨辉三角II {
    
    static List<Integer> getRow(int rowIndex) {
        
        List<Integer> list = new ArrayList<Integer>();
        rowIndex += 1;

        int[][] dp = new int[rowIndex + 1][rowIndex + 1];
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }

                if (i == rowIndex){
                    list.add(dp[i][j]);
                }
            }
        }

        return list;
    }

    public static void main(String[] args){
        getRow(3);
    }
}
