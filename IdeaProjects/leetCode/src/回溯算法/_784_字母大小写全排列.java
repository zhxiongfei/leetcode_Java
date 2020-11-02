package 回溯算法;

import javax.swing.*;
import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.List;

/**
给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
        示例：
        输入：S = "a1b2"
        输出：["a1b2", "a1B2", "A1b2", "A1B2"]

        输入：S = "3z4"
        输出：["3z4", "3Z4"]

        输入：S = "12345"
        输出：["12345"]
         

        提示：

        S 的长度不超过12。
        S 仅由数字和字母组成。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/letter-case-permutation
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _784_字母大小写全排列 {

    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;

        char[] chars = S.toCharArray();
        dfs(chars, 0, res);
        return res;
    }

    public void dfs(char[] chars, int index, List<String>res){
        if (index == chars.length){
            res.add(new String(chars));
            return;
        }

        dfs(chars, index + 1, res);

        if (Character.isLetter(chars[index])){
            chars[index] ^= 1 << 5;
            dfs(chars, index + 1, res);
        }
    }

    public static void main(String[] args) {
        _784_字母大小写全排列 cls = new _784_字母大小写全排列();
        String s = "a1b3";
        cls.letterCasePermutation(s);
    }
}
