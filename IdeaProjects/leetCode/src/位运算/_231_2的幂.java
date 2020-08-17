package 位运算;

/**
给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

        示例 1:

        输入: 1
        输出: true
        解释: 20 = 1
        示例 2:

        输入: 16
        输出: true
        解释: 24 = 16
        示例 3:

        输入: 218
        输出: false

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/power-of-two
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _231_2的幂 {

    public boolean isPowerOfTwo(int n) {
        if(n == 0) return false;
        while(n % 2 == 0) n /= 2;
        return n == 1;
    }

    /**
     * 若 n = 2 ^ x， 且 x 为自然数(即 n为 2的幂), 则一定满足以下条件
     * 1，恒有 n & (n - 1) == 0，这是因为
     *    n为二进制最高位为1， 其余所有为为0
     *    n - 1二进制最高位为0， 其余所有位为1
     *
     * 2，一定满足 n > 0
     * */
    public boolean isPowerOfTwo1(int n) {
        if(n == 0) return false;
        long x = (long)n;
        return (x & (x - 1)) == 0;
    }
}
