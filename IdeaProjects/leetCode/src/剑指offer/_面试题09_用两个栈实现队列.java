package 剑指offer;

/*
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

         

        示例 1：

        输入：
        ["CQueue","appendTail","deleteHead","deleteHead"]
        [[],[3],[],[]]
        输出：[null,null,3,-1]
        示例 2：

        输入：
        ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
        [[],[],[5],[2],[],[]]
        输出：[null,-1,null,null,5,2]
        提示：

        1 <= values <= 10000
        最多会对 appendTail、deleteHead 进行 10000 次调用

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _面试题09_用两个栈实现队列 {

    /*
    *
    * 常规题
    *
    * 两个栈 instack 用来push入栈
    * outstack 用来 pop 出栈
    *
    *
    * 入队时
    *   直接入 instack 栈即可
    * 出队时
    *   如果 outstack不为空，则return outstack.pop()
    *   如果 outstack为空， 则把 instack 中的全部数据，压入 outstack中
    *   最终如果 outstack不为空，则返回outstack.pop(). 为空则返回 -1
    *
    * */
    Stack<Integer> instack;
    Stack<Integer> outstack;
    public _面试题09_用两个栈实现队列() {
        instack = new Stack<>();
        outstack = new Stack<>();
    }

    public void appendTail(int value) {
        instack.push(value);
    }

    public int deleteHead() {
        if (!outstack.isEmpty())
            return outstack.pop();
        while (!instack.isEmpty()){
            outstack.push(instack.pop());
        }
        return outstack.isEmpty() ? -1 : outstack.pop();
    }

    // [3,4,5,1,2]
    public int minArray(int[] numbers) {
        int begin = 0, end = numbers.length - 1;
        while (begin < end){
            int mid = begin + ((end - begin) >> 1);
            if (numbers[mid] > numbers[begin]){
                begin = mid + 1;
            }else {
                end = mid - 1;
            }
        }

        return numbers[begin];
    }

    public int translateNum1(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < chars.length; i++) {
            char pre = chars[i - 1];
            char cur = chars[i];

            if (pre > '2' || pre == '0' || (pre == '2' && cur > '5')){
                dp[i] = dp[i - 1];
            }else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[chars.length];
    }

    public int translateNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int a = 1, b = 1;
        for (int i = 2; i < chars.length; i++) {
            char pre = chars[i - 1];
            char cur = chars[i];

            if (pre > '2' || pre == '0' || (pre == '2' && cur > '5')){
                b = a;
            }else {
                int tmp = b;
                b += a;
                a = tmp;
            }
        }
        return b;
    }

}
