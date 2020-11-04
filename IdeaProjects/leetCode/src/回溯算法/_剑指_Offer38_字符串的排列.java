package 回溯算法;

import java.util.*;

/**
   输入一个字符串，打印出该字符串中字符的所有排列。
   你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
   示例:

   输入：s = "abc"
   输出：["abc","acb","bac","bca","cab","cba"]
    
   限制：
   1 <= s 的长度 <= 8

   来源：力扣（LeetCode）
   链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
   著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _剑指_Offer38_字符串的排列 {

    /**
     * 此题目跟 47.全排列II 一模一样
     * */
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        List<String> res = new ArrayList<>();
        Deque<String> path = new ArrayDeque<>();
        boolean[] used = new boolean[s.length()];

        dfs(res, path, used,chars, 0);

        return res.toArray(new String[res.size()]);
    }

    public void dfs(List<String>res, Deque<String> path, boolean[] used,char[] chars, int depth){
        if (depth == chars.length){
            res.add(String.join("", path));
            return;
        }

        for (int i = 0; i < chars.length; i++) {

            if (used[i]) continue;
            if (i > 0 && chars[i-1] == chars[i] && !used[i - 1]) continue;

            used[i] = true;
            path.addLast(String.valueOf(chars[i]));
            dfs(res, path,used, chars, depth + 1);

            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _剑指_Offer38_字符串的排列 cls = new _剑指_Offer38_字符串的排列();
        cls.permutation("aab");
    }
}
