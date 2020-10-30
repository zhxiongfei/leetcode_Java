package 动态规划;

/**
给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

        示例:

        nums = [1, 2, 3]
        target = 4

        所有可能的组合为：
        (1, 1, 1, 1)
        (1, 1, 2)
        (1, 2, 1)
        (1, 3)
        (2, 1, 1)
        (2, 2)
        (3, 1)

        请注意，顺序不同的序列被视作不同的组合。

        因此输出为 7。
        进阶：
        如果给定的数组中含有负数会怎么样？
        问题会产生什么变化？
        我们需要在题目中添加什么限制来允许负数的出现？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/combination-sum-iv
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _377_组合总和_Ⅳ {

    /**
     * 暴力回溯
     * 超时
     * */
    int res = 0, cur = 0;
    public int combinationSum41(int[] nums, int target) {
        dfs(nums, target);
        return res;
    }

    public void dfs(int[] nums, int target){
        if (cur == target){
            res ++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (cur + num > target) continue;
            cur += num;
            dfs(nums,target);
            cur -= num;
        }
    }

    /**
     * 动态规划 三步走
     * 1, 定义 dp[i] 含义 : dp[i]表示 nums 组成 i 的 组合个数
     * 2, 定义初始值 : dp[nums[0]] = 1
     * 3, 定义状态转移方程 : 遍历nums  dp[i] += dp[i - num];
     *
     * */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            int cur = 0;
            for (int num : nums) {
                if (i >= num) cur += dp[i - num];
            }
            dp[i] = cur;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        _377_组合总和_Ⅳ cls = new _377_组合总和_Ⅳ();
        int[] nums = {1,2,3};
        int res = cls.combinationSum4(nums, 4);
        System.out.println(res);
    }
}
