/*

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

* */

package 栈;

import java.util.HashMap;
import java.util.Stack;

public class _20_有效的括号 {

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

            if ( map.containsKey(c)){
                // 左括号
                stack.push(c);
            }else{
                // 右括号
                if (stack.isEmpty() || map.get(stack.peek()) != c) return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
