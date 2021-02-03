package 堆;

import java.util.*;

/**
中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
        例如，
        [2,3,4] 的中位数是 3
        [2,3] 的中位数是 (2 + 3) / 2 = 2.5

        设计一个支持以下两种操作的数据结构：
        void addNum(int num) - 从数据流中添加一个整数到数据结构中。
        double findMedian() - 返回目前所有元素的中位数。

        示例：
        addNum(1)
        addNum(2)
        findMedian() -> 1.5
        addNum(3)
        findMedian() -> 2
        进阶:

        如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
        如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-median-from-data-stream
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

class MedianFinder {

    public static void main(String[] args) {
        MedianFinder cls = new MedianFinder();
        cls.addNum(1);
        cls.addNum(2);
        cls.addNum(3);
        cls.addNum(4);
        cls.addNum(5);
        cls.addNum(6);
        cls.addNum(7);
        cls.addNum(8);
        cls.addNum(9);
    }

    /**
     * 两个堆
     * 时间复杂度 : O(log N)
     * 空间复杂度 : O(N)
     * */
    // 最大堆中存放小元素
    PriorityQueue<Integer>maxHeap;
    // 最小堆中存放大元素
    PriorityQueue<Integer>minHeap;

    int count;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
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
        if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
        // 数量相等时， 返回 (最大堆堆顶元素 + 最小堆堆顶元素) >> 1
        return (minHeap.peek() + maxHeap.peek()) * 0.5;
    }

    /**
     * 全排序
     * addNum时间复杂度 : O(N *log n)
     * findMedian时间复杂度 : o(1)
     *
     * 时间复杂度很高
     * */
    List<Integer>list = new ArrayList<>();
    public void addNum1(int num) {
        list.add(num);
        Collections.sort(list);
    }

    public double findMedian1() {
        if ((list.size() & 1 ) == 1) return list.get(list.size() >> 1);
        int right = list.get(list.size() >> 1);
        int left = list.get((list.size() >> 1) - 1);
        return (left + right) / 2.0f;
    }

}
