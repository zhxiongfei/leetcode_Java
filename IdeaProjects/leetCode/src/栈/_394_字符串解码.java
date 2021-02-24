package 栈;

import java.util.*;

/**
给定一个经过编码的字符串，返回它解码后的字符串。
        编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
        你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
        此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

        示例 1：
        输入：s = "3[a]2[bc]"
        输出："aaabcbc"
        示例 2：

        输入：s = "3[a2[c]]"
        输出："accaccacc"
        示例 3：

        输入：s = "2[abc]3[cd]ef"
        输出："abcabccdcdcdef"
        示例 4：

        输入：s = "abc3[cd]xyz"
        输出："abccdcdcdxyz"

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/decode-string
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _394_字符串解码 {

    // "3[a]2[bc]"
    /**
     * 两个栈
     * 一个存储数字
     * 一个存储临时的字符串
     *
     * 遍历字符串
     * 当 c 是数字时，记录在 multi 变量中
     * 当 c 是字符时，记录在 res 中
     * 当 c 是左括号时，当前状态分别入栈，并清空当前状态
     * 当 c 是右括号时，弹出状态， 组合字符串
     *
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     * */
    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer>stack_multi = new LinkedList<>();
        LinkedList<String>stack_res = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '['){
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }else if (c == ']'){
                StringBuilder tmp = new StringBuilder();
                int curMulti = stack_multi.removeLast();
                for (int i = 0; i < curMulti; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }else if (c >= '0' && c <= '9'){
                multi = multi * 10 + Integer.parseInt(c + "");
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }

}
