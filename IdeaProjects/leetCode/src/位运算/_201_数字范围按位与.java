package 位运算;

/**
给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

        示例 1: 

        输入: [5,7]
        输出: 4
        示例 2:

        输入: [0,1]
        输出: 0

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _201_数字范围按位与 {

    public int rangeBitwiseAnd(int m, int n) {

        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

}
