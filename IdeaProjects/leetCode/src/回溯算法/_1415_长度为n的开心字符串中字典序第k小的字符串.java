package 回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
一个 「开心字符串」定义为：

        仅包含小写字母 ['a', 'b', 'c'].
        对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
        比方说，字符串 "abc"，"ac"，"b" 和 "abcbabcbcb" 都是开心字符串，但是 "aa"，"baa" 和 "ababbc" 都不是开心字符串。

        给你两个整数 n 和 k ，你需要将长度为 n 的所有开心字符串按字典序排序。

        请你返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串 。
        示例 1：

        输入：n = 1, k = 3
        输出："c"
        解释：列表 ["a", "b", "c"] 包含了所有长度为 1 的开心字符串。按照字典序排序后第三个字符串为 "c" 。
        示例 2：

        输入：n = 1, k = 4
        输出：""
        解释：长度为 1 的开心字符串只有 3 个。
        示例 3：

        输入：n = 3, k = 9
        输出："cab"
        解释：长度为 3 的开心字符串总共有 12 个 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"] 。第 9 个字符串为 "cab"
        示例 4：

        输入：n = 2, k = 7
        输出：""
        示例 5：

        输入：n = 10, k = 100
        输出："abacbabacb"

        提示：
        1 <= n <= 10
        1 <= k <= 100

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1415_长度为n的开心字符串中字典序第k小的字符串 {

    public String getHappyString(int n, int k) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = {'a','b','c'};
        dfs(list,chars,  sb, n, k, 0);

        return k > list.size() ? "" : list.get(k - 1);
    }

    private void dfs(List<String> list,char[] chars, StringBuilder sb, int n, int k, int depth){
        if (list.size() == k) return;//达到目标k
        if (depth == n){
            list.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) continue;
            if (list.size() == k) break;

            sb.append(c);
            dfs(list, chars, sb, n, k, depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        _1415_长度为n的开心字符串中字典序第k小的字符串 cls = new _1415_长度为n的开心字符串中字典序第k小的字符串();
        cls.getHappyString(3, 2);
    }
}
