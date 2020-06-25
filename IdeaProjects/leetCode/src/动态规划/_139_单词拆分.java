package 动态规划;

/*
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

        说明：

        拆分时可以重复使用字典中的单词。
        你可以假设字典中没有重复的单词。
        示例 1：

        输入: s = "leetcode", wordDict = ["leet", "code"]
        输出: true
        解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
        示例 2：

        输入: s = "applepenapple", wordDict = ["apple", "pen"]
        输出: true
        解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
             注意你可以重复使用字典中的单词。
        示例 3：

        输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        输出: false

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/word-break
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;

public class _139_单词拆分 {

    // 动态规划
    // s = "leetcode", wordDict = ["leet", "code"]
    // "leetcode", wordDict = ["leet", "code"]
    public boolean wordBreak(String s, List<String> wordDict) {

        // dp[i]表示， 以 i 结尾的子串是否被包含在字典中
        boolean[] dp = new boolean[s.length() + 1];

        // 定义初始值
        dp[0] = true;

        // 状态转移方程
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 以 j 拆分字符串
                // dp[j] == true
                // 且 字符串字典 包含 s.substring(j, i)
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

}
