package 剑指offer;

/*
一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

        答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

        示例 1：

        输入：n = 2
        输出：2
        示例 2：

        输入：n = 7
        输出：21
        提示：

        0 <= n <= 100
        注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题10_II_青蛙跳台阶问题 {

    /*
    *
    *
    * 这道题跟主站的爬楼梯一模一样
    *
    * 简单的动态规划问题
    *
    * 动态规划三步走
    * 1，定义状态
    *    dp[n] 代表 跳到 第 n 级 的跳法
    * 2，定义初始值
    *    跳一级只有一种条法，跳两级有两种跳法
    *    所以 dp[1] = 1, dp[2] = 2;
    *
    * 3, 状态转移方程
    * 青蛙跳到 第 N级。 可以从跳到 N - 1 级再跳一步而来。 也可以从跳到 N - 2级 再跳两步而来
    *  dp[n] = dp[n - 1] + dp[n - 2]
    *
    * */
    public int numWays(int n) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    /*
    *
    * 上述方法，可以看出，虽然存储了 dp 数组
    * 但是我们计算 dp[n] 时，只用到了 dp[n - 1] 和 dp[n - 2]
    * 所以我们可以对空间复杂度优化
    * 使用两个变量记录即可
    *
    * */
    // 空间复杂度的优化
    public int numWays1(int n) {
        if (n <= 1) return n;

        int first = 1;
        int second = 2;
        for (int i = 2; i < n; i++) {
            int tmp = second;
            second = (first + second) % 1000000007;
            first = tmp;
        }
        return second;
    }

}
