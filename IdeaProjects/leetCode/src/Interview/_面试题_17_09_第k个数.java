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


    /*
     为了叙述方便，我们把符合题目要求的数这些书称为丑数
     不难发现，一个丑数总是由前边的某一个丑数 *3 / *5 /*7得到

     如果把丑数数列叫做 ugly[i], 那么考虑一下三个数列

     ugly[0]*3,ugly[1]*3,ugly[2]*3,ugly[3]*3,ugly[4]*3,ugly[5]*3……
     ugly[0]*5,ugly[1]*5,ugly[2]*5,ugly[3]*5,ugly[4]*5,ugly[5]*5……
     ugly[0]*7,ugly[1]*7,ugly[2]*7,ugly[3]*7,ugly[4]*7,ugly[5]*7……

     上面这三个数列合在一起就形成了新的，更长的丑数数列

     如何合在一起呢？ 其实就是一个合并有序线性表的问题。

     定义三个index 分别指向上面三个数列，下一个丑数一定是这三个index代表的值中最小的那个。然后相应的index ++即可。

     举个例子
     初始值 ugly[0]=1; index1=0; index2=0; index3=0
     ugly[1]=Min(ugly[index1]*3,ugly[index2]*5,ugly[index3]*7)
        =Min(1*3,1*5,1*7)
        =3
    于是 index1++;

    ugly[2]=Min(ugly[index1]*3,ugly[index2]*5,ugly[index3]*7)
    =Min(3*3,1*5,1*7)
    =5
    于是 index2++;
     */
    public int getKthMagicNumber2(int k){
        int [] dp = new int[k];
        dp[0] = 1;

        int p3 = 0, p5 = 0, p7 = 0;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(dp[p3] * 3, Math.min(dp[p5] * 5, dp[p7] * 7));

            if (dp[i] == dp[p3] * 3) p3 ++;
            if (dp[i] == dp[p5] * 5) p5 ++;
            if (dp[i] == dp[p7] * 7) p7 ++;
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
