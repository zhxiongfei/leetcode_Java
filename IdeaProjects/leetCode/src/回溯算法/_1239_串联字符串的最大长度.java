package 回溯算法;

import java.util.Iterator;
import java.util.List;

/**
给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。

        请返回所有可行解 s 中最长长度。
        示例 1：

        输入：arr = ["un","iq","ue"]
        输出：4
        解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
        示例 2：

        输入：arr = ["cha","r","act","ers"]
        输出：6
        解释：可能的解答有 "chaers" 和 "acters"。
        示例 3：
        输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
        输出：26
        提示：

        1 <= arr.length <= 16
        1 <= arr[i].length <= 26
        arr[i] 中只含有小写英文字母

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1239_串联字符串的最大长度 {

    /**
     * 回溯
     * */
    int res;
    public int maxLength(List<String> arr) {
        if (arr.size() == 1) return arr.get(0).length();
        boolean[] letters = new boolean[26];

        for (Iterator<String> it = arr.iterator(); it.hasNext();) {
            char[] chars = it.next().toCharArray();

            boolean[] tmp = new boolean[26];
            for (int i = 0; i < chars.length; i++) {
                if (tmp[chars[i] - 'a']){
                    it.remove();
                    break;
                }
                tmp[chars[i] - 'a'] = true;
            }
        }

        dfs(letters, arr, 0);
        return res;
    }

    private void dfs(boolean[] letters, List<String> arr, int start){
        int cnt = 0;
        for (boolean exist : letters) {
            if (exist) cnt ++;
        }

        res = Math.max(res, cnt);

        for (int i = start; i < arr.size(); i ++) {
            String s = arr.get(i);
            char[] chars = s.toCharArray();
            boolean contains = false;
            for (char c : chars) {
                if (letters[c - 'a']){
                    contains = true;
                    break;
                }
            }
            if (contains) continue;
            for (char c : chars) {
                letters[c - 'a'] = true;
            }
            dfs(letters, arr, i + 1);
            for (char c : chars) {
                letters[c - 'a'] = false;
            }
        }
    }

    public static void main(String[] args) {

    }
}
