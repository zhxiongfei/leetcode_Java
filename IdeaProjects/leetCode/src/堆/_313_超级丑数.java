package 堆;

/*
编写一段程序来查找第 n 个超级丑数。

        超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。

        示例:

        输入: n = 12, primes = [2,7,13,19]
        输出: 32
        解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
        说明:

        1 是任何给定 primes 的超级丑数。
         给定 primes 中的数字以升序排列。
        0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
        第 n 个超级丑数确保在 32 位有符整数范围内。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/super-ugly-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class _313_超级丑数 {
    public static int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 1) return 1;

        // 存放 primes 对应位置的数字 指针指向的位置
        int[] pointers = new int[primes.length];
        pointers[0] = 1;
        // 小顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // primes中所有添加到小顶堆中
        for (int i = 0; i < primes.length; i++) {
            heap.add(primes[i]);
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = heap.remove();
            int next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                int tmp = primes[j] * dp[pointers[j]];
                if (next < tmp) next = tmp;
            }

            if (i == 1 || dp[i - 1] != next) {
                heap.add(next);
            }

            for (int j = 0; j < pointers.length; j++) {
                if (next == primes[j] * dp[pointers[j]]) pointers[j]++;
            }

        }
        return dp[n - 1];
    }

    // 动态规划
    // 递推
    // 跟丑数题目
    public static int nthSuperUglyNumber1(int n, int[] primes) {
        if (n <= 1) return 1;

        // 存放 primes 对应位置的数字 指针指向的位置
        int[] pointers = new int[primes.length];

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                next = Math.min(next, primes[j] * dp[pointers[j]]);
            }

            dp[i] = next;

            for (int j = 0; j < pointers.length; j++) {
                if (next == primes[j] * dp[pointers[j]]) pointers[j]++;
            }

        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

        int[] nums = {2,7,13,19};
        nthSuperUglyNumber1(12,nums);
    }
}
