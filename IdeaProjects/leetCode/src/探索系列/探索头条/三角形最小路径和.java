package 探索系列.探索头条;

import java.util.ArrayList;
import java.util.List;

/*
           [2],
          [3,4],
         [6,5,7],
        [4,1,8,3]
 */

public class 三角形最小路径和 {

    /**
     *
     * 动态规划
     * 三步走
     * 1，定义含义
     *    dp[i][j]表示， 以 第 i 行， 第 j 列 结尾的最小路径和
     * 2，定义初始值
     *    当 j=0 或者 j==triangle.size - 1时,只能是从0 累加 过来
     * 3, 定义状态转移方程
     *    dp[i][j] 为其 前一行 左，右两个值的较小值 + triangle[i][j]
     *
     * 到最后一行时，记录最小值
     *
     * */
    public static int minimumTotal(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        // dp数组表示 第 i 行， 第 j  列的最小路径和
        int[][]dp = new int[triangle.get(triangle.size() - 1).size()][triangle.size()];

        // 定义初始值
        int min = Integer.MAX_VALUE;
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer>list = triangle.get(i);
            dp[i][0] = dp[i - 1][0] + list.get(0);
            dp[i][list.size() - 1] = dp[i - 1][list.size() - 2] + list.get(list.size() - 1);

            if (i == triangle.size() - 1)
                min = Math.min(min, Math.min(dp[i][0], dp[i][list.size() - 1]));
        }

        // 状态转移方程
        for (int i = 2; i < triangle.size(); i++) {
            List<Integer>list = triangle.get(i);
            for (int j = 1; j < list.size() - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + list.get(j);

                if (i == triangle.size() - 1)
                    min = Math.min(min, dp[i][j]);
            }
        }

        return min;
    }

    /**
     *
     * 我们在计算当前行时
     * 只用到了上一行的值
     * 使用一维数组记录上一行的值即可
     *
     * 下个解法我们尝试将空间复杂度优化之 O(N)
     *
     * */
    public static int minimumTotal1(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        // 定义状态 dp[i] 代表当前行，第 i 列 的最小路径和
        int[] dp = new int[triangle.size() + 1];

        // 初始值
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

    public static void main(String[] args) {

        // [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);

        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);

        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);

        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);

        minimumTotal(lists);
    }
}
