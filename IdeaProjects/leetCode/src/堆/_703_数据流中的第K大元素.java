package 堆;

/*
设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。

        你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。

        示例:

        int k = 3;
        int[] arr = [4,5,8,2];
        KthLargest kthLargest = new KthLargest(3, arr);
        kthLargest.add(3);   // returns 4
        kthLargest.add(5);   // returns 5
        kthLargest.add(10);  // returns 5
        kthLargest.add(9);   // returns 8
        kthLargest.add(4);   // returns 8
        说明:
        你可以假设 nums 的长度≥ k-1 且k ≥ 1。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.PriorityQueue;

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

public class _703_数据流中的第K大元素{

    private int limit = 0;
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    public _703_数据流中的第K大元素(int k, int[] nums) {

        limit = k;
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() < k){
                heap.offer(nums[i]);
            }else if (heap.peek() < nums[i]){
                heap.poll();
                heap.offer(nums[i]);
            }
        }
    }

    public int add(int val) {

        if (heap.size() < limit){
            heap.offer(val);
        }else if (val > heap.peek()){
            heap.poll();
            heap.offer(val);
        }

        return heap.peek();
    }

    public static void main(String[] args) {

        int [] nums = {4,5,8,2};

        _703_数据流中的第K大元素 test = new _703_数据流中的第K大元素(3,nums);
        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }

}
