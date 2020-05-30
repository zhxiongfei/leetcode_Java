package Interview;

/*
请你帮忙设计一个程序，用来找出第 n 个丑数。

        丑数是可以被 a 或 b 或 c 整除的 正整数。

         

        示例 1：

        输入：n = 3, a = 2, b = 3, c = 5
        输出：4
        解释：丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。
        示例 2：

        输入：n = 4, a = 2, b = 3, c = 4
        输出：6
        解释：丑数序列为 2, 3, 4, 6, 8, 9, 12... 其中第 4 个是 6。
        示例 3：

        输入：n = 5, a = 2, b = 11, c = 13
        输出：10
        解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
        示例 4：

        输入：n = 1000000000, a = 2, b = 217983653, c = 336916467
        输出：1999999984

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/ugly-number-iii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _1201_丑数III {

    public static int nthUglyNumber(int n, int a, int b, int c) {
        // 看到 n 的范围就应该想到二分
        long low = Math.min(Math.min(a, b), c);

        long high = low * n;

        long res = binarySearch(low, high, a, b, c, n);
        long cnta = res % a;
        long cntb = res % b;
        long cntc = res % c;
        res -= Math.min(cnta, Math.min(cntb, cntc));

        return (int) res;
    }


    public static long binarySearch(long low , long high, int a, int b, int c, long n){
        if (low >= high) return low;

        long mid = (low + high) >> 1;

        long MCM_a_b = MCM(a, b);
        long MCM_a_c = MCM(a, c);
        long MCM_b_c = MCM(b, c);

        long MCM_a_b_c = MCM(MCM_a_b, c);

        long count = mid / a + mid / b + mid / c - mid / MCM_a_b - mid / MCM_a_c - mid / MCM_b_c + mid / MCM_a_b_c;

        if (count == n) return mid;

        if (count > n) return binarySearch(low, mid - 1, a, b, c, n);
        return binarySearch(mid + 1, high, a, b, c, n);
    }

    // 求最小公倍数 : 两数相乘 除以 最小公约数
    public static long MCM(long m, long n){

        long Multi = m * n;
        while (n > 0){
            long tmp = m % n;
            m = n;
            n = tmp;
        }
        return Multi / m;
    }

    public static void main(String[] args) {
        nthUglyNumber(12, 2,11,13);
    }
}
