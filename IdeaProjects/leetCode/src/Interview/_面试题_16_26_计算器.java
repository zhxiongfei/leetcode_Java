package Interview;

/*
给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。

        表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

        示例 1:

        输入: "3+2*2"
        输出: 7
        示例 2:

        输入: " 3/2 "
        输出: 1
        示例 3:

        输入: " 3+5 / 2 "
        输出: 5
        说明：

        你可以假设所给定的表达式都是有效的。
        请不要使用内置的库函数 eval。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/calculator-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _面试题_16_26_计算器 {
    public static int calculate(String s) {

        Stack<Integer> numstack = new Stack<>();
        Stack<Character> symbolstack = new Stack<>();

        // 1  表示上一个是运算符'*'
        // -1 表示上一个是运算符'/'
        // 0  表示上一个非 * /
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)){
                // 符号 入 符号栈  且 如果是 * / 需要设置 flag
                symbolstack.push(c);
                if (c == '*') flag = 1;
                if (c == '/') flag = -1;

            }else if (flag == 0){
                // 数字 且 不需要 * /
                // 需要找到下一个位置为符号为止
                numstack.push(c - '0');
            }else {
                // 数字 需要 * 或者 /
                if (flag == 1){
                    numstack.push(numstack.pop() * (c - '0'));
                }else{
                    numstack.push(numstack.pop() / (c - '0'));
                }
                flag = 0;
                symbolstack.pop();
            }
        }

        while (!symbolstack.isEmpty()){
            int n1 = numstack.pop();
            int n2 = numstack.pop();

            if (symbolstack.pop() == '+'){
                numstack.push(n2 + n1);
            }else {
                numstack.push(n2 - n1);
            }
        }

        return numstack.peek();
    }

    public static void main(String[] args) {
        calculate("3+5/2+5*4+6/5+8/1+9*3");
    }
}
