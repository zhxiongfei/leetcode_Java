package 动态规划;

public class _746_使用最小花费爬楼梯 {

    /**
     *
     * 动态规划
     * 三步走 :
     * 1, 定义 dp 数组含义 : dp[i] 表示 到底 第 i 个节点的最小花费
     * 2, 定义初始值 , 初始到达 0,1 节点，可以直接到达, 花费为 0。 Java中数组默认为0，不需显性初始化
     * 3, 定义状态转移方程 :
     *    到达 i, 可以从 i - 1跳一步过来, 也可以从 i - 2跳两步过来.
     *    到达 i的花费为这两种情况的最小值 .
     *    dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2][cost i - 2])
     *
     * 最后一步走完全部楼梯,可以从 len - 1走一步走完，也可以 len - 2 走两步走完.
     *
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     *
     * */
    public static int minCostClimbingStairs(int[] cost) {
        // dp[i] 表示到达第 i 个节点 的最小花费
        int[] dp = new int[cost.length];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return Math.min(dp[cost.length - 2] + cost[cost.length - 2], dp[cost.length - 1] + cost[cost.length - 1]);
    }

    /**
     *
     * 空间复杂度的优化
     * 上述解题过程中, 发现解题过程中, dp[i] 只与 dp[i - 1] 和 dp[i - 2] 有关, 而数组的其他元素跟 dp[i] 没有关系
     * 所以, 可以通过记录两个变量来代替数组
     * 使空间复杂度降低至 : O(1)
     * 时间复杂度仍然是 : O(n)
     *
     * */
    public static int minCostClimbingStairs1(int[] cost) {
        // dp[i] 表示到达第 i 个节点 的最小花费
        int fir = 0, sec = 0;
        for (int i = 2; i < cost.length; i++) {
            int tmp = Math.min(fir + cost[i - 2], sec + cost[i - 1]);
            fir = sec;
            sec = tmp;
        }
        return Math.min(fir + cost[cost.length - 2], sec + cost[cost.length - 1]);
    }

    /**
     *
     * 感觉上述解题中, 最后一步比较 和 for循环中的比较是一样的
     * 下边我们尝试把最后一次加法，放在for循环中去
     *
     * */
    public static int minCostClimbingStairs2(int[] cost) {
        // dp[i] 表示经过第 i 个节点 的最小花费
        int fir = 0, sec = 0;
        for (int i = 0; i < cost.length; i++) {
            int tmp = Math.min(fir, sec) + cost[i];
            fir = sec;
            sec = tmp;
        }
        return Math.min(fir,sec);
    }

    public static void main(String[] args) {
//        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1,23,1,2,3,4,2,1,3,4,5,3,2,3,4,5,3,2,3,4,5,3,2,3,4,5,5,3,2};
        int[] cost = {0,0,1};
        int res = minCostClimbingStairs2(cost);
        System.out.println(res);
    }

}
