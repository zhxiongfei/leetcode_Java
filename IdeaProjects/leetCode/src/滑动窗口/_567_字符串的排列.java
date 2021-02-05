package 滑动窗口;

/**
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
        换句话说，第一个字符串的排列之一是第二个字符串的子串。

        示例1:
        输入: s1 = "ab" s2 = "eidbaooo"
        输出: True
        解释: s2 包含 s1 的排列之一 ("ba").
         
        示例2:
        输入: s1= "ab" s2 = "eidboaoo"
        输出: False

        注意：
        输入的字符串只包含小写字母
        两个字符串的长度都在 [1, 10,000] 之间

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/permutation-in-string
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _567_字符串的排列 {

    // s1 = "ab"
    // s2 = "eidboaooo"

    /**
     * 滑动窗口
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(1) 常数级别
     *
     * 1, 因为只存在小写字母， 所以用两个长度为 26 的数组存储元素出现数量
     * 2, count1 记录 s1 中元素的出现数量
     * 3，count2 记录 s2 滑动窗口中的出现次数
     * 4，每次滑动窗口后， 比较 count1 和 count2 是否完全相同
     * 5，如果相同， 则return true
     * 6，不同， 则继续滑动窗口
     * 7，滑动完后， 更新 counts2 中存储的元素数量
     *  * */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            count1[chars1[i] - 'a']++;
            count2[chars2[i] - 'a']++;
        }
        if (match(count1, count2)) return true;

        int length = s2.length(), size = s1.length();
        for (int i = 1; i < length - size + 1; i ++){
            count2[chars2[i - 1] - 'a']--;
            count2[chars2[i + size - 1] - 'a'] ++;
            if (match(count1, count2)) return true;
        }
        return false;
    }

    public boolean match(int[]num1, int[]num2){
        for (int i = 0; i < 26; i++) {
            if (num1[i] != num2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        _567_字符串的排列 cls = new _567_字符串的排列();
        // "abc"
        // "ccccbbbbaaaa"

        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(cls.checkInclusion(s1, s2));
    }
}
