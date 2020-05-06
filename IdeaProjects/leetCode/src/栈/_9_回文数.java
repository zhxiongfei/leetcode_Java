package 栈;

/*
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

        示例 1:

        输入: 121
        输出: true
        示例 2:

        输入: -121
        输出: false
        解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
        示例 3:

        输入: 10
        输出: false
        解释: 从右向左读, 为 01 。因此它不是一个回文数。
        进阶:

        你能不将整数转为字符串来解决这个问题吗？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/palindrome-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.Stack;

public class _9_回文数 {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;

        String s = "" + x;
        if (s.length() == 2) return s.charAt(0) == s.charAt(1);

        boolean isSingle = s.length() % 2 != 0; // 位数是否是单数
        int half = (int) Math.ceil(s.length() / 2.0) - (isSingle ? 1 : 0);
        Stack <String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (i == half && isSingle) continue;

            if (i < half){
                stack.push(String.valueOf(c));
                continue;
            }

            String topS = stack.pop();
            if (!String.valueOf(c).equals(topS)) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    // 递归回文判断 level
    public static boolean isPalindromeRecursive(String s) {

        if (s == null || s.length() <= 1) return true;
        char start = s.charAt(0);
        char end = s.charAt(s.length() - 1);
        if (start != end) return false;

        s = s.substring(1,s.length()-1);
        isPalindromeRecursive(s);

        return true;
    }

    public static boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        //双指针循环找出不等于的字符索引
        while (i < j && chars[i] == chars[j]) {
            i++;
            j--;
        }
        //删除左边循环判断
        if (isValid(chars,i + 1,j)) return true;
        //删除右边循环判断
        if (isValid(chars,i,j - 1)) return true;
        //如果上面都是false，那么结果肯定是false
        return false;
    }
    //验证是否是回文
    private static boolean isValid(char[] chars,int i,int j) {
        while (i < j) {
            if (chars[i++] != chars[j--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){

        System.out.println(validPalindrome("deeee"));
    }
}
