package Interview;

/*
泰波那契序列 Tn 定义如下： 

        T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2

        给你整数 n，请返回第 n 个泰波那契数 Tn 的值。

         

        示例 1：

        输入：n = 4
        输出：4
        解释：
        T_3 = 0 + 1 + 1 = 2
        T_4 = 1 + 1 + 2 = 4
        示例 2：

        输入：n = 25
        输出：1389537
         

        提示：

        0 <= n <= 37
        答案保证是一个 32 位整数，即 answer <= 2^31 - 1。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _1137_第N个泰波那契数 {

    /**
     *
     * 此题目 与 斐波那契数列解题思路一致
     * 不同之处在于 斐波那契数列是 前两个数之和，此题目为 前三个数之和
     *
     * 方法一 ： 递归
     * 思路简单，华丽丽的超时了
     * 时间复杂度 : O(2 ^ N)
     *
     * */
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        return tribonacci(n-1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    /**
     *
     * 递推
     * 初步动态规划
     *
     * 使用dp数组，记录之前的值。 而计算当前值 为 当前的前三个值的和
     *
     * */
    public int tribonacci1(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }


    /**
     *
     * 空间复杂度的优化
     * 在计算 dp[i] 的 过程中，我们只使用了 dp[i - 1], dp[i - 2], dp[i - 3]
     * 所以我们完全没必要，使用额外的dp数组，空间复杂度 O(N)
     *
     * 可以使用 一个size为3的滚动数组。 优化空间复杂度
     *
     * 本解法直接使用 a, b, c 三个变量记录
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     *
     * */
    public int tribonacci2(int n){
        if (n == 0) return 0;
        if (n <= 2) return 1;

        int a = 0, b = 1, c = 1;
        for (int i = 3; i <= n; i++) {
            int tmp = a + b;
            a = b;
            b = c;
            c += tmp;
        }
        return c;
    }

}
