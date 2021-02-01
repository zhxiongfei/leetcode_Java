package 前缀和问题;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/find-pivot-index/
 * */
public class _724_寻找数组的中心索引 {

    /**
     * 前缀和问题
     * 用 prefixSum 记录前缀和
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * */
    public static int pivotIndex(int[] nums) {
        int total = 0;
        int[]prefixSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            total += num;
            prefixSum[i] = total;
        }
        for (int i = 0; i < prefixSum.length; i++) {
            int num = nums[i];
            if ((total - num == (i < 1 ? 0 : prefixSum[i - 1] << 1)) ) return i;
        }
        return -1;
    }

    /**
     * 空间复杂度优化
     * 使用 sum 记录前缀和
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * */
    public static int pivotIndex1(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if ((total - num == (sum << 1))) return i;
            sum += num;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,-1,-1,0,1,1};
        System.out.println(pivotIndex1(nums));
    }
}
