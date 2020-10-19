package 字符串;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

        注意：如果对空文本输入退格字符，文本继续为空。

         

        示例 1：

        输入：S = "ab#c", T = "ad#c"
        输出：true
        解释：S 和 T 都会变成 “ac”。
        示例 2：

        输入：S = "ab##", T = "c#d#"
        输出：true
        解释：S 和 T 都会变成 “”。
        示例 3：

        输入：S = "a##c", T = "#a#c"
        输出：true
        解释：S 和 T 都会变成 “c”。
        示例 4：

        输入：S = "a#c", T = "b"
        输出：false
        解释：S 会变成 “c”，但 T 仍然是 “b”。
         

        提示：

        1 <= S.length <= 200
        1 <= T.length <= 200
        S 和 T 只含有小写字母以及字符 '#'。
         

        进阶：

        你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/backspace-string-compare
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _844_比较含退格的字符串 {

    /**
     * 思路一 :
     * 模拟栈
     * 比较转换之后的字符串是否一样
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * */
    List<Character>getFinalCharList(String string){
        List<Character>list = new ArrayList<>();
        for (char c : string.toCharArray()) {
            if (c == '#'){
                if (list.size() > 0) list.remove(list.size() - 1);
            }else {
                list.add(c);
            }
        }
        return list;
    }

    public boolean backspaceCompare1(String S, String T) {

        List<Character>l1 = getFinalCharList(S);
        List<Character>l2 = getFinalCharList(T);
        if (l1.size() != l2.size()) return false;

        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i) != l2.get(i)) return false;
        }
        return true;
    }

    /**
     *
     * 思路二 : 双指针
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     *
     * */
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1, backCnt1 = 0, backCnt2 = 0;

        while (true){
            while (i >= 0){
                if (S.charAt(i) == '#'){
                    backCnt1 ++;
                }else if (backCnt1 > 0) {
                    backCnt1 --;
                }else{
                    break;
                }
                i--;
            }

            while (j >= 0){
                if (T.charAt(j) == '#'){
                    backCnt2 ++;
                }else if (backCnt2 > 0){
                    backCnt2 --;
                }else {
                    break;
                }
                j --;
            }

            if (i < 0 || j < 0) break;
            if (S.charAt(i) != T.charAt(j)) return false;

            i --;
            j --;
        }

        return i==-1 && j==-1;
    }

    public static void main(String[] args) {
        _844_比较含退格的字符串 cls = new _844_比较含退格的字符串();

        String s1 = "y#fo##f";
        String s2 = "y#f#o##f";
        cls.backspaceCompare(s1, s2);
    }

}
