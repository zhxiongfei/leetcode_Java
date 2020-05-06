package 动态规划;

/*
最长公共子串(Longest Common Substring)
子串是连续的子序列

求两个字符串的最长公共子串长度
ABCBA和BABCA的最长公共子串是 ABC，长度为3
 */

public class _最长公共子串 {
    static int solution(String str1, String str2){

        return max(str1,str1.length(),str2,str2.length());
    }

    // 动态规划 二维数组
    // 时间复杂度 O(n * m)
    // 空间复杂度 O(n * m)
    static int max1(String str1, int len1, String str2, int len2){
        if (len1 == 0 || len2 == 0) return 0;
        // 二维数组
        int[][] dp = new int[len1 + 1][len2 + 1];

        int max = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                if (c1 == c2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = 0;
                }

                max = Math.max(max,dp[i][j]);
            }
        }
        return max;
    }

    // 动态规划 一维数组
    // 时间复杂度 O(n * m)
    // 空间复杂度 O(n)
    static int max(String str1, int len1, String str2, int len2){
        if (len1 == 0 || len2 == 0) return 0;
        // 一维数组
        int[] dp = new int[len2 + 1];

        int max = 0;
        for (int i = 1; i <= len1; i++) {
            // 需要从后往前遍历，如果从前往后就会把之前dp覆盖。 而从后往前就可以正常用之前dp的值
            for (int j = len2; j >= 1; j--) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                if (c1 == c2){
                    dp[j] = dp[j-1] + 1;
                }else {
                    dp[j] = 0;
                }

                max = Math.max(max,dp[j]);
            }
        }
        return max;
    }

    // 继续优化思路 - 找出len1 和 len2小的作为行. 大的作为列。 空间复杂度优化至 O(min(len1,len2))

    public static void main(String[] args){
        System.out.println(solution("abcba","babca"));
    }
}
