package 剑指offer;

/*
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

        示例:

        输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
        输出: [3,3,5,5,6,7]
        解释:

        滑动窗口的位置                最大值
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
        1 [3  -1  -3] 5  3  6  7       3
        1  3 [-1  -3  5] 3  6  7       5
        1  3  -1 [-3  5  3] 6  7       5
        1  3  -1  -3 [5  3  6] 7       6
        1  3  -1  -3  5 [3  6  7]      7
         

        提示：

        你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _剑指Offer59_I滑动窗口的最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) return null;
        int[] res = new int[nums.length - k + 1];

        // left, right 分别为滑动窗口的左右边界 [left, right]
        int left = 0, right = 0;
        while (right < nums.length){



        }


        return res;
    }

}
