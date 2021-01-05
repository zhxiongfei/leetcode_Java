package 堆;

import 链表.ListNode;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/*
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

        示例 1:

        输入: [3,2,1,5,6,4] 和 k = 2
        输出: 5
        示例 2:

        输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
        输出: 4
        说明:

        你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _215_数组中的第K个最大元素 {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;

        // 优先级队列
        // 小顶堆
        // 小顶堆中维持 k 个元素
        // N 个元素依次加入小丁堆。 当小丁堆中元素数量 > k时, 移除堆顶
        // 遍历完 N 个元素，堆中剩余 K 个元素，堆顶即是最终结果
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

        res = queue.peek();
        return res;
    }

    /**
     *
     * 快排减治思想
     *
     * 加随机
     *
     * */
    Random random = new Random();
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        return findKthLargestHelper(nums, 0, nums.length - 1, nums.length - k);
    }

    public int findKthLargestHelper(int[] nums, int begain, int end, int targetIdx){

        int pivotIdx = pivotIndex(nums, begain, end);
        if (pivotIdx == targetIdx) return nums[pivotIdx];
        if (pivotIdx > targetIdx){
            return findKthLargestHelper(nums, begain, pivotIdx - 1, targetIdx);
        }else {
            return findKthLargestHelper(nums, pivotIdx + 1, end, targetIdx);
        }
    }

    public int pivotIndex(int[] nums, int begain, int end){

        if (end > begain) {
            int randomIndex = begain + 1 + random.nextInt(end - begain);
            swap(nums, begain, randomIndex);
        }

        int tmp = nums[begain];
        boolean isright = true;

        while (begain < end){
            if (isright){
                if (nums[end] > tmp){
                    end --;
                }else {
                    nums[begain ++] = nums[end];
                    isright = false;
                }
            }else {
                if (nums[begain] < tmp){
                    begain ++;
                }else {
                    nums[end --] = nums[begain];
                    isright = true;
                }
            }
        }
        nums[begain] = tmp;
        return begain;
    }

    public void swap(int[] nums, int i1, int i2){
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

}
