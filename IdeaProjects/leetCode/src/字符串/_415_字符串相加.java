package 字符串;

/*
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

        注意：

        num1 和num2 的长度都小于 5100.
        num1 和num2 都只包含数字 0-9.
        num1 和num2 都不包含任何前导零。
        你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/add-strings
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.math.BigDecimal;

public class _415_字符串相加 {

    /*
    *
    * 18年底，去苏宁面试，题目之一
    * 当时只想着用 Obj-C 的 NSDecimal类来解决,想不到其它
    * 现在再看这类题目, 跟两个链表求和 思路一模一样的
    * 无非是用两个指针从末尾开始遍历，逐渐求和
    * 用carry记录，上一次相加是否有进位
    * 下次位数求和时，需要加上carry
    * 但是此解法，反转了字符串
    * 下边尝试一下，不使用反转
    * */
    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0) {

            int n1 = i < 0 ? 0 : num1.charAt(i--) - '0';
            int n2 = j < 0 ? 0 : num2.charAt(j--) - '0';

            int res = n1 + n2 + carry;
            carry = 0;
            if (res >= 10) {
                carry = 1;
                res -= 10;
            }
            sb.append(res);
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    /*
    *
    * 在这种做法中
    * 我们将字符串转为 字符数组
    * 转为数组好操作的地方在于 ： 可以直接根据下标直接访问(存 / 取)
    * 减少了一次 reverse() 操作。
    * 而字符串的reverse() 依然需要 O(n) 的时间复杂度
    * */
    public static String addStrings1(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        if (i < j) return addStrings1(num2, num1);

        // 到这一步，保证了 chars1.length() > chars2.length()
        // 两个数字相加， 最多不超过两个数字较大者 再加一位
        // 所以把最终结果放入 chars1 中.
        // 如果最终carry > 0, 前边再拼接上1
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        int carry = 0;
        while (i >= 0) {

            int n1 = i < 0 ? 0 : chars1[i] - '0';
            int n2 = j < 0 ? 0 : chars2[j--] - '0';

            int res = n1 + n2 + carry;
            carry = 0;
            if (res >= 10) {
                carry = 1;
                res -= 10;
            }
            chars1[i--] = (char) (res + '0');
        }
        return carry > 0 ? 1 + String.valueOf(chars1) : String.valueOf(chars1);
    }

    public static void main(String[] args) {
        addStrings("1","9");


    }

}
