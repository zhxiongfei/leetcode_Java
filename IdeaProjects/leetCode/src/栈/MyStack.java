package 栈;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    private Queue<Integer> q1;
    private Queue<Integer> q2;
    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        q2.clear();
        while (!q1.isEmpty()){
            if (q1.size() != 1){
                q2.add(q1.remove());
            }else {
                Queue tmp = q1;
                q1 = q2;
                q2 = tmp;

                return q2.remove();
            }
        }
        return -1;
    }


    /** Get the top element. */
    public int top() {
        q2.clear();
        while (!q1.isEmpty()){
            if (q1.size() != 1){
                q2.add(q1.remove());
            }else {
                q2.add(q1.peek());
                Queue tmp = q1;
                q1 = q2;
                q2 = tmp;

                return q2.peek();
            }
        }
        return -1;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.size() == 0;
    }
}
