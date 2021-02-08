package 滑动窗口;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度
        该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
        如果不存在满足条件的子数组，则返回 0 。

        示例 1：
        输入：nums = [8,2,4,7], limit = 4
        输出：2
        解释：所有子数组如下：
        [8] 最大绝对差 |8-8| = 0 <= 4.
        [8,2] 最大绝对差 |8-2| = 6 > 4.
        [8,2,4] 最大绝对差 |8-2| = 6 > 4.
        [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
        [2] 最大绝对差 |2-2| = 0 <= 4.
        [2,4] 最大绝对差 |2-4| = 2 <= 4.
        [2,4,7] 最大绝对差 |2-7| = 5 > 4.
        [4] 最大绝对差 |4-4| = 0 <= 4.
        [4,7] 最大绝对差 |4-7| = 3 <= 4.
        [7] 最大绝对差 |7-7| = 0 <= 4.
        因此，满足题意的最长子数组的长度为 2 。

        示例 2：
        输入：nums = [10,1,2,4,7,2], limit = 5
        输出：4
        解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。

        示例 3：
        输入：nums = [4,2,2,2,4,4,2,2], limit = 0
        输出：3
         
        提示：
        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^9
        0 <= limit <= 10^9

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1438_绝对差不超过限制的最长连续子数组 {

    // [10,1,2,4,7,2], limit = 5
    /**
     * 滑动窗口
     * 记录窗口内的最大值和最小值。
     *    当差值 <= limit时， 窗口扩大
     *    当差值 > limit时, 窗口缩小
     *    ⚠️ 需要注意的是，当窗口缩小时， 需要判断出新窗口的最大最小值。并重新赋值
     *    所以， 整体时间复杂度为 O(N ^ 2)
     * 时间复杂度 : O(n ^ 2)
     * 空间复杂度 : O(1)
     * */
    public int longestSubarray(int[] nums, int limit) {
        int left = 0, right = 1, min = nums[0], max = nums[0], length = nums.length;
        while (right < length){
            int num = nums[right ++];
            if (num < min){
                min = num;
            }else if (num > max){
                max = num;
            }

            if (max - min > limit){
                // 窗口缩小
                if (max == nums[left]) max = getMax(nums,left + 1,right);
                if (min == nums[left]) min = getMin(nums,left + 1,right);;
                left ++;
            }
        }
        return right - left;
    }
    private int getMin(int[]nums, int left, int right){
        int res = Integer.MAX_VALUE;
        for (int i = left; i < right; i ++){
            if (res > nums[i]) res = nums[i];
        }
        return res;
    }
    private int getMax(int[]nums, int left, int right){
        int res = Integer.MIN_VALUE;
        for (int i = left; i < right; i ++){
            if (res < nums[i]) res = nums[i];
        }
        return res;
    }

    /**
     * 滑动窗口 + 单调栈
     * 因为在窗口缩小时， 需要重新获取最大最小值，导致时间复杂度较高
     * 接下来， 用空间换时间的思想。 来提高查找最值的效率
     *
     * 维护两个单调栈
     *
     * */
    public int longestSubarray1(int[] nums, int limit) {
        //这里维护的是最大值们对应的下标
        Deque<Integer> maxQ=new LinkedList<>();
        Deque<Integer> minQ=new LinkedList<>();
        int res = 0, left = 0;
        //窗口左沿
        //窗口右沿
        for (int right = 0; right < nums.length;right++){
            //右沿元素进入窗口、维护最大值单调队列
            while(!maxQ.isEmpty() && nums[maxQ.peekLast()]<nums[right]){
                maxQ.pollLast();
            }
            maxQ.add(right);
            //右沿元素进入窗口、维护最小值单调队列
            while(!minQ.isEmpty() && nums[minQ.peekLast()]>nums[right]){
                minQ.pollLast();
            }
            minQ.add(right);

            //如果当前窗口的最大值最小值的差大于 limit，则不断缩小窗口（左沿++），直至最大值变小或者最小值变大从而满足 limit 限制
            while(!maxQ.isEmpty() && !minQ.isEmpty() && nums[maxQ.peek()]-nums[minQ.peek()]>limit){
                if(maxQ.peek() <= left) maxQ.poll();
                if(minQ.peek() <= left) minQ.poll();
                left ++;
            }
            res = Math.max(res,right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        _1438_绝对差不超过限制的最长连续子数组 cls = new _1438_绝对差不超过限制的最长连续子数组();
        int[] nums = {10,1,4,2,7,2};
        int res = cls.longestSubarray1(nums, 5);
        System.out.println(res);
    }
}
