package 动态规划;

/*
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

        你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

        字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

        示例 1:
        s = "abc", t = "ahbgdc"

        返回 true.

        示例 2:
        s = "axc", t = "ahbgdc"

        返回 false.

        后续挑战 :

        如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/is-subsequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Map;

public class _392_判断子序列 {

    static boolean isSubsequence(String s, String t) {
        if(s == null || s.length() == 0) return true;

        // 定义状态  字符串s 截止index为i 子串 是否是子序列。

        // 设置初始状态 字符串为nil或者长度为0时，肯定是t的子序列

        int lastIdx = 0;
        // 确定状态转移方程
        // 确定dp[i] 和 dp[i-1]的关系
        for (int i = 1; i <= s.length(); i++) {
            char c1 = s.charAt(i-1);
            for (int j = lastIdx; j < t.length(); j++) {
                char c2 = t.charAt(j);
                if (c1 == c2){
                    if (i == s.length()) return true;
                    lastIdx = j + 1;
                    break;
                }else {
                    if (j == t.length()-1){
                        return false;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args){
        boolean res = isSubsequence("acb","ahbgdc");
        System.out.println(res);
    }
}
