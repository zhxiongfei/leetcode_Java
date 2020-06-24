package 探索系列.探索头条;

public class 最大子序和 {

    /**
     *
     * 动态规划
     *
     * */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     *
     * 动态规划
     * 空间复杂度的优化
     *
     * */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int current = nums[0], res = current;
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(current+ nums[i], nums[i]);
            res = Math.max(current, res);
        }
        return res;
    }

    /**
     *
     * 分治策略
     * 时间复杂度 : O(N * logN
     * */
    // 左闭右开
    public int maxSubArrayhelpper(int[] nums, int begain, int end){
        if (end - begain < 2) return nums[begain];

        int mid = (begain + end) >> 1;

        // 左边最大子序和
        int left = maxSubArrayhelpper(nums, begain, mid);
        // 右边最大子序和
        int right = maxSubArrayhelpper(nums, mid, end);

        // 包含 mid 的最大自序和
        int rightMax = nums[mid];
        int rightSum = rightMax;
        for (int i = mid + 1; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        int leftMax = nums[mid - 1];
        int leftSum = leftMax;
        for (int i = mid - 2; i >= 0; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        return Math.max(leftMax + rightMax, Math.max(left, right));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArrayhelpper(nums, 0, nums.length);
    }

}
