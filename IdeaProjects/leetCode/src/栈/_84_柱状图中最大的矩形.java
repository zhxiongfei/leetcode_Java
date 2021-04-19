package 栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

        求在该柱状图中，能够勾勒出来的矩形的最大面积。

         



        以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

         



        图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

         

        示例:

        输入: [2,1,5,6,2,3]
        输出: 10

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _84_柱状图中最大的矩形 {

    /**
    这道问题的暴力解法比「接雨水」那道题要其实好想得多：
     可以枚举以每个柱形为高度的最大矩形的面积。
     具体来说就是：依次遍历柱形的高度，对于每一个高度分别向两边扩散，求出以当前高度为矩形的最大宽度多少。
    */
    /**
     * 暴力法
     * 时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(1)
     * 超出时间限制
     * */
    public int largestRectangleArea1(int[] heights) {
        int res = 0, len = heights.length;
        for (int i = 0; i < len; i++) {
            int height = heights[i];

            int left = i, right = i;
            while (left > 0 && heights[left - 1] >= height){
                left --;
            }
            while (right < len-1 && heights[right + 1] >= height){
                right ++;
            }
            res = Math.max(res, (right - left + 1) * height);
        }
        return res;
    }

    /**
     *
     * 单调递增栈
     * 本质上向左向右寻找，比自己小的高度
     * 就是每日问题， next great number 问题
     *
     * 可以实现 O(n) 时间复杂度内解决问题
     *
     * */
    public int largestRectangleArea(int[] heights) {
        // 为了代码方便简单， 用柱体数组的头和尾增加了两个0
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights,0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<Integer>();
        int res = 0;

        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积

            int height = tmp[i];
            while (!stack.isEmpty() && tmp[stack.peek()] > height){
                int h = tmp[stack.pop()];
                System.out.println(((i - stack.peek() - 1) * h));
                res = Math.max(res, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        _84_柱状图中最大的矩形 cls = new _84_柱状图中最大的矩形();
        int[]heights = {2,1,5,6,2,3};
        cls.largestRectangleArea(heights);
    }
}
