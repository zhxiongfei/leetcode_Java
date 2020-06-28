package 剑指offer;

/*
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

         

        示例 1：

        输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        输出：true
        解释：我们可以按以下顺序执行：
        push(1), push(2), push(3), push(4), pop() -> 4,
        push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
        示例 2：

        输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        输出：false
        解释：1 不能在 2 之前弹出。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _剑指_Offer_31_栈的压入_弹出序列 {

    /**
     *
     * 模拟压栈，出栈过程
     *
     * 初始化一个 Stack 用于push pop
     * 初始化一个 compare 数组，用于存放模拟 push/pop 的结果
     *
     * 遍历 pushed 数组
     * 逐个压入 stack
     * 当 stack不为空 并且 栈顶元素 和 pop中数组相等时
     * stack 出栈， 且 加入 compare数组中
     *
     * 遍历完毕后，如果 stack 不为空
     * 逐个将 stack.pop() 加入 compare数组中
     *
     * 最终比较 compare数组 和 poped数组
     * 有一位不相同，则 return false
     *
     * 比较完毕，每一位都相同， return true
     *
     *
     * */
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();
        int[] compare = new int[pushed.length];
        int idx = 0;

        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);

            while (!stack.isEmpty() && stack.peek() == popped[idx]){
                compare[idx ++] = stack.pop();
            }
        }

        while (!stack.isEmpty()){
            compare[idx ++] = stack.pop();
        }

        for (int i = 0; i < compare.length; i++) {
            if (compare[i] != popped[i]) return false;
        }

        return true;
    }

    /**
     *
     * 如何不利用 compare 数组，在遍历 pushed 过程中，解决问题呢?
     *
     * 其实跟方法一 原理相同
     * 不同之处在于，我们省去了 compare 数组
     * 直接拿 stack栈顶元素 和 poped 数组比较
     *
     * */
    public boolean validateStackSequences1(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();
        int idx = 0;

        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);

            while (!stack.isEmpty() && stack.peek() == popped[idx]){
                stack.pop();
                idx ++;
            }
        }

        return stack.isEmpty();
    }

}
