package 滑动窗口;

/*
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

         

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
         

        提示：

        s.length <= 40000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;

public class _剑指_Offer_48_最长不含重复字符的子字符串 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int left = 0, right = 1, max = 1;
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(left));

        while (right < s.length()){
            if (!set.contains(s.charAt(right))){
                set.add(s.charAt(right ++));
                max = Math.max(max, right - left);
            }else{
                set.remove(s.charAt(left ++));
            }
        }
        return max;
    }

}
