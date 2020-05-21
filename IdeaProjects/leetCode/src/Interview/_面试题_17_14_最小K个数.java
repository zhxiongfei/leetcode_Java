package Interview;

/*
设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

        示例：

        输入： arr = [1,3,5,7,2,4,6,8], k = 4
        输出： [1,2,3,4]
        提示：

        0 <= len(arr) <= 100000
        0 <= k <= min(100000, len(arr))

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/smallest-k-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.time.Year;
import java.util.Comparator;
import java.util.PrimitiveIterator;
import java.util.PriorityQueue;

public class _面试题_17_14_最小K个数 {

    /*
    * 使用最大堆,堆中保持K个元素，依次把 arr 中的数据加入堆
    * 最终堆中剩余的就是最小的前 K 个数
    * 时间复杂度 : O(N * log K)
    * 空间复杂度 : O(K)
    * */
    public int[] smallestK(int[] arr, int k) {

        // 最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
            if (queue.size() > k){
                queue.poll();
            }
        }

        int[] res = new int[k];
        int i = 0;
        while (!queue.isEmpty()){
            res[i ++] = queue.poll();
        }
        return res;
    }

    /*
    * 利用最小堆，最小堆的话，所有的数加入堆中
    * 然后，从最小堆中依次取出前K个堆顶元素即可
    * 时间复杂度 : O(N * log K)
    * 空间复杂度 : O(N)
    * */
    public int[] smallestK1(int[] arr, int k) {

        if (k == 0 || arr == null || arr.length == 0) return new int[0];
        // 最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);
        }

        int[] res = new int[k];
        int i = 0;
        while (i < k){
            res[i ++] = queue.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        _面试题_17_14_最小K个数 cls = new _面试题_17_14_最小K个数();

        int[] nums = {1,3,5,7,2,4,6,8};
        int[] res = cls.smallestK1(nums, 4);
        if (res == null){

        }
    }
}
