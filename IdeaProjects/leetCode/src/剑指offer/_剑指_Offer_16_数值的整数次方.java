package 剑指offer;

/**
实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
        示例 1:

        输入: 2.00000, 10
        输出: 1024.00000
        示例 2:

        输入: 2.10000, 3
        输出: 9.26100
        示例 3:

        输入: 2.00000, -2
        输出: 0.25000
        解释: 2-2 = 1/22 = 1/4 = 0.25
         

        说明:

        -100.0 < x < 100.0
        n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _剑指_Offer_16_数值的整数次方 {

    /**
     * 递归
     * 0.00001
     * 2147483647
     *
     * StackOverflowError
     * */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n > 1){
            return myPow(x, n - 1) * x;
        }
        return myPow(x, n - 1) / x;
    }

    /**
     * 快速幂算法
     * */
    double myPowHelper(double x, int n){
        if (n == 1) return x;
        if (n == 0) return 1;
        double half = myPowHelper(x, n / 2);
        return half * half * (n % 2 == 0 ? 1 : x);
    }

    public double myPow1(double x, int n) {
        if (n == 0 || x == 1) return 1;
        if (n < 0) return 1/myPowHelper(x, Math.abs(n));
        return myPowHelper(x, n);
    }

    public static void main(String[] args) {
        _剑指_Offer_16_数值的整数次方 cls = new _剑指_Offer_16_数值的整数次方();
        double res = cls.myPow1(2.0000, -2147483648);
        System.out.println(res);
    }

}
