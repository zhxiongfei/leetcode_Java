package 动态规划;

/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。

        一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
        例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

        若这两个字符串没有公共子序列，则返回 0。

         

        示例 1:

        输入：text1 = "abcde", text2 = "ace"
        输出：3
        解释：最长公共子序列是 "ace"，它的长度为 3。
        示例 2:

        输入：text1 = "abc", text2 = "abc"
        输出：3
        解释：最长公共子序列是 "abc"，它的长度为 3。
        示例 3:

        输入：text1 = "abc", text2 = "def"
        输出：0
        解释：两个字符串没有公共子序列，返回 0。
         

        提示:

        1 <= text1.length <= 1000
        1 <= text2.length <= 1000
        输入的字符串只含有小写英文字符。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-common-subsequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _1143_最长公共子序列 {

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;

        return max(text1,text1.length(),text2,text2.length());
    }

    // 暴力递归 leetcode超出时间显示 时间复杂度O(2^n) 空间复杂度(Math.min(n,m))
    private int max1(String text1,int len1,String text2,int len2){
        if (len1 == 0 || len2 == 0) return 0;

        char c1 = text1.charAt(len1 - 1);
        char c2 = text2.charAt(len2 - 1);

        // 最后一位相等时  加1
        if (c1 == c2){
            return max1(text1,len1 - 1,text2,len2 - 1) + 1;
        }else {
            // 不相等时，取出两处 的最大值
            return Math.max(max1(text1,len1,text2,len2 - 1), max1(text1,len1-1,text2,len2));
        }
    }

    // 二维数组 时间复杂度O(n * m) 空间复杂度O(n * m)
    private int max2(String text1,int len1,String text2,int len2){
        if (len1 == 0 || len2 == 0) return 0;
        // 二维数组
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1 == c2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    // 用滚动数组优化空间复杂度  学到了滚动数组再搞
    private int max3(String text1,int len1,String text2,int len2){
        if (len1 == 0 || len2 == 0) return 0;

        return 0;
    }

    // 一维数组
    static int max(String text1,int len1,String text2,int len2){
        if (len1 == 0 || len2 == 0) return 0;

        // 数组
        int[] dp = new int[len2 + 1];
        for (int i = 1; i <= len1; i++) {
            int cur = 0;
            for (int j = 1; j <= len2; j++) {
                int leftTop = cur;  // 存储左上的值
                cur = dp[j];

                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1 == c2){
                    dp[j] = leftTop + 1;
                }else {
                    dp[j] = Math.max(dp[j],dp[j-1]);
                }
            }
        }
        return dp[len2];
    }

    public static void main(String[] args){
        System.out.println(longestCommonSubsequence("pmjghexybyrgzczy","hafcdqbgncrcbihkd"));
    }
}
