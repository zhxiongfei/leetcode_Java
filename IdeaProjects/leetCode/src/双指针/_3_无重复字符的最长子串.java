package 双指针;

import java.util.HashSet;
import java.util.Set;

/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

        示例 1:

        输入: "abcabcbb"
        输出: 3
        解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        示例 2:

        输入: "bbbbb"
        输出: 1
        解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        示例 3:

        输入: "pwwkew"
        输出: 3
        解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
             请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _3_无重复字符的最长子串 {

    // 滑动窗口
    // 思路是逐步计算 以 第一个字符开头的最长无重复子串，第二个字符开头的...直到末尾
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Set set = new HashSet();
        int ans = 0,i = 0, j = 0;
        while (i < length && j < length){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans,j - i);
            }else {
                set.remove(s.charAt(i++));
            }
        }

        return ans;
    }

    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
