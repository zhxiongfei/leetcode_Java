package 栈;

/*
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

        示例 1:

        输入: [1,2,1]
        输出: [2,-1,2]
        解释: 第一个 1 的下一个更大的数是 2；
        数字 2 找不到下一个更大的数；
        第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
        注意: 输入数组的长度不会超过 10000。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/next-greater-element-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Arrays;
import java.util.Stack;

public class _503_下一个更大元素II {

    /**
     *
     * 此题目是 739。每日温度的进阶版
     * 不同之处在于，本题是循环数组
     *
     * 这里我们先说单调栈的使用，讲清楚单调栈再说看如何解决循环问题
     *
     * 初始化一个栈， 并在添加元素过程中，严格遵守从栈底到栈顶，不递增(每个元素 >= 其上部元素(靠近栈顶的元素))
     *
     * 遍历数组
     *    当栈不为空 并且 当前元素 > 栈顶元素时，我们就找到了比 栈顶元素 大的 元素
     *        这时，需要把结果数组中存储 res[栈顶元素] = 当前元素
     *        并且，将 栈顶元素出栈，继续拿当前元素 和 新的栈顶元素做比较。
     *        直到栈为空 或者 当前元素 <= 栈顶元素
     *    当栈为空 或者 当前元素 <= 栈顶元素时
     *        当前元素直接入栈
     *
     * 处理循环问题
     *     由于这道题的数组是循环的, 我们把数组循环两遍
     *     用 % 模拟循环的问题
     *
     * */

    public static int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];

        // 先将结果数组全部填充为 -1
        Arrays.fill(res, -1);

        // 初始化一个 栈 ，并在添加过程中，维护其单调不递增的性质
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length * 2; i++) {
            int num = nums[i % nums.length];
            // 如果栈不为空 且 当前数字 > 栈顶元素
            // 我们就找到了 第一个比 栈顶元素 大的 数字
            // res结果数组赋值，栈顶元素弹出
            // 当前元素 继续和 新的栈顶元素比较
            while (!stack.isEmpty() && nums[stack.peek()] < num){
                res[stack.pop()] = num;
            }
            // 栈为空 或者 当前元素 <= 栈顶元素时
            // 直接入栈即可
            stack.push(i % nums.length);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        nextGreaterElements(nums);
    }
}


