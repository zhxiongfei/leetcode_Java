package 探索头条;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 数组中的第K个最大元素 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);

            if (queue.size() > k){
                queue.poll();
            }
        }

        return queue.peek();
    }

}
