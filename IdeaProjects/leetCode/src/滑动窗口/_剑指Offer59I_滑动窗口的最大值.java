package 滑动窗口;

import java.util.Deque;
import java.util.LinkedList;

/**
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
public class _剑指Offer59I_滑动窗口的最大值 {

    /**
     *
     * 双端 递减 队列
     *
     * */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 1) return nums;
        // 结果数组
        int[] res = new int[nums.length - k + 1];

        // 维护单调递减双端队列
        Deque<Integer> deque = new LinkedList<>();

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // while 循环
            // 当队列不为空，且 队尾元素 < num 时
            // 队尾元素出队
            while (!deque.isEmpty() && nums[deque.peekLast()] < num){
                deque.removeLast();
            }

            // 入队新元素
            deque.addLast(i);

            // 判断对头元素下标有效性
            // 无效时，需要移除
            if (isValidIdx(deque.peekFirst(), i, k) == false){
                deque.pollFirst();
            }

            // i - k + 1 为 结果数组下标
            // i - k + 1 >= 0，说明结果数组下标有效了
            // 也就是, 遍历了 k 个元素了
            // 此时开始对结果数组赋值
            if (i - k + 1>= 0) res[i - k + 1] = nums[deque.getFirst()];
        }

        return res;
    }

    /*
    *
    * 计算 targetIdx 是否有限
    * i - k < targetIdx <= i 有效
    *
    * 否则无效，移除
    *
    * */
    public static boolean isValidIdx(int targetIdx, int i, int k){
        return targetIdx > (i - k) && targetIdx <= i;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        maxSlidingWindow(nums, 3);
    }

}
