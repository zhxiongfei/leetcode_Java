package 剑指offer;

import java.util.Stack;

/*
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

         

        示例:

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();   --> 返回 -3.
        minStack.pop();
        minStack.top();      --> 返回 0.
        minStack.min();   --> 返回 -2.
         

        提示：

        各函数的调用总次数不超过 20000 次

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
        注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
 */
public class _面试题30_包含min函数的栈 {

    private Stack<Integer> norstack;
    private Stack<Integer> minstack;
    public _面试题30_包含min函数的栈() {
        norstack = new Stack<>();
        minstack = new Stack<>();
    }

    public void push(int x) {
        norstack.push(x);
        if (minstack.isEmpty()){
            minstack.push(x);
        }else {
            minstack.push(Math.min(minstack.peek(), x));
        }
    }

    public void pop() {
        if (norstack.isEmpty()) return;
        norstack.pop();
        minstack.pop();
    }

    public int top() {
        if (norstack.isEmpty()) return -1;
        return norstack.peek();
    }

    public int min() {
        if (minstack.isEmpty()) return -1;

        return minstack.peek();
    }
}

