package 回溯算法;

import java.util.*;

/**
有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
        示例1:
        输入：S = "qqe"
        输出：["eqq","qeq","qqe"]
        示例2:
        输入：S = "ab"
        输出：["ab", "ba"]
        提示:
        字符都是英文字母。
        字符串长度在[1, 9]之间。
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/permutation-ii-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _面试题_08_08_有重复字符串的排列组合 {

    public String[] permutation(String S) {
        List<String> res = new ArrayList<>();
        char[] chars = S.toCharArray();
        Arrays.sort(chars);
        boolean[] used = new boolean[chars.length];
        StringBuilder path = new StringBuilder();

        dfs(res, chars, used, path, 0,chars.length);

        return res.toArray(new String[res.size()]);
    }

    private void dfs(List<String> res, char[] chars,boolean[] used, StringBuilder path, int depth, int length){
        if (depth == length){
            res.add(path.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            path.append(chars[i]);

            dfs(res,chars,used,path,depth + 1,length);

            used[i] = false;
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        _面试题_08_08_有重复字符串的排列组合 cls = new _面试题_08_08_有重复字符串的排列组合();
        String str = "qqe";
        cls.permutation(str);
    }
}
