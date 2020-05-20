package 排序;

import java.util.Enumeration;
import java.util.PriorityQueue;

public class HeapSort extends Sort{

    /*
     *
     * 选择排序每一轮都在选最大
     * 选最值用堆优化
     * 故用堆排序优化选择排序
     *
     * 我们先看看选择排序的代码
     * 如果把选择排序选最值的代码，使用堆
     * 就可以把选最值代码时间复杂度优化值 O(log N)
     * 整体的时间复杂度为 O(N * log N)
     * */

    private int heapSize;
    // 堆排序
    @Override
    protected void sort() {

        heapSize = array.length;

        heaptify();

        while (heapSize > 1){
            // 交换堆顶元素 和 尾部元素
            swap(0, --heapSize);

            // 交换过堆顶元素下滤, 恢复堆的性质
            siftDown(0);
        }
    }

    // 原地建堆
    private void heaptify(){
        // 自下而上的下滤
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    // 下滤
    private void siftDown(int index){
        int element = array[index];

        // 非叶子节点
        // 与子节点比较，如果大，则交换

        // half 非叶子节点的数量
        int half = heapSize >> 1;
        while (index < half){

            // 左子节点的下标
            int childIdx = (index << 1) + 1;
            int child = array[childIdx];

            int rightIdx = childIdx + 1;

            // 有右子节点 且 右子节点的值 > 左子节点的值
            if (rightIdx < heapSize && array[rightIdx] > child){
                childIdx = rightIdx;
                child = array[childIdx];
            }

            // 子节点小 退出循环
            if (element >= child) break;

            // 子节点大 交换
            array[index] = child;

            index = childIdx;
        }

        array[index] = element;
    }

    public static void main(String[] args) {
        HeapSort cls = new HeapSort();
        Integer[] nums = {4,2,3,1,5,8,7,6};
        cls.sort(nums);

        if (nums == null){

        }
    }

}
