package Interview;

/*
请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。


        示例：

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   --> 返回 -3.
        minStack.pop();
        minStack.top();      --> 返回 0.
        minStack.getMin();   --> 返回 -2.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/min-stack-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _面试题_03_02_栈的最小值 {

    // 利用两个栈，一个存放正常数据，一个存放最小数据
    // push的时候要注意，如果minStack不为空，minStack要入栈 其栈顶和当前元素中较小的
    // getMin时，直接返回minStack的栈顶元素即可
    Stack<Integer> normalStack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public _面试题_03_02_栈的最小值() {

        normalStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        normalStack.push(x);
        if (minStack.isEmpty()){
            minStack.push(x);
        }else {
            minStack.push(Math.min(minStack.peek(),x));
        }
    }

    public void pop() {
        normalStack.pop();
        minStack.pop();
    }

    public int top() {
        return normalStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
