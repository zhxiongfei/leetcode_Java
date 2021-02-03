package 滑动窗口;

import java.util.Arrays;
import java.util.HashSet;

/**
        给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
        在执行上述操作后，找到包含重复字母的最长子串的长度。

        注意：字符串长度 和 k 不会超过 104。
        示例 1：

        输入：s = "ABAB", k = 2
        输出：4
        解释：用两个'A'替换为两个'B',反之亦然。
        示例 2：

        输入：s = "AABABBA", k = 1
        输出：4
        解释：
        将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
        子串 "BBBB" 有最长重复字母, 答案为 4。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _424_替换后的最长重复字符 {

    /**
     窗口从左至右不断扩张/滑动，当窗口触达字符串末尾字符时，运算结束，窗口的宽度为最终结果。

     初始窗口的宽度为 1，我们不断的通过向当前窗口覆盖的子串后面追加一个字符看是否能满足我们的要求。
     如果满足窗口扩张，如果不满足，窗口向右滑动。

     当 K>0 时，子串的条件变成了允许我们变换子串中的 K 个字符使其变成一个连续子串
     那么这个题的关键点就是我们如何判断一个字符串改变K 个字符，能够变成一个连续串

     如果当前字符串中的出现次数最多的字母个数+K 大于串长度，那么这个串就是满足条件的

     我们维护一个数组 int[26] 来存储当前窗口中各个字母的出现次数，
     left 表示窗口的左边界，
     right 表示窗口右边界

     窗口扩张：
        left 不变，
        right++

     窗口滑动：
        left++，
        right++
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;

        char[] chars = s.toCharArray();
        int left = 0, right = 0, length = s.length(), historyCharMax = 0;
        int[] counts = new int[26];

        // 左闭右开
        while (right < length){
            int index = chars[right] - 'A';
            counts[index] ++;

            historyCharMax = Math.max(historyCharMax, counts[index]);
            if (right - left + 1 > historyCharMax + k) {
                counts[chars[left] - 'A']--;
                left++;
            }
            right ++;
        }
        return length - left;
    }

    public static void main(String[] args) {
        _424_替换后的最长重复字符 cls = new _424_替换后的最长重复字符();
        int res = cls.characterReplacement("ABBBA", 2);
        System.out.println(res);
    }
}
