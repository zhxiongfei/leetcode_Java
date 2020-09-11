package 回溯算法;

import java.util.LinkedList;
import java.util.List;

/**
无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。

        示例1:

        输入：S = "qwe"
        输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
        示例2:

        输入：S = "ab"
        输出：["ab", "ba"]
        提示:

        字符都是英文字母。
        字符串长度在[1, 9]之间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/permutation-i-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _面试题_08_07_无重复字符串的排列组合 {

    public String[] permutation(String S) {
        char[] chars = S.toCharArray();
        boolean[] used = new boolean[chars.length];
        List<String> res = new LinkedList<>();

        dfs(chars, used, new StringBuilder(), res);
        return res.toArray(new String[0]);
    }

    private void dfs(char[] data, boolean[] used, StringBuilder stringBuilder, List<String> res){
        if (stringBuilder.length() == data.length){
            res.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < data.length; i++) {
            if (used[i] == true) continue;
            used[i] = true;
            stringBuilder.append(data[i]);
            dfs(data,used,stringBuilder,res);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            used[i] = false;
        }
    }
}
