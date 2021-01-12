package 剑指offer;

/*
    给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

            示例 1：

            输入: 2
            输出: 1
            解释: 2 = 1 + 1, 1 × 1 = 1
            示例 2:

            输入: 10
            输出: 36
            解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
            提示：

            2 <= n <= 58

            来源：力扣（LeetCode）
            链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
            著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _剑指_Offer_14_I_剪绳子 {

    /**
     *
     * 很显然
     * 3是拆分的最小数字，因为 3 只能拆分成 1 2。 乘积 比 其自身还小
     * 而 4 可以拆分 2 2乘积 == 4
     * 5 可以拆分成 2 3 乘积为6 > 5
     *
     *
     * */
    public static int cuttingRope(int n) {
        if (n <= 3) return n - 1;

        int res = 1;
        while (n > 4){
            res *= 3;
            n -= 3;
        }

        return res * n;
    }

    public static void main(String[] args) {
        cuttingRope(10);
    }
}
