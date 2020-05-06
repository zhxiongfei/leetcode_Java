package 未分组;

/*
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

        返回滑动窗口中的最大值。

         

        进阶：

        你能在线性时间复杂度内解决此题吗？

         

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

        1 <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
        1 <= k <= nums.length

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sliding-window-maximum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _239_滑动窗口最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return null;
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];

        // 双端队列中，存放 下标
        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {

            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]){
                queue.removeLast();
            }

            queue.addLast(i);

            int w = i - k + 1;
            if (w >= 0){
                if (queue.peekFirst() < w){
                    queue.removeFirst();
                }
                maxes[w] = nums[queue.peekFirst()];
            }
        }
        return maxes;
    }

    public static void main(String[] args) {
        _239_滑动窗口最大值 instance = new _239_滑动窗口最大值();

        int[] nums = {1,3,1,2,0,5};
        instance.maxSlidingWindow(nums,3);
    }
}
