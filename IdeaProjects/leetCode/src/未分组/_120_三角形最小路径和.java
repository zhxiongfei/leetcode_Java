package 未分组;

/*
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

        例如，给定三角形：

        [
           [2],
          [3,4],
         [6,5,7],
        [4,1,8,3]
        ]

        [
            [2],
           [5,6],
          [13,10,14],
         [17,11,18,17]
        ]

        自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

        说明：

        如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/triangle
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;

public class _120_三角形最小路径和 {

    public static int minimumTotal1(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        // 定义状态 dp[i][j] 代表到第 i 行，第 j 列 的最小路径和
        int[][] dp = new int[triangle.size() + 1][triangle.get(triangle.size() - 1).size() + 1];

        // 初始值
        dp[0][0] = 0;
        dp[1][1] = triangle.get(0).get(0);

        // 状态转移方程
        int result = Integer.MAX_VALUE;
        for (int i = 2; i <= triangle.size(); i++) {
            List <Integer> list = triangle.get(i - 1);

            for (int j = 1; j <= list.size(); j++) {
                if (j == 1){
                    // 最左
                    dp[i][j] = dp[i - 1][1] + list.get(0);
                }else if (j == list.size()){
                    // 最右
                    dp[i][j] = dp[i-1][j-1] + list.get(j - 1);
                }else {
                    // 中间
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + list.get(j - 1);
                }

                if (i == triangle.size()) {
                    result = Math.min(result,dp[i][j]);
                }
            }
        }

        return result;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {

        // 定义状态 dp[i] 代表当前行，第 i 列 的最小路径和
        int[] dp = new int[triangle.size()];

        // 初始值
        dp[0] = 0;
        dp[1] = triangle.get(0).get(0);

        // 状态转移方程
        int result = Integer.MAX_VALUE;
        for (int i = 2; i <= triangle.size(); i++) {
            List <Integer> list = triangle.get(i - 1);

            for (int j = list.size(); j > 0; j--) {
                if (j == 1){
                    // 最左
                    dp[j] = dp[1] + list.get(0);
                }else if (j == list.size()){
                    // 最右
                    dp[j] = dp[j-1] + list.get(j - 1);
                }else {
                    // 中间
                    dp[j] = Math.min(dp[j-1], dp[j]) + list.get(j - 1);
                }

                if (i == triangle.size()) {
                    result = Math.min(result,dp[j]);
                }
            }
        }

        return result;
    }
}
