package Interview;

/*
有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

        示例 1:

        输入: k = 5

        输出: 9

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/get-kth-magic-number-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class _面试题_17_09_第k个数 {

    /*
    * 解题思路：
    * 每个 素因子，乘以 3, 5, 7 也是素因子
    *
    * 利用最小堆
    * */
    public int getKthMagicNumber(int k) {

        // 默认为最小堆
        PriorityQueue<Long> queue = new PriorityQueue<>();

        queue.offer(1L);
        long number = 1;
        for (int i = 0; i < k; i++) {

            number = queue.poll();
            if (!queue.contains(number * 3))
                queue.offer(number * 3);

            if (!queue.contains(number * 5))
                queue.offer(number * 5);

            if (!queue.contains(number * 7))
                queue.offer(number * 7);
        }

        return (int)number;
    }

    // 利用set优化，是否存在时的查找次数
    public int getKthMagicNumber1(int k){
        // 默认为最小堆
        PriorityQueue<Long> queue = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();

        queue.offer(1L);
        long number = 1;
        for (int i = 0; i < k; i++) {

            number = queue.poll();
            if (!set.contains(number * 3)){
                set.add(number * 3);
                queue.offer(number * 3);
            }

            if (!set.contains(number * 5)){
                set.add(number * 5);
                queue.offer(number * 5);
            }

            if (!set.contains(number * 7)){
                set.add(number * 7);
                queue.offer(number * 7);
            }
        }

        return (int)number;
    }

    // 动态规划
    public int getKthMagicNumber2(int k){
        int [] dp = new int[k];
        dp[0] = 1;

        for (int i = 0; i < k; i++) {

            dp[i] = 
        }

        return dp[k - 1];
    }

    // 思路二
    // 动态规划

    public static void main(String[] args) {
        _面试题_17_09_第k个数 cls = new _面试题_17_09_第k个数();
        cls.getKthMagicNumber(10);
    }
}
