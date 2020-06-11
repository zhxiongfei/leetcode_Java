package 动态规划;

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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class _3_无重复字符的最长子串 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        // map key是字符，value是字符出现的index
        HashMap<Character,Integer> map = new HashMap<>();
        int max = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
//            if (map.containsKey(s.charAt(i))){
//                for (int j = left; j < map.get(s.charAt(i)); j++) {
//                    map.remove(s.charAt(j));
//                }
//                left = map.get(s.charAt(i)) + 1;
//            }
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) >= left){
                left = map.get(s.charAt(i)) + 1;
            }

            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring("dvdf");
    }
}
