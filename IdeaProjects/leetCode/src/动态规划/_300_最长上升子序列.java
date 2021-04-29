package 动态规划;

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

import java.util.ArrayList;
import java.util.List;

public class _300_最长上升子序列 {

    // 动态规划
    // 时间复杂度 O(n^2) 空间复杂度O(n)
    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        return max(nums,nums.length - 1);
    }

    // dp[i] 等于 dp[i-1]...dp[0] 比他小的 + 1
    public int max(int[] nums, int end){

        int[] dp = new int[end + 1];
        int max = dp[0] = 1;
        for (int i = 1; i <= end; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i],dp[j] + 1);
            }

            max = Math.max(dp[i],max);
        }
        return max;
    }

    /**
     * 贪心 + 二分查找
     * O(N * logN)
     * */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) return len;

        int[] tail = new int[len];
        tail[0] = nums[0];
        int end = 0;

        for (int i = 1; i < len; i++) {
            if (nums[i] > tail[end]) {
                end++;
                tail[end] = nums[i];
                continue;
            }

            int left = 0;
            int right = end;
            while (left < right) {
                int mid = left + ((right - left) >>> 1);
                if (tail[mid] < nums[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tail[left] = nums[i];
        }
        end++;
        return end;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        _300_最长上升子序列 cls = new _300_最长上升子序列();
        cls.lengthOfLIS(nums);
    }
}
