package 数组;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
        示例 1:

        输入: [1,2,0]
        输出: 3
        示例 2:

        输入: [3,4,-1,1]
        输出: 2
        示例 3:

        输入: [7,8,9,11,12]
        输出: 1
         

        提示：

        你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/first-missing-positive
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _41_缺失的第一个正数 {

    /**
     * 全排序
     * 时间复杂度 : O(N * logN)
     * 空间复杂度 : O(1)
     * 不符合题目对时间复杂度的要求
     * */
    public static int firstMissingPositive1(int[] nums) {
        if (nums ==  null || nums.length == 0) return 1;
        if (nums.length == 1) return nums[0] == 1 ? 2 : 1;
        Arrays.sort(nums);
        int start = 0;
        while (nums[start] < 0) start++;
        int prev = nums[start];
        if (prev > 1) return 1;
        for (int i = start; i < nums.length; i++) {
            int cur = nums[i];
            if (prev <= 0) {
                if (cur != 0 && cur != 1) return 1;
                prev = cur;
                continue;
            }

            if (cur != prev && cur != prev + 1) return prev + 1;
            prev = cur;
        }
        return prev + 1;
    }

    /**
     * 哈希表存储
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * 空间复杂度不符合要求
     * */
    public static int firstMissingPositive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (!set.contains(i)) return i;
        }
        return len;
    }

    /**
     * 将数组视为哈希表
     *
     * 遍历一遍数组
     * 将元素 i 放到 数组 i-1 的位置
     *
     * 再次遍历数组
     * 找到第1个 i不在i+1位置的元素直接返回 i+1
     *
     * 时间
     *
     * https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
     *
     * */
    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return len + 1;
    }

    private static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,4,3};
        int res = firstMissingPositive(nums);
        System.out.println(res);
    }
}
