package Interview;

/*
实现一个MyQueue类，该类用两个栈来实现一个队列。


        示例：

        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        queue.peek();  // 返回 1
        queue.pop();   // 返回 1
        queue.empty(); // 返回 false

        说明：

        你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
        你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
        假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 */

import javax.sound.sampled.Line;
import java.awt.desktop.SystemSleepEvent;
import java.util.LinkedList;
import java.util.Queue;

public class _面试题_03_04_化栈为队 {

    // 利用两个队列 实现栈

    // push时，存入q1

    // pop时，如果q2不空， 则q2 移除队头元素并返回
    // 如果 q2为空， 则将q1的元素依次出队，再入队q2。 最后q2移除队头元素并返回

    // peek ，同pop同理，只是将pop操作 换为 peek即可

    // isEmpty ， 如果q1 和 q2都为空，则为空。 否则不为空

    Queue<Integer> q1;
    Queue<Integer> q2;
    /** Initialize your data structure here. */
    public _面试题_03_04_化栈为队() {

        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        q1.add(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!q2.isEmpty()){
            return q2.remove();
        }

        while (!q1.isEmpty()){
            q2.add(q1.remove());
        }

        return q2.remove();
    }

    /** Get the front element. */
    public int peek() {
        if (!q2.isEmpty()){
            return q2.peek();
        }

        while (!q1.isEmpty()){
            q2.add(q1.remove());
        }

        return  q2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public static void main(String[] args) {
        _面试题_03_04_化栈为队 cls = new _面试题_03_04_化栈为队();
        cls.push(1);
        int res1 = cls.pop();

        cls.push(2);
        cls.push(3);
        cls.push(4);

        int res2 = cls.pop();
        int res3 = cls.pop();
        int res4 = cls.pop();

        cls.push(5);
        int res5 = cls.peek();

        System.out.println(res1);
    }

}
