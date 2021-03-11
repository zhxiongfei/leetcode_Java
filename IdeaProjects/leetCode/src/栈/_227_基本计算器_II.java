package 栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
        整数除法仅保留整数部分。
        示例 1：
        输入：s = "3+2*2"
        输出：7
        示例 2：

        输入：s = " 3/2 "
        输出：1
        示例 3：

        输入：s = " 3+5 / 2 "
        输出：5
         

        提示：

        1 <= s.length <= 3 * 105
        s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
        s 表示一个 有效表达式
        表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
        题目数据保证答案是一个 32-bit 整数

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/basic-calculator-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _227_基本计算器_II {

    /**
     * 栈
     * 因为乘除 优先于 加减
     * 所以先算乘除，再算加减
     *
     * 最后需要栈反转一遍
     * */
    public int calculate1(String s) {
        Stack<Integer>stack1 = new Stack<>();
        Stack<Character>stack2 = new Stack<>();
        char[] chars = s.toCharArray();
        int i = 0, length = chars.length;
        while (i < length) {
            char c = chars[i];
            if (c == ' ') {
                i++;
                continue;
            }
            if (c == '-' || c == '+' || c == '*' || c == '/') {
                stack2.push(c);
                i++;
                continue;
            }
            // 数字
            int num = 0;
            while (i < length && Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
                i++;
            }

            if (!stack2.isEmpty() && (stack2.peek() == '*' || stack2.peek() == '/')) {
                stack1.push(stack2.pop() == '*' ? stack1.pop() * num : stack1.pop() / num);
            } else {
                stack1.push(num);
            }
        }

        Stack<Integer>tmp1 = new Stack<>();
        while (!stack1.isEmpty()){
            tmp1.push(stack1.pop());
        }

        Stack<Character>tmp2 = new Stack<>();
        while (!stack2.isEmpty()){
            tmp2.push(stack2.pop());
        }

        while (!tmp2.isEmpty()){
            char c = tmp2.pop();
            int i1 = tmp1.pop();
            int i2 = tmp1.pop();
            tmp1.push(c == '+' ? i1 + i2 : i1 - i2);
        }

        return tmp1.peek();
    }

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0 ;
        char ops = '+';
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }
            //  这里 i == s.length() - 1 是因为表达式最后一位一定是整数，必须进入运算，否则丢失最后一位
            if(!Character.isDigit(c) && c != ' ' || i == s.length() - 1){
                // 每次遇到符号位 才将该符号位之前的结果 入栈， 如果上一个符号位是 * / 需要将之前的结果 出栈 再做操作
                switch(ops){
                    case '+' : stack.addLast(num); break;
                    case '-' : stack.addLast(-num); break;
                    case '*' : stack.addLast(stack.removeLast() * num); break;
                    case '/' : stack.addLast(stack.removeLast() / num); break;
                };
                ops = c;
                num = 0;
            }
        }
        int res = 0 , size = stack.size();
        for(int i = 0 ; i < size ; i++){
            res += stack.removeLast();
        }
        return res;
    }

    public static void main(String[] args) {
        _227_基本计算器_II cls = new _227_基本计算器_II();
        System.out.println(cls.calculate("3+2*2"));
    }
}
