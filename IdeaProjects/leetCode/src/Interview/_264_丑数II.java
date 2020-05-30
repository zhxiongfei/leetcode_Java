package Interview;

/*
编写一个程序，找出第 n 个丑数。

        丑数就是质因数只包含 2, 3, 5 的正整数。

        示例:

        输入: n = 10
        输出: 12
        解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
        说明:  

        1 是丑数。
        n 不超过1690。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/ugly-number-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;
import java.util.PriorityQueue;

public class _264_丑数II {

    /*
    *
    * 思路一：
    * 最小堆
    * 利用优先队列有自动排序的功能
    * 每次取出队头元素，存入队头元素*2、队头元素*3、队头元素*5
    * 但注意，像12这个元素，可由4乘3得到，也可由6乘2得到，所以要注意去重
    * */
    public int nthUglyNumber1(int n) {

        PriorityQueue<Long> queue = new PriorityQueue<>();

        long answer = 1;
        for (int i = 1; i < n; i++) {
            queue.add(answer * 2);
            queue.add(answer * 3);
            queue.add(answer * 5);

            answer = queue.remove();
            while (!queue.isEmpty() && queue.peek() == answer) queue.remove();
        }

        return (int)answer;
    }

    /*
    * 进一步使用 set 优化 去重
    * */
    public int nthUglyNumber2(int n) {

        PriorityQueue<Long> queue = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        int[] nums = {2,3,5};

        long answer = 1;
        for (int i = 1; i < n; i++) {
            long tmp = queue.peek();
            for (int j = 0; j < nums.length; j++) {
                if (set.contains(nums[i] * tmp)){
                    set.add(nums[i] * tmp);
                    queue.add(nums[i] * tmp);
                }
            }
            answer = queue.remove();
        }
        return (int)answer;
    }

    /*
     *
     * 思路三：
     * 动态规划 三指针法
     * 起初三根指针都指向数组 0 位置
     * 当数组的下一个是 *2 得出时， p2++, *3 得出时， p3++, *5 得出时， p5++
     * 注意有可能，同时是 2 和 3的公倍数， 比如6， 当下一个为6时， p2 和 p3同时 ++
     * */
    public int nthUglyNumber(int n) {
        int p2 = 0, p3 = 0, p5 = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[p2] * 2, dp[p3] * 3), dp[p5] * 5);

            if (dp[i] == dp[p2] * 2) p2 ++;
            if (dp[i] == dp[p3] * 3) p3 ++;
            if (dp[i] == dp[p5] * 5) p5 ++;
        }
        return dp[n - 1];
    }
}
