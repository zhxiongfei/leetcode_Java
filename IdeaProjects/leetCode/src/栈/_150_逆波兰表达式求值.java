package 栈;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class _150_逆波兰表达式求值 {

    public int evalRPN(String[] tokens) {
        final HashSet<String> symbols = new HashSet<String>() {{
            add("+");
            add("-");
            add("*");
            add("/");
        }};
        Stack<String> stack = new Stack<>();
        for (String s : tokens) {
            if (symbols.contains(s)){
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());

                if (s.equals("+")) stack.push(String.valueOf(num2 + num1));
                else if (s.equals("-")) stack.push(String.valueOf(num2 - num1));
                else if (s.equals("*")) stack.push(String.valueOf(num2 * num1));
                else if (s.equals("/")) stack.push(String.valueOf(num2 / num1));
            }else {
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.peek());
    }
}
