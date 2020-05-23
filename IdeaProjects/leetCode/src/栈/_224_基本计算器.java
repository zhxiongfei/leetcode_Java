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

// 未解决
import java.util.Stack;

public class _224_基本计算器 {

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.replace(" ","");

        // 数字栈
        Stack<Integer> stack1 = new Stack<>();

        // 运算符栈
        Stack<Character> stack2 = new Stack<>();

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                // 是数字
                num = num * 10 + (c - '0');
            }else {
                if (num != 0){
                    stack1.push(num);
                    num = 0;
                }
                if (c == '('){
                    // 左括号
                    stack2.push(c);
                }else if (c == ')'){
                    // 右括号
                    while (stack2.peek() != '('){
                        int n1 = stack1.pop();
                        int n2 = stack1.pop();
                        if (stack2.pop() == '+'){
                            stack1.push(n2 + n1);
                        }else {
                            stack1.push(n2 - n1);
                        }
                    }
                    // 把左括号弹出来
                    stack2.pop();
                }else {
                    // + -
                    while (!stack2.isEmpty()) {
                        if (stack2.peek() == '(') break;

                        int n1 = stack1.pop();
                        int n2 = stack1.pop();

                        char op = stack2.pop();
                        if (op == '+') stack1.push(n2 + n1);
                        if (op == '-') stack1.push(n2 - n1);
                    }
                    // 当前运算符入栈
                    stack2.push(c);
                }
            }
        }
        stack1.push(num);

        while (!stack2.isEmpty()){
            int n1 = stack1.pop();
            int n2 = stack1.pop();
            if (stack2.pop() == '+')
                stack1.push(n2 + n1);
            else
                stack1.push(n2 - n1);
        }
        return stack1.peek();
    }

    public static void main(String[] args) {
        calculate("(1+(4+5+2)-3)+(6+8)");
    }
}
