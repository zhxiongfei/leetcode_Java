package 剑指offer;

/*
把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

         

        你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

         

        示例 1:

        输入: 1
        输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
        示例 2:

        输入: 2
        输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题60_n个骰子的点数 {

    // 动态规划
    public static double[] twoSum(int n) {

        // dp[i][j] 就表示 投掷i次骰子后，点数j出现的次数
        int[][] dp = new int[n + 1][6 * n + 1];

        // 第一阶段 ： 一枚骰子, 出现1...6的次数 都是1
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        // 外层循环表示 i 个骰子
        for (int i = 2; i <= n; i++) {
            // 内层循环表示总点数。i个骰子总点数至少为i，所以循环从i开始. i个骰子最大全是6 * i, 所以最大到 6 * i
            for (int j = i; j <= 6 * i; j++) {

                // dp[i][j] 表示投掷 i 个骰子， 投掷出 j 点数的组合个数
                // 而 i 个骰子点数 j, 可以由 i - 1个骰子，再加上一个骰子得来
                // dp[i][j] = dp[i - 1][j - k] + dp[i - 1][j - 2] + ... + dp[i - 1][j - 6]
                // 换成for循环， 则如下代码

                for (int k = 1; k <= 6; k++) {

                    // k >= j时，表示 没有 dp[i - 1] + 1...6可组成 dp[i][j]. 直接break 退出循环
                    if (k >= j) break;

                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        double[] result = new double[5 * n + 1];
        int index = 0;
        for (int i = n; i < dp[n].length; i++) {
            result[index ++] = dp[n][i] / Math.pow(6, n);
        }
        return result;
    }

    // 空间复杂度的优化
    // 以上题解我们看出
    // 计算 i 个骰子投掷出的点数， 只能由 i - 1个骰子投出的点数，再加上当前骰子的1...6某个值确定
    // 我们只用到了上一次投掷骰子的总点数
    // 所以可以优化为一维数组来存储旧值
    public static double[] twoSum1(int n) {

        // dp[i] n个骰子，点数 i 出现的次数
        int[] dp = new int[6 * n + 1];

        // 第一阶段 ： 一枚骰子, 出现1...6的次数 都是1
        for (int i = 1; i <= 6; i++) {
            dp[i] = 1;
        }

        // 外层循环表示 i 个骰子
        for (int i = 2; i <= n; i++) {
            // 内层循环表示总点数。i个骰子总点数至少为i，所以循环从i开始. i个骰子最大全是6 * i, 所以最大到 6 * i
            for (int j = 6 * i; j >= i; j--) {
                dp[j] = 0;

                // dp[i][j] 表示投掷 i 个骰子， 投掷出 j 点数的组合个数
                // 而 i 个骰子点数 j, 可以由 i - 1个骰子，再加上一个骰子得来
                // dp[i][j] = dp[i - 1][j - k] + dp[i - 1][j - 2] + ... + dp[i - 1][j - 6]
                // 换成for循环， 则如下代码

                for (int k = 1; k <= 6; k++) {

                    // k >= j时，表示 没有 dp[i - 1] + 1...6可组成 dp[i][j]. 直接break 退出循环
                    if (j - k < i-1) break;

                    dp[j] += dp[j - k];
                }
            }
        }

        double[] result = new double[5 * n + 1];
        int index = 0;
        for (int i = n; i < dp.length; i++) {
            result[index ++] = dp[i] / Math.pow(6, n);
        }
        return result;
    }

    public static void main(String[] args) {
        twoSum1(3);
    }
}
