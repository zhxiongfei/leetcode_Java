package Interview;

/*
编写一个程序判断给定的数是否为丑数。

        丑数就是只包含质因数 2, 3, 5 的正整数。

        示例 1:

        输入: 6
        输出: true
        解释: 6 = 2 × 3
        示例 2:

        输入: 8
        输出: true
        解释: 8 = 2 × 2 × 2
        示例 3:

        输入: 14
        输出: false
        解释: 14 不是丑数，因为它包含了另外一个质因数 7。
        说明：

        1 是丑数。
        输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/ugly-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;

public class _263_丑数 {

    /*
    * 第一种思路
    * 在丑数小于 num 时
    * 依次计算，丑数数列的下一个
    * 直到数列的数字 >= 丑数时
    * 当 == 丑数时，true。 当 > 丑数时， false。
    * */
    public static boolean isUgly2(int num) {
        if (num <= 0) return false;

        int p2 = 0, p3 = 0, p5 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        int i = 0;
        while (list.get(i) < num){
            list.add(Math.min(Math.min(list.get(p2) * 2, list.get(p3) * 3), list.get(p5) * 5));

            i ++;
            if (list.get(i) == list.get(p2) * 2) p2 ++;
            if (list.get(i) == list.get(p3) * 3) p3 ++;
            if (list.get(i) == list.get(p5) * 5) p5 ++;
        }

        return list.get(i) == num;
    }

    /*
    *
    * 思路二：
    * 如果是丑数， 则 除2 ， 除3， 除5 最终为1。
    * 如果 余数 != 0， 代表除不尽， 退出循环
    * 继续除下一个
    * 一直除到5，如果最终数为1， 则为丑数，否则不是丑数
    * */
    public static boolean isUgly1(int num) {
        if (num <= 0) return false;

        while (num % 2 == 0) num /= 2;
        if (num == 1) return true;

        while (num % 3 == 0) num /= 3;
        if (num == 1) return true;

        while (num % 5 == 0) num /= 5;
        if (num == 1) return true;

        return false;
    }

    /*
    *
    * 思路三：
    * 递归
    * 跟思路二类似
    * 迭代 换成 递归
    * */
    public static boolean isUgly(int num) {
        if (num < 1) return false;
        if (num == 1) return true;
        return search(num);
    }

    public static boolean search(int num){
        if (num == 1) return true;

        // 如果对 2 3 5都不能整除, 则说明这条路走不通, 返回上一层
        if (num % 2 != 0 && num % 3 != 0 && num % 5 != 0) return false;

        if (num % 2 == 0){
            return search(num >> 1);
        }else if (num % 3 == 0){
            return search(num / 3);
        }
        return search(num / 5);
    }

    public static void main(String[] args) {
        isUgly1(6);
    }
}
