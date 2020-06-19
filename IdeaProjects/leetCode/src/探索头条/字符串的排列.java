package 探索头条;

public class 字符串的排列 {
    public static boolean checkInclusion(String s1, String s2) {

        int[] chars = new int[26];

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            chars[c - 'a'] += 1;
        }

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (chars[c - 'a'] <= 0) return false;
            chars[c - 'a'] -= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";

        checkInclusion(s1, s2);
    }
}
