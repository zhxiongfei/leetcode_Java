package 哈希表;

/**
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

        示例 1：

        输入：s = "bcabc"
        输出："abc"
        示例 2：

        输入：s = "cbacdcbc"
        输出："acdb"
         

        提示：

        1 <= s.length <= 104
        s 由小写英文字母组成

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/remove-duplicate-letters
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class _316_去除重复字母 {

    public String removeDuplicateLetters(String s) {
        boolean[] letters = new boolean[26];
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (letters[c - 'a']) continue;
            letters[c - 'a'] = true;
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

}
