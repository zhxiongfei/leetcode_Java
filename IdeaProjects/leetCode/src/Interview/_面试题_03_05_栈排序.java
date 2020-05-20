package Interview;

/*
栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，
但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。
当栈为空时，peek 返回 -1。

        示例1:

        输入：
        ["SortedStack", "push", "push", "peek", "pop", "peek"]
        [[], [1], [2], [], [], []]
        输出：
        [null,null,null,1,null,2]
        示例2:

        输入：
        ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
        [[], [], [], [1], [], []]
        输出：
        [null,null,null,null,null,true]
        说明:

        栈中的元素数目在[0, 5000]范围内。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sort-of-stacks-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _面试题_03_05_栈排序 {

    // 正常栈
    private Stack<Integer> stack;
    // 缓存栈
    private Stack<Integer> buffer;

    public _面试题_03_05_栈排序() {

        stack = new Stack<>();
        buffer = new Stack<>();
    }

    // stack中，是有序栈，即从栈顶到栈底是升序的
    // push时，依然要保持有序
    // 如果val比栈顶元素大
    // 则 while循环依次将 stack中的数据pop 到 辅助栈中
    // 之后把当前元素 val 入stack
    // 然后把 辅助栈的元素依次入stack
    public void push1(int val) {
        while (!stack.isEmpty() && val > stack.peek()) {
            buffer.push(stack.pop());
        }

        stack.push(val);

        while (!buffer.isEmpty()){
            stack.push(buffer.pop());
        }
    }

    // pop没有特殊的，如果stack不为空就pop
    public void pop1() {
        if (!stack.isEmpty()) stack.pop();
    }

    // peek 不为空则返回peek(), 为空则返回 -1
    public int peek1() {
        if (stack.isEmpty()) return -1;
        return stack.peek();
    }

    // stack是否为空
    public boolean isEmpty1() {
        return stack.isEmpty();
    }

    // 优化：
    // https://tva1.sinaimg.cn/large/007S8ZIlly1gexlwu1mb4j30r204qaam.jpg
    // 要保证，主栈 和 辅助栈都是有序的
    // 且 主栈 从栈底到栈顶，降序
    // 辅助栈 从栈底到栈顶，升序

    // 新加入元素时，如果 > 主栈栈顶， 则将小于 val 的元素，都pop到辅助栈中
    // 如果val < 辅助栈最小元素， 则将辅助栈中 大于 val 的元素，都pop到主栈中
    // 如果val正好 < 主栈栈顶 ， 且 > 辅助栈栈顶，则入主栈(辅助栈亦可)
    // 考虑到，pop(), peek() 操作时，还需要将辅助栈元素， pop回主栈，所以最好 入主栈
    public void push(int val){
        int max = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
        int min = buffer.isEmpty() ? Integer.MIN_VALUE : buffer.peek();
        while (true){
            if (val > max){
                buffer.push(stack.pop());
                max = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
            }else if (val < min){
                stack.push(buffer.pop());
                min = buffer.isEmpty() ? Integer.MIN_VALUE : buffer.peek();
            }else{
                stack.push(val);
                break;
            }
        }
    }

    public void pop(){
        while (!buffer.isEmpty()){
            stack.push(buffer.pop());
        }

        if (!stack.isEmpty()) stack.pop();
    }

    public int peek(){
        while (!buffer.isEmpty()){
            stack.push(buffer.pop());
        }
        if (stack.isEmpty()) return -1;
        return stack.peek();
    }

    public boolean isEmpty(){
        return buffer.isEmpty() && stack.isEmpty();
    }

}
