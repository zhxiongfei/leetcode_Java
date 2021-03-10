package 栈;

/*
实现一个基本的计算器来计算一个简单的字符串表达式的值。

        字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。

        示例 1:

        输入: "1 + 1"
        输出: 2
        示例 2:

        输入: " 2-1 + 2 "
        输出: 3
        示例 3:

        输入: "(1+(4+5+2)-3)+(6+8)"
        输出: 23
        说明：

        你可以假设所给定的表达式都是有效的。
        请不要使用内置的库函数 eval。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/basic-calculator
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.Deque;
import java.util.LinkedList;

public class _224_基本计算器 {

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int sign = 1, res = 0, length = s.length(), i = 0;
        while (i < length){
            if (s.charAt(i) == ' '){
                i ++;
            }else if (s.charAt(i) == '+'){
                sign = ops.peek();
                i++;
            }else if (s.charAt(i) == '-'){
                sign = -ops.peek();
                i++;
            }else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            }else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            }else{
                long num = 0;
                while (i < length && Character.isDigit(s.charAt(i))){
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                res += sign * num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        calculate("(1+(4+5+2)-3)+(6+8)");
    }
}
