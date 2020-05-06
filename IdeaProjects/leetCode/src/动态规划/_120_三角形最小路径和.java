package 动态规划;

/*
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

        例如，给定三角形：

        [
        [2],
        [3,4],
        [6,5,7],
        [4,1,8,3]
        ]
        自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/triangle
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;

public class _120_三角形最小路径和 {

    // 动态规划 使用二维数组
    static int minimumTotal1(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        // 定义状态
        // dp[i][j]的含义。
        // dp[i[j] 表示到 第 i 行。 index 为 j 的 数字 的 最小的路径和。
        int[][] dp = new int[triangle.size() + 1][triangle.get(triangle.size() - 1).size() + 1];
        dp[0][0] = 0;
        dp[1][1] = triangle.get(0).get(0);

        int result = Integer.MAX_VALUE;
        for (int i = 2; i <= triangle.size(); i++) {
            List list = triangle.get(i-1);
            for (int j = 1; j <= list.size(); j++) {
                if (j == 1){
                    dp[i][j] = (Integer) list.get(0) + dp[i-1][1];
                }else if (j == list.size()){
                    dp[i][j] = (Integer) list.get(j-1) + dp[i-1][j-1];
                }else {
                    dp[i][j] = (Integer) list.get(j-1) + Math.min(dp[i-1][j-1],dp[i-1][j]);
                }

                if (i == triangle.size()){
                    int tmp = dp[i][j];
                    result = Math.min(result,tmp);
                }
            }
        }

        return result;
    }

    // 优化 ： 使用一维数组  降低空间复杂度
    static int minimumTotal(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        // 定义状态
        // dp[i]的含义。
        // dp[i] 表示到 第 i 个数字的 最小的路径和。
        int[] dp = new int[triangle.get(triangle.size() - 1).size() + 1];
        dp[0] = 0;
        dp[1] = triangle.get(0).get(0);

        int result = Integer.MAX_VALUE;
        for (int i = 2; i <= triangle.size(); i++) {
            List list = triangle.get(i-1);
            for (int j = list.size(); j >= 1; j--) {
                if (j == 1){
                    dp[j] = (Integer) list.get(0) + dp[1];
                }else if (j == list.size()){
                    dp[j] = (Integer) list.get(j-1) + dp[j-1];
                }else {
                    dp[j] = (Integer) list.get(j-1) + Math.min(dp[j-1],dp[j]);
                }

                if (i == triangle.size()){
                    int tmp = dp[j];
                    result = Math.min(result,tmp);
                }
            }
        }

        return result;
    }

    // 有待优化 : 改为自底向上
}
