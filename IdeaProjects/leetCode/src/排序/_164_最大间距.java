package 排序;

import java.util.Arrays;

/**
给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

        如果数组元素个数小于 2，则返回 0。

        示例 1:

        输入: [3,6,9,1]
        输出: 3
        解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
        示例 2:

        输入: [10]
        输出: 0
        解释: 数组元素个数小于 2，因此返回 0。
        说明:

        你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
        请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-gap
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _164_最大间距 {

    /**
     * 基于系统的快排
     * 时间复杂度 : O(N * logN)
     * 空间复杂度 : O(1)
     * */
    public int maximumGap1(int[] nums) {
        if (nums.length <= 1) return 0;
        Arrays.sort(nums);
        int diff = nums[1] - nums[0];
        for (int i = 2; i < nums.length; i++) diff = Math.min(diff, nums[i]- nums[i - 1]);
        return diff;
    }

    /**
     * 但是题目要求
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * 所以使用 基数排序 / 桶排序
     * */
}
