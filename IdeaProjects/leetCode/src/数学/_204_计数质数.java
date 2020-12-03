package 数学;

import java.util.Arrays;

/**
统计所有小于非负整数 n 的质数的数量。
        示例 1：

        输入：n = 10
        输出：4
        解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
        示例 2：

        输入：n = 0
        输出：0
        示例 3：

        输入：n = 1
        输出：0
         

        提示：

        0 <= n <= 5 * 106

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/count-primes
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _204_计数质数 {

    /**
     *
     * 暴力法 :
     * 思路 ：
     * 1 : 写一个方法 isPrime 判断是否是质数, 此步骤需要 O(n) 的时间复杂度
     * 2 : 在遍历 [2, n - 1] 之间质数的数量, 此步需要 O(n) 的时间复杂度
     * 3 : 整体时间复杂度 : O(n ^ 2)
     *
     * 优化一 :
     * 数字 n 不可能整除 > n/2 的数字, 所以 isPrime 的循环可以砍掉一半
     * 时间复杂度仍然是 : O(n ^ 2)
     *
     * 优化二 :
     * 2 * 6 = 12
     * 3 * 4 = 12
     * 6 * 2 = 12
     * 4 * 3 = 12
     * 由上述几个例子可以看到.
     * 计算 4 * 3 和 6 * 2 是不必要的。
     * 所以我们只需要考虑 <= 根号n
     * 时间复杂度下降到了 O(n ^ 1.5)
     *
     * */
    public int countPrimes1(int n) {
        int res = 0, i = 1;
        while (++i < n){
            if (isPrime(i)) {
                System.out.println(i);
                res ++;
            }
        }
        return res;
    }

    private boolean isPrime(int n){
        // i < n
        // i <= (n / 2)
        // i * i <= n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * 厄拉多塞筛法
     * 素数筛选题解 :
     * https://leetcode-cn.com/problems/count-primes/solution/kuai-lai-miao-dong-shai-zhi-shu-by-sweetiee/
     * */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i*i <= n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        _204_计数质数 cls = new _204_计数质数();
        cls.countPrimes1(10);
    }

}
