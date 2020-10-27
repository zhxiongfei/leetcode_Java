package 剑指offer;

/**
数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

        请写一个函数，求任意第n位对应的数字。

         

        示例 1：

        输入：n = 3
        输出：3
        示例 2：

        输入：n = 11
        输出：0
         

        限制：

        0 <= n < 2^31

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _剑指_Offer_44_数字序列中某一位的数字 {

    /**
    数字范围    数量  位数    占多少位
    1-9        9      1       9
    10-99      90     2       180
    100-999    900    3       2700
    1000-9999  9000   4       36000  ...

    例如 2901 = 9 + 180 + 2700 + 12 即一定是4位数,第12位   n = 12;
    数据为 = 1000 + (12 - 1)/ 4  = 1000 + 2 = 1002
    定位1002中的位置 = (n - 1) %  4 = 3    s.charAt(3) = 2;
    */
    public int findNthDigit(int n) {
        if (n < 10) return n;
        int digit = 1;
        long start = 1, count = 9;
        while (n > count){
            n -= count;
            digit ++;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

}
