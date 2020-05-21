package 动态规划;

public class 最长回文串 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        // 最长回文子串的长度
        int maxlen = 1;
        // 最长回文字串的开始位置
        int maxstart = 0;
        // 最长回文字串的结束位置
        int maxend = 0;

        // dp 数组表示 以 i 开头，以j结尾的 子串 是否是回文子串
        boolean[][] dp = new boolean[s.length()][s.length()];

        // r 是右边指针
        for (int r = 1; r < s.length(); r++) {
            // l 是左边指针
            for (int l = 0; l < r; l++) {

                if (s.charAt(r) == s.charAt(l) && (dp[l + 1][r - 1]) || (r - l <= 2)){
                    dp[l][r] = true;

                    if ((r - l + 1) > maxlen){
                        maxlen = r - l + 1;
                        maxstart = l;
                        maxend = r;
                    }
                }
            }
        }

        return s.substring(maxstart, maxend + 1);
    }

}
