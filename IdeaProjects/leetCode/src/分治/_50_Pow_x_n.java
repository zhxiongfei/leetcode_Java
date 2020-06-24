package 分治;

/*
实现 pow(x, n) ，即计算 x 的 n 次幂函数。

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
        链接：https://leetcode-cn.com/problems/powx-n
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _50_Pow_x_n {


    /**
     *
     * 快速幂算法
     *
     * */
    // 快速幂
    double quickMul(double x, long N) {
        // 任何数(除0外) 的 0次幂都是1
        if (N == 0) return 1.0;
        // 先计算 x 的 N / 2幂
        double y = quickMul(x, N / 2);
        // 如果 N 为偶数， 则 x 的 N次幂等于 y * y
        // 如果 N 为奇数， 则 x 的 N次幂等于 y * y * x
        return N % 2 == 0 ? y * y : y * y * x;
    }

    double myPow(double x, int n) {
        long N = n;
        // 如果 n < 0，结果为 1/快速幂
        // 如果 n >= 0, 结果为 快速幂
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    double quickMul1(double x, long N) {

        double ans = 1.0;
        //初始值为 x
        double current_product = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= current_product;
            }
            // 将上次结果不断地平方
            current_product *= current_product;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

}
