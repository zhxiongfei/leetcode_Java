package 贪心;

/*
有一堆石头，每块石头的重量都是正整数。

        每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

        如果 x == y，那么两块石头都会被完全粉碎；
        如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
        最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

         

        提示：

        1 <= stones.length <= 30
        1 <= stones[i] <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/last-stone-weight
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _1046_最后一块石头的重量 {

    /**
     * 最后剩下的肯定只有1个。
     * 升序排序数组。 最后一个 等于 最后一个 - 倒数第二个。
     * 并且把倒数第二个值为0。
     *
     * 重新排序数组。
     * 循环
     * 直至数组只剩一个不为0元素
     *
     * 时间复杂度 : O(N ^ 2 * logN)
     * 空间复杂度 : O(1)
     * */
    static int lastStoneWeight1(int[] stones) {

        if (stones == null || stones.length == 0) return 0;
        if (stones.length == 1) return stones[0];

        for (int i = 0; i < stones.length; i++) {
            Arrays.sort(stones);

            stones[stones.length -1] -= stones[stones.length - 2];
            stones[stones.length - 2] = 0;
        }

        return stones[stones.length - 1];
    }

    /**
     * 最大堆
     * 时间复杂度 : O(N * logN)
     * 空间复杂度 : O(N)
     * */
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < stones.length; i++) {
            queue.add(stones[i]);
        }

        while (queue.size() > 1){
            int n1 = queue.poll();
            int n2 = queue.poll();
            int diff = Math.abs(n1 - n2);
            queue.add(diff);
        }

        return queue.peek();
    }

    public static void main(String[] args){

        int[] stones = {4,2,4,6,2,1,4,5,6,29};
        lastStoneWeight(stones);
    }
}
