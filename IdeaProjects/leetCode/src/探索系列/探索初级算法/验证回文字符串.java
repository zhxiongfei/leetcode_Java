package 探索系列.探索初级算法;

public class 验证回文字符串 {

    /**
     *
     * 迭代
     *
     * */
    public boolean isPalindrome1(String s) {
        if (s == null || s.length() <= 1) return true;

        char[] chars = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right){
            if (!Character.isLetterOrDigit(chars[left])){
                left ++;
                continue;
            }
            if (!Character.isLetterOrDigit(chars[right])){
                right --;
                continue;
            }

            if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])) return false;

            left ++;
            right --;
        }
        return true;
    }

    /**
     *
     * 递归
     *
     * */
    public boolean isPalindrome(String s, int left, int right) {
        if (left >= right) return true;

        if (!Character.isLetterOrDigit(s.charAt(left))){
            return isPalindrome(s, left + 1, right);
        }

        if (!Character.isLetterOrDigit(s.charAt(right))){
            return isPalindrome(s, left, right - 1);
        }

        return Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)) &&
                isPalindrome(s, left ++, right --);
    }

    public boolean isPalindrome(String s) {
        return isPalindrome(s,0, s.length() - 1);
    }
}
