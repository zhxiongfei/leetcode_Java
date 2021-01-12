
package Interview;

/*
三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。

        示例1:

        输入：n = 3
        输出：4
        说明: 有四种走法
        示例2:

        输入：n = 5
        输出：13

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/three-steps-problem-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_08_01_三步问题 {

    // 思路一：
    // 递归
    // 同斐波那契数列，递归解法存在大量重复的计算
    // 递归解法，提交后，毫无疑问的超出时间限制
    // 时间复杂度为指数级
    public int waysToStep(int n) {
        if (n <= 2) return n;
        if (n == 3) return 4;

        return ((waysToStep(n - 1) + waysToStep(n - 2)) % 1000000007 + waysToStep(n - 3)) % 1000000007;
    }

    // 思路二
    // 动态规划 递推
    // 用dp数组记录，前n - 1阶，需要的步数
    // 第 n 阶，需要的步数就是 dp数组中 n - 1, n - 2, n - 3 的和
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(N)，因为使用了dp数组存储之前结果
    public int waysToStep1(int n) {
        if (n <= 2) return n;
        if (n == 3) return 4;

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;

        for (int i = 3; i < n; i++) {
            dp[i] = ((dp[i - 1] + dp[i - 2]) % 1000000007 + dp[i - 3]) % 1000000007;
        }

        return dp[n - 1];
    }

    // 思路二
    // 动态规划 递推
    // 上个解法看出，貌似不需要使用数组记录之前结果
    // 因为计算的当前结果，用到的知识前三个结果
    // 下边我们尝试使用三个变量记录，优化空间复杂度
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(1)
    public int waysToStep2(int n) {
        if (n <= 2) return n;
        if (n == 3) return 4;

        int a = 1, b = 2, c = 4, result = 0;
        for (int i = 3; i < n; i++) {
            result = ((a + b) % 1000000007 + c) % 1000000007;

            a = b;
            b = c;
            c = result;
        }
        return result;
    }
}
