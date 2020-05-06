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

    static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;

        int len = s.length();
        int maxStart = 0;       // 最长回文串的起点
        int maxEnd = 0;         // 最长回文串的终点
        int maxLen = 1;         // 最长回文串的长度

        // dp[i][j] 表示子串 s[i,j]是否为回文子串。
        boolean[][] dp = new boolean[len][len];

        for (int r = 1; r < len; r++) {     // 右
            for (int l = 0; l < r; l++) {   // 左
                if (s.charAt(l) == s.charAt(r) && (dp[l + 1][r - 1] || r - l <= 2)){
                    dp[l][r] = true;

                    if (r - l + 1 > maxLen){
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }

        return s.substring(maxStart,maxEnd + 1);
    }

    public static void main(String[] args){

       System.out.println(longestPalindrome("ccc"));
    }
}
