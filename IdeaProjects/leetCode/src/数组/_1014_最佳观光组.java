package 数组;

/*
给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。

        一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。

        返回一对观光景点能取得的最高分。

         

        示例：

        输入：[8,1,5,2,6]
        输出：11
        解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
         

        提示：

        2 <= A.length <= 50000
        1 <= A[i] <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/best-sightseeing-pair
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _1014_最佳观光组 {

    /**
     *
     * 暴力法
     * 双重循环
     * 计算每一种可能性
     *
     * 时间复杂度 : O(N ^ 2)
     * commit 后， 果然毫无疑问的超时了
     *
     * */
    public int maxScoreSightseeingPair(int[] A) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int num1 = A[i];
            for (int j = i + 1; j < A.length; j++) {
                int num2 = A[j];

                res = Math.max(res, (num1 + num2 + i - j));
            }
        }
        return res;
    }

    /**
     *
     * 时间复杂度的优化
     * 看到此题目第一眼，就感觉可以首页那个dp
     * 下面我们尝试 用 dp 在 o(N) 的时间复杂度内解决此问题
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     * */
    public int maxScoreSightseeingPair1(int[] A) {
        int[] dp = new int[A.length];
        dp[1] = A[0] + A[1] - 1;
        int res = dp[1];

        for (int j = 2; j < A.length; j++) {
            // dp[j] 的最大值， 也就是 包含dp[j - 1]的前边计算来的， 和 不包含 dp[j -1] 前边的值 中的最大值
            // dp[j - 1] - A[j-1] + A[j] -1 为包含 前边的景点, 截止当前景点的 观看组合
            // A[j-1] + A[j] -1 为不包含前边， 也就是 [j-1, j] 的观看组合
            dp[j] = Math.max(dp[j - 1] + A[j] - A[j-1] -1, A[j-1] + A[j] -1);
            res = Math.max(res, dp[j]);
        }
        return res;
    }

    /**
     *
     * dp 空间复杂度的优化
     *
     * 上述题中，我们用到了额外的dp数组
     * 然而，在解题过程中，我们只用到了 dp数组的 j-1项
     * 也就是说, dp[j] 只和 dp[j-1] 有关
     * 所以我们使用一个变量来代替 dp数组
     *
     * 将空间复杂度优化至 O(1)
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * */
    public int maxScoreSightseeingPair2(int[] A) {
        int dp = A[0] + A[1] - 1;
        int res = dp;

        for (int j = 2; j < A.length; j++) {
            // dp[j] 的最大值， 也就是 包含dp[j - 1]的前边计算来的， 和 不包含 dp[j -1] 前边的值 中的最大值
            // dp[j - 1] - A[j-1] + A[j] -1 为包含 前边的景点, 截止当前景点的 观看组合
            // A[j-1] + A[j] -1 为不包含前边， 也就是 [j-1, j] 的观看组合
            dp = Math.max(dp + A[j] - A[j-1] -1, A[j-1] + A[j] -1);
            res = Math.max(res, dp);
        }
        return res;
    }

    /**
     *
     * 时间复杂度优化至 O(N)
     *
     * 官方思路
     * 观光公式 : A[i] + A[j] + i - j  等于  A[i] + i + A[j] - j
     * 而我们在遍历, j时， A[j] - j 是固定的
     * 暴力法存在的问题，是在 A[j] - j固定后，还在用 O(N) 的时间复杂度来计算 A[i] + i
     *
     * 而 A[i] + i, 我们可以维护一个变量来记录扫描以来的最大值，这时我们去取出 A[i] + i的最大值时
     * 时间复杂度 就为 O(1)
     * 算法的整体时间复杂度 为 O(N)
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     *
     * */
    public int maxScoreSightseeingPair3(int[] A) {
        int res = 0;
        int maxi = A[0] + 0;
        for (int j = 1; j < A.length; j++) {
            maxi = Math.max(maxi, A[j] + j);
            res = Math.max(res, maxi + A[j] - j);
        }
        return res;
    }


}
