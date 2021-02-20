package 动态规划;

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

        给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

        示例 1:

        输入: [1,2,3,1]
        输出: 4
        解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
             偷窃到的最高金额 = 1 + 3 = 4 。
        示例 2:

        输入: [2,7,9,3,1]
        输出: 12
        解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
             偷窃到的最高金额 = 2 + 9 + 1 = 12 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/house-robber
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

public class _198_打家劫舍 {

    // 递归 leetcode 超出时间限制
    // 每个房租有两种选择， 偷 或者 不偷
    public int rob1(int[] nums) {

        // 0 1 2 3 4 5
        // sum1 = nums[0] + rob(2,3,4,5)    // 偷   0号 房屋
        // sum2 = rob(1,2,3,4,5)            // 不偷 0号 房屋
        // sum = Math.max(sum1,sum2)

        if (nums == null || nums.length == 0) return 0;
        return rob1(nums,nums.length - 1);
    }

    // 从前往后偷
    private int rob1(int[] nums, int begain){
        if (begain == nums.length - 1) return nums[begain];
        if (begain == nums.length - 2) return Math.max(nums[begain],nums[begain + 1]);

        int sum = Math.max(nums[begain] + rob1(nums,begain+2), rob1(nums,begain + 1));
        return sum;
    }

    // 从后往前偷
    private int rob2(int[] nums, int begain) {
        if (begain == 0) return nums[0];
        if (begain == 1) return Math.max(nums[0], nums[1]);

        int sum = Math.max(nums[begain] + rob2(nums, begain - 2), rob2(nums, begain - 1));
        return sum;
    }

    // 数组缓存
    private int rob3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] array = new int[nums.length];
        array[0] = nums[0];
        array[1] = Math.max(nums[0],nums[1]);

        for (int i = 2; i < nums.length; i++) {
            array[i] = Math.max(nums[i] + array[i - 2], array[i-1]);
        }
        return array[array.length - 1];
    }

    // 动态规划
    private int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // 前一个能偷的最大的
        int prev = nums[0];

        // 当前能偷的最大值
        int cur = Math.max(nums[0], nums[1]);;

        for (int i = 2; i < nums.length; i++) {
            int tmp = nums[i] + prev;
            prev = cur;
            cur = Math.max(prev,tmp);
        }
        return cur;
    }

    /**
     * 进阶
     * 保留要偷的下标
     * */
    public int rob4(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];

        int[] dp = new int[length];
        // robs[i] 为 1表示偷, 0表示不偷
        int[] robs = new int[length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i ++){
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            if (dp[i - 2] + nums[i] >= dp[i - 1]){
                robs[i - 2] = 1;
                robs[i] = 1;
            }else {
                robs[i - 1] = 1;
            }
        }
        return dp[length - 1];
    }
}
