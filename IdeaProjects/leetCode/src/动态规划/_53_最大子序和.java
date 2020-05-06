package 动态规划;

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


public class _53_最大子序和 {

    // 动态规划
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return max1(nums,nums.length - 1);
    }

    // 空间复杂度O(1), 时间复杂度O(n)
    // 下标end的子数组的子序列最大和
    public int max1(int[] nums, int end){
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= end; i++) {
            cur = Math.max(nums[i] + cur,nums[i]);
            max = Math.max(max,cur);
        }
        return max;
    }


    // 暴力法 超出时间限制 O(n ^ 3)
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int max = Integer.MIN_VALUE;
        for (int begain = 0; begain < nums.length; begain++) {
            for (int end = begain; end < nums.length; end++) {

                int sum = 0;
                for (int i = begain; i <= end; i++) {
                    sum += nums[i];
                }

                max = Math.max(sum,max);
            }
        }
        return max;
    }

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

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int resIdx = m + n - 1, left = m - 1,right = n - 1;

        while (left >= 0 || right >= 0){

            if (left < 0){

                while (right >= 0){
                    nums1[resIdx --] = nums2[right --];
                }

                continue;
            }

            if (n < 0) break;

            if (nums1[left] >= nums2[right]){
                nums1[resIdx] = nums1[left];
                m --;
            }else if (nums1[left] < nums2[right]){
                nums1[resIdx] = nums1[right];
                n --;
            }
        }

    }
}
