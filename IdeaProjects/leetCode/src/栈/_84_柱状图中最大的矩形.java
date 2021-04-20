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
        // 头尾增加两个0， 是为了保证柱体左右一定有比其小柱体, 方便统一处理
        // 两个0也不会对结果产生影响
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights,0, tmp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<Integer>();
        int res = 0;

        for (int i = 0; i < tmp.length; i++) {
            while (!stack.isEmpty() && tmp[stack.peek()] > tmp[i]){
                // 当tmp[i] < tmp[stack.peek()]时
                // 就找到了 栈顶 柱体的右边界 (右边比自己小的第一个元素)
                // 左边界为 栈 的下一个元素 (左边比自己小的第一个元素)
                // 就可以计算出 area

                // 柱体高度
                int height = tmp[stack.pop()];
                // 柱体左边界
                int l = stack.peek();
                // 柱体右边界
                int r = i;
                int area = height * (r - l - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        _84_柱状图中最大的矩形 cls = new _84_柱状图中最大的矩形();
        int[]heights = {2,1,5,6,2,3};
        System.out.println(cls.largestRectangleArea(heights));
    }
}
