package 探索头条;

import java.util.Stack;

public class 最小栈 {

    private Stack<Integer> norStack;
    private Stack<Integer> minStack;

    public 最小栈() {
        norStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        norStack.push(x);

        if (minStack.isEmpty() || minStack.peek() > x){
            minStack.push(x);
        }else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        norStack.pop();
        minStack.pop();
    }

    public int top() {
        return norStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
