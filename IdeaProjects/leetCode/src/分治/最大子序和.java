package 分治;

/*
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

        示例:

        输入: [-2,1,-3,4,-1,2,1,-5,4],
        输出: 6
        解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-subarray
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 最大子序和 {

    // 分治 O(nlog(n))
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return maxSubArray(nums,0,nums.length);
    }

    // 求[begain,end) 之间的最大子序列和
    public int maxSubArray(int[] nums,int begain,int end){

        if (end - begain < 2) return nums[begain];

        int mid = (begain + end) >> 1;

        // mid左边的最大和。
        int left = maxSubArray(nums,begain,mid);

        // mid右边的最大和。
        int right = maxSubArray(nums,mid,end);

// [-2,1,-3,4,-1,2,1,-5,4]

        // 包含mid的最大和
        // 包含mid左边最大和
        int leftMax = nums[mid - 1];
        int leftSum = leftMax;
        for (int i = mid - 2; i >= begain; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax,leftSum);
        }

        int rightMax = nums[mid];
        int rightSum = rightMax;
        for (int i = mid + 1; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax,rightSum);
        }

        return Math.max(leftMax + rightMax,Math.max(left,right));
    }
}
