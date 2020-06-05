package 剑指offer;

/*
找出数组中重复的数字。


        在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

        示例 1：

        输入：
        [2, 3, 1, 0, 2, 5, 3]
        输出：2 或 3
         

        限制：

        2 <= n <= 100000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;

public class _面试题03_数组中重复的数字 {

    /**
     *
     *
     * 解法一：
     * 遍历数组，使用set存放遍历过的值
     * 遍历时，如果set中包含， 则返回 当前元素
     * 如果不包含，加入set中
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     */

    public int findRepeatNumber1(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return nums[i];
            set.add(nums[i]);
        }
        return -1;
    }

    /**
     *
     * 解法二：
     * 上述题解发现，我们完全没有用到数组中这个条件：
     * 长度为n， 且所有数字都在 [0, n-1]范围内
     *
     * 所以，如果没有重复元素， 则所有元素在排序后，都应该与 下标index一致
     *
     * 那么，空间复杂度O(1),时间复杂度O(n)的解法就来了
     *  1，遍历数组， 当 下标 != 当前元素时，将当前元素交换至正确的位置。
     *  2，交换过程中，如果 当前元素 == 正确位置上的元素， 则重复，return当前元素
     *  3，交换过程中，如果 当前元素 != 正确位置上的元素， 则依次将正确位置上的元素，移动位置
     *
     *
     * */

    // 交换数组两个下标的元素
    public static void swap(int[] nums, int idx1, int idx2){
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }

    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 这里需要注意，一定不要 先取出来 int num = nums[i]
            // 因为 nums[i] 在每一轮 while循环，是变化的。所以每一轮都取
            while (nums[i] != i){
                if (nums[i] == nums[nums[i]]) return nums[i];
                swap(nums,i, nums[i]);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {3, 1, 0, 2, 5, 5};
        findRepeatNumber(nums);
    }
}
