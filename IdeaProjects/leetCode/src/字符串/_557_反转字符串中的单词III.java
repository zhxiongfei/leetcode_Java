package 字符串;

import java.util.Arrays;
import java.util.List;

/**
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

        示例 1:

        输入: "Let's take LeetCode contest"
        输出: "s'teL ekat edoCteeL tsetnoc" 
        注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _557_反转字符串中的单词III {

    // 翻转 [l, r) 左闭右开
    public void reverseHelper(char[] chars, int l, int r){
        r --;
        while (l < r){
            char c1 = chars[l];
            chars[l ++] = chars[r];
            chars[r --] = c1;
        }
    }

    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) return s;

        char[] chars = s.toCharArray();
        reverseHelper(chars, 0, chars.length);
        s = String.valueOf(chars);
        String[] strings = s.split(" ");

        StringBuilder stringBuilder = new StringBuilder();

        int l = 0, r = strings.length - 1;
        while (l < r){
            String s1 = strings[l];
            strings[l ++] = strings[r];
            strings[r --] = s1;
        }

        for (int i = 0; i < strings.length; i++) {
            if (i != 0) stringBuilder.append(" ");
            stringBuilder.append(strings[i]);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        _557_反转字符串中的单词III cls = new _557_反转字符串中的单词III();
        cls.reverseWords("Let's take LeetCode contest");
    }
}
