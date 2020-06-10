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

    /**
     *
     * 利用栈
     * 小于 一半时 入栈
     * 大于 一半时 出栈
     *
     * 最终栈为空，则回文。 不空，则不回文
     *
     * */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;

        String s = "" + x;
        if (s.length() == 2) return s.charAt(0) == s.charAt(1);

        boolean isSingle = s.length() % 2 != 0; // 位数是否是单数
        int half = s.length() >> 1 - (isSingle ? 1 : 0);
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

    /**
     *
     * 双指针
     * 数字转字符串
     * left指针指向最左边
     * right指针指向最右边
     *
     * 字符串[left] == 字符串[right] 时，left ++ ,right --;
     * 字符串[left] != 字符串[right] 时，return false;
     *
     * */
    public boolean isPalindrome1(int x) {
        if(x < 0) return false;
        if(x < 10) return true;

        String string = String.valueOf(x);
        int left = 0, right = string.length() - 1;
        while (left <= right){

            char c1 = string.charAt(left);
            char c2 = string.charAt(right);

            if (c1 != c2) return false;
            left ++;
            right --;
        }
        return true;
    }


    /**
     *
     * 数学解法
     * 其实类似转化为字符串的双指针解法
     *
     * 只不过是用数学方法计算出首位 和 末尾进行比较
     * 然后再缩小 x
     *
     * 11211
     *
     * div = 10000
     * first = x/div
     * last = x % 10
     *
     * x对div取余 就是除首元素以外的数
     * 再 / 10， 就去掉了末尾元素
     * 经过这两步操作， 就获得了缩减后的x
     *
     * */
    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        int div = 1;
        while (x/div >= 10) div *= 10;

        while (x > 0){
            int first = x / div;
            int last = x % 10;
            if (first != last) return false;

            x = (x % div) / 10;
            div /= 100;
        }

        return true;
    }

    public static void main(String[] args){

        isPalindrome2(1201);
    }



    /**
     *
     * 彩蛋
     * 与本地思路类似
     * 大搜车笔试题之一
     * 递归判断是否是回文字串
     *
     * */
    public static boolean isPalindromeRecursive(String s) {

        if (s == null || s.length() <= 1) return true;
        char start = s.charAt(0);
        char end = s.charAt(s.length() - 1);
        if (start != end) return false;

        s = s.substring(1,s.length()-1);
        isPalindromeRecursive(s);

        return true;
    }
}
