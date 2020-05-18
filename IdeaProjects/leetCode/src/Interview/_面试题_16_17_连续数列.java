package Interview;

/*
给定一个整数数组，找出总和最大的连续数列，并返回总和。

        示例：

        输入： [-2,1,-3,4,-1,2,1,-5,4]
        输出： 6
        解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
        进阶：

        如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/contiguous-sequence-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_16_17_连续数列 {

    // 动态规划
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(1)
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;

        // dp数组表示，以第i结尾的连续数列的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 以i结尾的连续数列的最大和，为 两者nums[i] 和 dp[i - 1] + nums[i] 中的较大值
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }


    // 优化空间复杂度
    // 上述题解中看出，dp数组中的元素我们只利用了 dp[i - 1] 也就是上一个元素
    // 所以可以用一个变量 代替数组记录之前的值即可
    // 时间复杂度仍然是 O(N)
    // 空间复杂度优化为 O(1)
    public int maxSubArray1(int[] nums) {
        if (nums.length == 0) return 0;

        // dp数组表示，以第i结尾的连续数列的最大和
        int prev = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 以i结尾的连续数列的最大和，为 两者nums[i] 和 dp[i - 1] + nums[i] 中的较大值
            prev = Math.max(prev + nums[i], nums[i]);
            result = Math.max(result, prev);
        }

        return result;
    }


    // 分治策略
    // 和最大的连续数列，要么在 nums中心的左边，要么在nums中心的右边， 要么包含中心
    // 左右两遍的最大值，由递归求出
    // 包含mid的最大值，分别计算mid左边 和 右边相加得出
    // 时间复杂度 : O(log N)
    // 空间复杂度 : O(1)

    // 左闭右开
    public int maxSubArrayRange(int[] nums, int left , int right){
        if (right - left < 2) return nums[left];

        int mid = (left + right) >> 1;

        // 左边范围的最大值
        int l = maxSubArrayRange(nums, left, mid);

        // 右边范围的最大值
        int r = maxSubArrayRange(nums, mid, right);

        // 包含 mid 元素的最大值
        // 包含mid 左边的最大值
        int leftSum = nums[mid - 1];
        int leftMax = leftSum;
        for (int i = mid - 2; i >= left; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftSum, leftMax);
        }

        // 包含mid 右边的最大值
        int rightSum = nums[mid];
        int rightMax = rightSum;
        for (int i = mid + 1; i < right; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightSum, rightMax);
        }

        return Math.max(Math.max(l,r),leftMax + rightMax);
    }

    public int maxSubArray2(int[] nums) {
        if (nums.length == 0) return 0;
        return maxSubArrayRange(nums, 0, nums.length);
    }
}