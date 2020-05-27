package 集合;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class _409_最长回文串 {

    // 遍历字符串，使用set存储，遍历过的字符
    // 当遍历当前字符时，set 中包含，则将set中此字符移除，因为成对的字符一定能组成回文字串
    // 当遍历当前字符是，set 中不包含， 则将此字符加入到set中
    // 遍历完毕后，如果set为空，或者 set中只有一个元素，则原字符串都可以组成回文字串，所以最长就是 s.length()
    // 如果set.size > 1。 则 s.length() - set.size() + 1 为原字符串可组成的最长回文字串
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (set.contains(c)){
                set.remove(c);
            }else {
                set.add(c);
            }
        }
        return set.isEmpty() ? s.length() : s.length() - set.size() + 1;
    }


    public int longestPalindrome1(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] counts = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (counts[c - '0'] != 0){
                counts[c - '0'] = 0;
            }else {
                counts[c - '0'] = 1;
            }
        }

        int cnt = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 1) cnt ++;
        }

        return cnt == 0 ? s.length() : s.length() - cnt + 1;
    }

}
