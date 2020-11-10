package 字符串;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

        示例 1:

        输入: [3, 2, 1]

        输出: 1

        解释: 第三大的数是 1.
        示例 2:

        输入: [1, 2]

        输出: 2

        解释: 第三大的数不存在, 所以返回最大的数 2 .
        示例 3:

        输入: [2, 2, 3, 1]

        输出: 1

        解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
        存在两个值为2的数，它们都排第二。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/third-maximum-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _414_第三大的数 {

    /**
     * 优先级队列
     * 时间复杂度 : O(N * log(3)) = O(N)
     * 空间复杂度 : O(1)
     * */
    public int thirdMax1(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (heap.contains(num)) continue;
            heap.add(num);
            max = Math.max(max, num);
            if (heap.size() > 3) heap.poll();
        }
        return heap.size() < 3 ? max : heap.peek();
    }

    /**
     * 三个变量记录
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * */
    public int thirdMax2(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if (num > max2 && num < max1){
                max3 = max2;
                max2 = num;
            }else if (num > max3 && num < max2){
                max3 = num;
            }
        }
        return max3 == Long.MIN_VALUE ? (int)max1 : (int)max3;
    }

    /**
     * 快排减治思想
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * 但是有重复的测试用例 没有通过
     * */
    public int thirdMax(int[] nums) {
        if (nums.length < 3){
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                max = Math.max(num, max);
            }
            return max;
        }
        if (nums.length == 3){
            int min = Integer.MAX_VALUE;
            for (int num : nums) {
                min = Math.min(num, min);
            }
            return min;
        }
        return thirdMaxHelper(nums, 0, nums.length - 1, 3);
    }

    private int thirdMaxHelper(int[] nums, int begin, int end, int targetIdx){
        int pivotIdx = pivotIndex(nums, begin, end);
        if (pivotIdx == nums.length - targetIdx) return nums[pivotIdx];
        if (pivotIdx > nums.length - targetIdx) return thirdMaxHelper(nums, begin, pivotIdx - 1, targetIdx);
        return thirdMaxHelper(nums, pivotIdx + 1, end, targetIdx);
    }

    private int pivotIndex(int[]nums, int begin, int end){
        int tmp = nums[begin];
        boolean isRight = true;
        while (begin < end){
            if (isRight){
                if (nums[end] > tmp){
                    end --;
                }else {
                    nums[begin ++] = nums[end];
                    isRight = false;
                }
            }else {
                if (nums[begin] < tmp){
                    begin ++;
                }else {
                    nums[end --] = nums[begin];
                    isRight = true;
                }
            }
        }
        nums[begin] = tmp;
        return begin;
    }

    public static void main(String[] args) {
        _414_第三大的数 cls = new _414_第三大的数();
        int[] nums = {2,2,1,3,4};
        int res = cls.thirdMax(nums);
        System.out.println(res);
    }
}
