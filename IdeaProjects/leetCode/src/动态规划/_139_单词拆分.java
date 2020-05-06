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

import com.sun.source.doctree.SeeTree;

import java.util.HashSet;
import java.util.List;

public class _139_单词拆分 {

    // 动态规划
    // s = "leetcode", wordDict = ["leet", "code"]
    public boolean wordBreak(String s, List<String> wordDict) {

//        HashSet wordSet = new HashSet(wordDict);
        // dp【i】 表示从 字符串i开始的可能的字符串。
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public int maxProfit(int[] prices) {

        if (prices.length <= 1) return 0;

        int[] dp = new int[prices.length];
        dp[0] = 0;

        int max = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {

            if (min > prices[i]){
                dp[i] = 0;
                min = prices[i];

                continue;
            }

            max = Math.max(max, prices[i] - min);
        }

        return max;
    }
}
