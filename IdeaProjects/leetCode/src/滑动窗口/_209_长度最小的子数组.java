package 滑动窗口;

/*
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。
        如果不存在符合条件的连续子数组，返回 0。

        示例: 

        输入: s = 7, nums = [2,3,1,2,4,3]
        输出: 2
        解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
        进阶:

        如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 */

public class _209_长度最小的子数组 {

    /**
     *
     * 滑动窗口
     *
     * */
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // sum 存放窗口中 数值总和
        // left 为窗口最左边
        // right 为窗口右边第一个元素
        // res 存放最终结果
        int sum = nums[0], left = 0, right = 1, res = Integer.MAX_VALUE;
        while (right < nums.length || left < nums.length){
            if (sum < s){
                // 当 sum < s时， 窗口向右扩大
                if (right == nums.length)
                    sum -= nums[left ++];
                else
                    sum += nums[right++];
            }else {
                // 当 sum >= s时，计算 res
                // 左边窗口缩小
                res = Math.min(res, right - left);
                sum -= nums[left ++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        int[]nums = {2,3,1,2,4,3};
        minSubArrayLen(7, nums);
    }
}
