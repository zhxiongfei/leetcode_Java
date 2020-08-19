package 动态规划;

import tools.Asserts;

/**
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

        具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

         

        示例 1：

        输入："abc"
        输出：3
        解释：三个回文子串: "a", "b", "c"
        示例 2：

        输入："aaa"
        输出：6
        解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
         

        提示：

        输入的字符串长度不会超过 1000 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/palindromic-substrings
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _647_回文子串 {

    /**
     * 暴力法
     * 时间复杂度 : O(n ^ 3)
     * 空间复杂度 : O(1)
     *
     * 分别计算以每一位开头的子串的回文子串数量, 再相加
     *
     * 提交后，虽然ac了，但是耗时非常差劲，执行好事554ms，击败6.17%的用户
     * 下边尝试使用 dp 做优化
     * */
    public boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        int i=0, j = s.length() - 1;
        while (i < j){
            if(s.charAt(i ++) != s.charAt(j --)) return false;
        }
        return true;
    }

    public int countSubstrings1(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;

        // aaa
        for (int i = 0; i < s.length(); i++) {
            int cnt = 1;
            for (int j = i + 1; j < s.length(); j++) {
                String subStr = s.substring(i, j + 1);
                if (isPalindrome(subStr)) cnt ++;
            }
            res += cnt;
        }
        return res;
    }

    /**
     * 以每个位置的元素为中心点，分别向左右两边扩展
     * 时间复杂度 : O(n ^ 2)
     * 空间复杂度 : O(1)
     * */
    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0, len = s.length();

        for (int i = 0; i < 2 * len - 1; i++) {
            int m = i / 2, n = i / 2 + i % 2;
            while (m >= 0 && n < len && s.charAt(m --) == s.charAt(n ++)) {
                res ++;
            }
        }
        return res;
    }

    /**
     * dp
     * 时间复杂度 : O(n ^ 2)
     * 空间复杂度 : O(n ^ 2)
     * 用 dp 数组来进行动态规划
     * dp[i][len] 表示从 i 开始的长度为 len 的子串是否是回文子串
     * 那么 dp[i][len] = dp[i + 1][len - 2] && s[i] == s[i + len - 1]
     * 以此来动态规划即可, 其实和中心扩散本质是一样的, 都是从小区间向大区间扩散
     *
     * */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int length = s.length();
        int res = length;
        boolean[][] dp = new boolean[s.length()][s.length() + 1];
        for (int i = 0; i < length; i++) {
            dp[i][0] = true;
            dp[i][1] = true;
        }
        for (int len = 2; len <= length; len++) {
            for (int i = 0; i <= length - len; i++){
                if (i + 1 < length && dp[i + 1][len - 2] && s.charAt(i) == s.charAt(i + len - 1)){
                    res ++;
                    dp[i][len] = true;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        _647_回文子串 cls = new _647_回文子串();
        int res = cls.countSubstrings("aa");
        System.out.println(res);
    }

}
