package 栈;

import java.util.Stack;

/*
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

        push(x) —— 将元素 x 推入栈中。
        pop() —— 删除栈顶的元素。
        top() —— 获取栈顶元素。
        getMin() —— 检索栈中的最小元素。
        示例:

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   --> 返回 -3.
        minStack.pop();
        minStack.top();      --> 返回 0.
        minStack.getMin();   --> 返回 -2.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/min-stack
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _155_最小栈 {

    // 数据栈
    private Stack<Integer> stack;
    // 辅助栈
    private Stack<Integer> minStack;
    public _155_最小栈() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        // 数据栈直接入栈
        stack.push(x);
        // 当辅助栈栈顶 >= x时，直接入栈。 否则再次入栈栈顶元素
        if (minStack.isEmpty() || minStack.peek() >= x){
            minStack.push(x);
        }else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        // 两个栈都 pop
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
