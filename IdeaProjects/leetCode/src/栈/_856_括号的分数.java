package 栈;

/*
*
给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：

() 得 1 分。

AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。

(A) 得 2 * A 分，其中 A 是平衡括号字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/score-of-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

输入： "()"
输出： 1

输入： "(())"            (1)           2 * 1
输出： 2

输入： "()()"            1 + 1         2
输出： 2

输入： "(()(()))"        (1(1))         (12)           (3)         6
输出： 6

提示：

S 是平衡括号字符串，且只含有 ( 和 ) 。
2 <= S.length <= 50

* * */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class _856_括号的分数<E> {

    // 输入： "(()(()))"        (1(1))         (12)           (3)         6
    // 输入： "()()"            1 + 1         2
    // 输入： "(())"            (1)           2 * 1
    public int scoreOfParentheses(String S) {

        Stack <Integer>stack = new Stack();
        int res = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == '('){
                // 左括号
                stack.add(0);
            }else{
                // 右括号
                int cur = 0;
                int count = 0;
                while (stack.peek() != 0) {
                    count++;
                    cur += stack.pop();
                }
                stack.pop();
                if (count == 0)
                    stack.add(1);
                else
                    stack.add(cur << 1);
            }
        }

        while (!stack.isEmpty()){
            Integer integer = (Integer) stack.pop();
            res += integer.intValue();
        }

        return res;
    }

    private static HashMap<Character,Character> map = new HashMap<>();
    static {
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
    }

    public boolean isValid(String s) {

        Stack stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                stack.push(c);
            }else {
                if (stack.isEmpty() || map.get(stack.peek()) != c) return false;

                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
