package 双指针;

/*
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

        说明：本题中，我们将空字符串定义为有效的回文串。

        示例 1:

        输入: "A man, a plan, a canal: Panama"
        输出: true
        示例 2:

        输入: "race a car"
        输出: false

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/valid-palindrome
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;
import java.util.Stack;

public class _125_验证回文串 {

    public static void main(String[] args) {
        _125_验证回文串 cls = new _125_验证回文串();

        boolean res = cls.wordPattern("abba", "dog cat cat fish");
        System.out.println(res);
    }

    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        HashMap<String, Character> map = new HashMap<>();
        if (strings.length != pattern.length()) return false;

        for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            char pat = pattern.charAt(i);
            if (map.containsKey(str)){
                char c = map.get(str);
                if (c != pat) return false;
            }else {
                if (map.values().contains(pat)) return false;
                map.put(str, pat);
            }
        }
        return true;
    }

    /**
     *
     * 双指针
     *
     * */
    public boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;

        int i=0, j = s.length() - 1;
        while (i < j){

            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j --;

            char c1 = s.charAt(i);
            char c2 = s.charAt(j);

            if(Character.toLowerCase(c1) != Character.toLowerCase(c2)) return false;

            i ++;
            j --;
        }
        return true;
    }

    // 递归
    public static boolean isPalindromeHelper(String s, int left , int right){
        if (left >= right) return true;

        if (!Character.isLetterOrDigit(s.charAt(left))){
            return isPalindromeHelper(s, ++left, right);
        }
        if (!Character.isLetterOrDigit(s.charAt(right))){
            return isPalindromeHelper(s, left, --right);
        }

        return Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)) && isPalindromeHelper(s, ++left, --right);
    }

    public static boolean isPalindrome1(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }
}
