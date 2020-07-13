package DFS;

/*
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

         

        示例 1：

        输入：m = 2, n = 3, k = 1
        输出：3
        示例 2：

        输入：m = 3, n = 1, k = 0
        输出：1
        提示：

        1 <= n,m <= 100
        0 <= k <= 20

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

public class _剑指_Offer_13_机器人的运动范围 {

    public int movingCount(int m, int n, int k) {

        boolean[][] lists = new boolean[m][n];
        return dfs(0,0, m, n, k, lists);
    }

    public int dfs(int i, int j, int m, int n, int k, boolean[][] lists){
        if (i < 0 || j < 0 || i >= m || j >= n || sum(i) + sum(j) > k || lists[i][j] == true){
            return 0;
        }

        lists[i][j] = true;

        return 1 + dfs(i - 1, j, m, n, k, lists)
                + dfs(i + 1, j, m, n , k, lists)
                + dfs(i, j - 1, m, n, k, lists)
                + dfs(i , j + 1, m, n, k, lists);
    }

    public int sum(int x){
        if(x < 10) return x;
        int res = 0;
        while (x > 0){
            res += x%10;
            x /= 10;
        }
        return res;
    }


}
