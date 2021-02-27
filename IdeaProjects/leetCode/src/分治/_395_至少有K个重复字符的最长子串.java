package 分治;

/**
找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。

        示例 1:

        输入:
        s = "aaabb", k = 3

        输出:
        3

        最长子串为 "aaa" ，其中 'a' 重复了 3 次。
        示例 2:

        输入:
        s = "ababbc", k = 2

        输出:
        5

        最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _395_至少有K个重复字符的最长子串 {

    /**
     * 对于字符串 s
     * 对于字符 ch， 次数 > 0 && < k
     * 则 任何包含 ch 的子串都不可能满足要求
     *
     * 按 ch 分成若干段
     * 则满足要求的最长子串一定出现在某个被切分的段内
     * 不能跨越多个段
     *
     * 所以采用分治
     * */
    public int longestSubstring(String s, int k){
        int length = s.length();
        return dfs(s, 0, length - 1, k);
    }

    private int dfs(String s, int l, int r, int k){
        int[] counts = new int[26];
        for (int i = l; i <= r; i++){
            counts[s.charAt(i) - 'a']++;
        }
        char split = 0;
        for (int i = 0; i < 26; i++){
            if (counts[i] > 0 && counts[i] < k){
                split = (char)(i + 'a');
                break;
            }
        }
        if (split == 0) return r - l + 1;
        int i = l;
        int res = 0;
        while (i <= r){
            while (i <= r && s.charAt(i) == split) i++;
            if (i > r) break;

            int start = i;
            while (i <= r && s.charAt(i) != split){
                i++;
            }
            int length = dfs(s, start, i - 1, k);
            res = Math.max(res, length);
        }
        return res;
    }

    public static void main(String[] args) {
        _395_至少有K个重复字符的最长子串 cls = new _395_至少有K个重复字符的最长子串();
        int res = cls.longestSubstring("aaabbc", 3);
        System.out.println(res);
    }
}
