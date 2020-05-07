package 未分组;

/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。

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

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-common-subsequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _1143_最长公共子序列 {

    public static int longestCommonSubsequence1(String text1, String text2) {

        // 定义状态  dp[i][j]为 字符串1 以i结尾的字串，和字符串2以j结尾的字串的最长公共子序列。
        int [][]dp = new int[text1.length() + 1][text2.length() + 1];

        // 定义初始值 数组中默认都为1，不需要重新赋值

        // 动态转移方程
        // 当 c1 和 c2相等时，dp为其左上的值加1
        // 当 c1 和 c2不想等时，dp为其左边 和 上边的最大值
        for (int i = 1; i <= text1.length(); i++) {
            char c1 = text1.charAt(i - 1);

            for (int j = 1; j <= text2.length(); j++) {
                char c2 = text2.charAt(j - 1);

                if (c1 == c2){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static int longestCommonSubsequence(String text1, String text2) {

        // 定义状态  dp[j]为 字符串1 以i结尾的字串，和字符串2以j结尾的字串的最长公共子序列。
        int []dp = new int[text2.length() + 1];

        // 定义初始值 数组中默认都为1，不需要重新赋值

        // 动态转移方程
        // 当 c1 和 c2相等时，dp为其左上的值加1
        // 当 c1 和 c2不想等时，dp为其左边 和 上边的最大值
        for (int i = 1; i <= text1.length(); i++) {
            char c1 = text1.charAt(i - 1);
            int cur = 0;

            for (int j = 1; j <= text2.length(); j++) {
                char c2 = text2.charAt(j - 1);

                int leftTop = cur;
                cur = dp[j];

                if (c1 == c2){
                    dp[j] = leftTop +1;
                }else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
            }
        }

        return dp[text2.length()];
    }

    public static void main(String[] args) {

        longestCommonSubsequence("bsbininm","jmjkbkjkv");
    }
}
