package 滑动窗口;

import java.util.PriorityQueue;

/**
中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。

        例如：

        [2,3,4]，中位数是 3
        [2,3]，中位数是 (2 + 3) / 2 = 2.5
        给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
        示例：

        给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
        窗口位置                      中位数
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       1
        1 [3  -1  -3] 5  3  6  7      -1
        1  3 [-1  -3  5] 3  6  7      -1
        1  3  -1 [-3  5  3] 6  7       3
        1  3  -1  -3 [5  3  6] 7       5
        1  3  -1  -3  5 [3  6  7]      6
        因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。

        提示：
        你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
        与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sliding-window-median
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _480_滑动窗口中位数 {

    /**
     * 295.数据流的中位数进阶版本
     * */
    class MedianFinder {

        // 最大堆中存放小元素
        PriorityQueue<Integer>maxHeap;
        // 最小堆中存放大元素
        PriorityQueue<Integer>minHeap;

        int count;
        public MedianFinder() {
            maxHeap = new PriorityQueue<>(((o1, o2) -> Integer.compare(o2, o1)));
            minHeap = new PriorityQueue<>();
            count = 0;
        }

        // 添加元素过程中
        // 保证
        // 最大堆堆顶元素 <= 最小堆堆顶元素
        // 最大堆元素数量 = (最小堆元素数量 + 1) 或者 最小堆元素数量
        public void addNum(int num) {
            count += 1;
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            // 偶数
            if ((count & 1) != 0) maxHeap.add(minHeap.poll());
        }

        public double findMedian() {
            // 当最大堆中元素数量 > 最小堆元素数量时， 返回最大堆堆顶元素
            if (maxHeap.size() > minHeap.size()) return (double)maxHeap.peek();
            // 数量相等时， 返回 (最大堆堆顶元素 + 最小堆堆顶元素) >> 1
            return ((double)minHeap.peek() + (double)maxHeap.peek()) * 0.5;
        }

        public void removeNum(int num){
            if ((count & 1) == 0) {
                if (minHeap.contains(num)){
                    minHeap.remove(num);
                }else {
                    maxHeap.remove(num);
                    maxHeap.offer(minHeap.poll());
                }
            }else{
                if (maxHeap.contains(num)){
                    maxHeap.remove(num);
                }else {
                    minHeap.remove(num);
                    minHeap.offer(maxHeap.poll());
                }
            }
            count --;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) return null;
        double[] res = new double[nums.length - k + 1];
        MedianFinder finder = new MedianFinder();

        for (int i = 0; i < k; i++) {
            finder.addNum(nums[i]);
        }

        for (int i = k; i < nums.length; i ++){
            int idx = i - k;
            res[idx] = finder.findMedian();
            finder.addNum(nums[i]);
            finder.removeNum(nums[i - k]);
        }
        res[res.length - 1] = finder.findMedian();
        return res;
    }

    public static void main(String[] args) {
        _480_滑动窗口中位数 cls = new _480_滑动窗口中位数();
        int[] nums = {
                -2147483648,-2147483648,2147483647,-2147483648,1,3,
                -2147483648,-100,8,17,22,-2147483648,
                -2147483648,2147483647,2147483647,2147483647,2147483647,
                -2147483648,2147483647,-2147483648
        };
        cls.medianSlidingWindow(nums, 6);
    }
}
