package 剑指offer;

/*
输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

        要求时间复杂度为O(n)。

         

        示例1:

        输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
        输出: 6
        解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
         

        提示：

        1 <= arr.length <= 10^5
        -100 <= arr[i] <= 100
        注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题42_连续子数组的最大和 {

    /*
    *
    * 解法一：
    * 简单动态规划
    *
    * 动态规划步骤三步走
    *   1， 定义dp数组含义。 dp[i] 代表 以[0,i]结尾的子数组的最大和
    *   2， 定义初始值， dp[0] = nums[0]
    *   3,  状态转移方程，dp[n] = Math.max(dp[n] - 1 + nums[n], nums[n])
    *
    * 用一个max变量记录，dp数组的最大值
    * 最终 max 即为最终结果
    *
    * */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /*
    *
    * 优化：
    * 上述题解看出，在计算当前 dp[i] 时，我们只用到了 dp[i - 1]
    * 所以，我们完全可以不使用额外的存储dp存储空间，而是使用变量记录
    *
    * */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int prev = nums[0];
        int max = prev;

        for (int i = 1; i < nums.length; i++) {
            prev = Math.max(nums[i], nums[i] + prev);
            max = Math.max(max, prev);
        }
        return max;
    }


    /**
    *
    * 题目中有说明，如果你实现了 O(N) 时间复杂度的算法，是否可以使用更巧妙的分治算法，解决问题
    *
    * 下边我们尝试使用 分治算法 来解决此问题
    *
    * */

    int [] nums;
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        this.nums = nums;
        return maxSubArr(0, nums.length);
    }

    // 左闭右开 [begain , end)
    public int maxSubArr(int begain, int end){
        // 说明子数组中只有一个元素
        if (end - begain < 2) return nums[begain];

        int mid = (begain + end) >> 1;

        // 左边数组的连续最大和
        int left = maxSubArr(begain, mid);
        // 右边数组的连续最大和
        int right = maxSubArr(mid, end);

        // 包含mid元素的子序列的最大和
        int leftMax = nums[mid - 1];
        int leftSum = leftMax;
        // 往左找
        for (int i = mid - 2; i >= begain; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        // 往右找
        int rightMax = nums[mid];
        int rightSum = rightMax;
        for (int i = mid + 1; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max(Math.max(left,right), leftMax + rightMax);
    }

}
