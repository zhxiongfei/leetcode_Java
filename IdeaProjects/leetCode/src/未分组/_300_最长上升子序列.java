package 未分组;

/*
给定一个无序的整数数组，找到其中最长上升子序列的长度。

        示例:

        输入: [10,9,2,5,3,7,101,18]
        输出: 4
        解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
        说明:

        可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
        你算法的时间复杂度应该为 O(n2) 。
        进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _300_最长上升子序列 {

    public static int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        // 定义状态 dp[i]代表以 第i个元素结尾的序列 的最长上升子序列
        int[] dp = new int[nums.length];

        // 定义初始状态 第一位时，最长上升子序列为1
        dp[0] = 1;

        int max = 1;
        // 定义状态转移方程
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i],dp[j] + 1);
            }

            max = Math.max(dp[i],max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }

}
