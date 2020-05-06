package com.fx.sort;

public class HeapSort <T extends Comparable<T>> extends Sort <T>{

    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1){

            // 交换堆顶元素和尾部元素
            swap(0, --heapSize);

            // 恢复堆的性质
            siftDown(0);
        }
    }

    // 下滤
    private void siftDown(int index){
        T element = array[index];

        // 非叶子节点 子节点比较 交换
        // index < 第一个叶子结点的索引
        // index < 非叶子结点的数量
        int half = heapSize >> 1;
        while (index < half){
            // index的结点有两种情况
            // 只有左子节点
            // 左右子节点都有

            // 默认取出左子节点的值跟element比较
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;
            if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0){
                child = array[childIndex = rightIndex];
            }

            // 子节点小
            if (cmp(element, child) >= 0) break;

            // 子结点大 交换
            array[index] = child;
            index = childIndex;
        }

        array[index] = element;
    }

}
