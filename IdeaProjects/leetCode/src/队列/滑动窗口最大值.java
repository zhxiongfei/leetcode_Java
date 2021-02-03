package 队列;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 滑动窗口最大值 {

    /*

    整体思路，利用双端队列, 对头存放最大值，新进来一个元素，从队尾开始while循环比较，当比队尾元素大时，
    移除队尾元素，当队列为空，或者找到队列中元素比当前元素大时结束循环，并将当前元素插入队尾。

    当队列对头存放的下标，不在滑动窗口范围即 < begain时，队头元素出队列

    当下标有效即下标 >= 0 时， 开始拿出队列头部元素放入 maxes结果数组中.

    经过一遍循环，时间复杂度O(n)
    需要额外k个元素大小的双端队列，空间复杂度O(k).

    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return null;
        if (k == 1) return nums;

        // 存放滑动窗口最大值数组
        int[] maxes = new int[nums.length - k + 1];

        // 双端队列 存放数组的下标
        Deque<Integer> queue = new LinkedList();
        for (int i = 0; i < nums.length; i++) {

            // 循环从尾部开始比较，比尾部元素小时，移除尾部元素
            while(!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]){
                queue.removeLast();
            }

            // 讲当前元素加入队尾（也有可能是队头，当前元素比之前对头元素还大时）
            queue.addLast(i);

            // begain 就是结果元素的下标
            int begain = i - k + 1;

            // 取出对头元素，即最大值下标，当下标无效（ < begain） 时，将其移除
            if (queue.peekFirst() < begain){
                queue.removeFirst();
            }

            // begain有效，即>=0时，赋值
            if (begain >= 0){
                maxes[begain] = nums[queue.peekFirst()];
            }
        }
        return maxes;
    }
}
