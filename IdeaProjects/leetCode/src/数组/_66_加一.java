package 数组;

/*
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

        最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

        你可以假设除了整数 0 之外，这个整数不会以零开头。

        示例 1:

        输入: [1,2,3]
        输出: [1,2,4]
        解释: 输入数组表示数字 123。
        示例 2:

        输入: [4,3,2,1]
        输出: [4,3,2,2]
        解释: 输入数组表示数字 4321。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/plus-one
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

public class _66_加一 {

    public int[] plusOne1(int[] digits) {

        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int n = digits[i];
            n += (carry);

            carry = 0;
            if (n == 10) {
                n = 0;
                carry = 1;
            }

            digits[i] = n;

            if (carry == 0) break;
        }

        if (carry > 0){
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }

        return digits;
    }

    public int[] plusOne(int[] digits) {

        int carry = 1;
        int n = digits.length - 1;
        while (carry > 0 && n >= 0){
            digits[n] += carry;

            carry = 0;
            if (digits[n] == 10){
                digits[n] = 0;
                carry ++;
            }

            n--;
        }

        if (carry > 0){
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }

        return digits;
    }
}
