package 动态规划;

/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

        示例 1：

        输入: "babad"
        输出: "bab"
        注意: "aba" 也是一个有效答案。
        示例 2：

        输入: "cbbd"
        输出: "bb"

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-palindromic-substring
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _5_最长回文子串 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;

        // 字符串长度
        int len = s.length();

        // 回文子串最大长度
        int maxlen = 1;

        // 回文子串开始位置
        int maxleft = 0;

        // 回文子串结束为止
        int maxright = 0;

        // dp数组表示 从 i 开始， j结束的子串是否是回文子串
        boolean[][] dp = new boolean[len][len];
        for(int r = 1; r < len; r++){
            for(int l = 0; l < r; l++){

                // 如果 i 和 j位置元素一样
                // 并且 dp[i + 1][j - 1] 即 [i + 1,j - 1]位置是回文子串
                // 或者 i 和 j 紧挨着，或者相差一个
                // 则 dp[l][r] 为真
                // 如果新值 大于 前一个最大值
                // 更新最值
                if (s.charAt(l) == s.charAt(r) && (dp[l+1][r-1] || r - l <= 2)) {

                    dp[l][r] = true;

                    if (r - l + 1 > maxlen) {
                        maxlen = r - l + 1;

                        maxleft = l;
                        maxright = r;
                    }

                }
            }
        }

        return s.substring(maxleft, maxright + 1);
    }
}
