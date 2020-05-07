package 未分组;

/*
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

        说明：每次只能向下或者向右移动一步。

        示例:

        输入:
        [
        [1,3,1],
        [1,5,1],
        [4,2,1]
        ]
        输出: 7
        解释: 因为路径 1→3→1→1→1 的总和最小。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-path-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.awt.desktop.ScreenSleepEvent;
import java.util.EventListener;

public class _64_最小路径和 {

    static int minPathSum(int[][] grid) {
        // 空
        if(grid == null || grid.length == 0) return 0;
        // m = 1;
        if(grid.length == 1) {
            int res = 0;
            for (int i = 0; i < grid[0].length; i++) {
                res += grid[0][i];
            }
            return res;
        }
        // n = 1;
        if(grid[0].length == 1) {
            int res = 0;
            for (int i = 0; i < grid.length; i++) {
                res += grid[0][i];
            }
            return res;
        }

        int[][]dp = new int[grid.length+1][grid[0].length+1];

        for(int i = 1; i <= grid.length; i++){

            for(int j = 1; j <= grid[0].length; j++){
                if (i == 1){
                    dp[i][j] = grid[i-1][j-1] + dp[i][j-1];
                    continue;
                }

                if (j == 1){
                    dp[i][1] = grid[i-1][0] + dp[i-1][1];
                    continue;
                }

                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i-1][j-1];

                System.out.println(dp);
            }
        }

        return dp[grid.length][grid[0].length];
    }

    public static void main(String[] args){

        int[][] arr = {{1,3,1},{1,5,1},{4,2,1}};
        minPathSum(arr);
    }
}
