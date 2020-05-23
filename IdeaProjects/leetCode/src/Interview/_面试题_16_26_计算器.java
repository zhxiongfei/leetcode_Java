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

    /*
    *
    * 思路：
    * 用 prev 数字前的 符号
    * 遇到 + 就将数字入栈
    * 遇到 - 就将 数字取 负数入栈
    *
    * 遇到 * / 就将栈顶pop 与 数字计算后，入栈
    *
    * 则一遍循环以后， 遍历栈中的元素，依次相加，即为最终结果
    *
    * 需要注意的点是： 数字有可能占好几位数，比如123+234;
    * 需要记录变量num, 当前一个是数字时，遍历到当前元素，则 num = num * 10 + c - '0'
    *
    * 下次遍历到符号时，把num置为0
    * */
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.replace(" ","");

        Stack<Integer> stack = new Stack<>();
        char prev = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                num = num * 10 + (int)(c - '0');
            }
            if (!Character.isDigit(c) || i == s.length() - 1){
                // 是数字
                switch (prev){
                    case '+' : stack.push(num);
                        break;
                    case '-' : stack.push(-num);
                        break;
                    case '*' : stack.push(stack.pop() * num);
                        break;
                    case '/' : stack.push(stack.pop() / num);
                        break;
                }
                prev = c;
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }

    public static void main(String[] args) {
        calculate("111*56-1+1");
    }
}
