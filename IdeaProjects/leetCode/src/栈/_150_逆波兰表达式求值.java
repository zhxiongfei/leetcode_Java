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

        Stack <String>stack = new Stack();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (symbols.contains(s)){
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());

                int res = 0;
                if (s.equals("+")){
                    res = num2 + num1;
                }else if (s.equals("-")){
                    res = num2 - num1;
                }else if (s.equals("*")){
                    res = num2 * num1;
                }else if (s.equals("/")){
                    res = num2 / num1;
                }
                stack.push(String.valueOf(res));
            }else{
                // 数字
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.peek());
    }
}
