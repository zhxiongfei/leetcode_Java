#### 堆排序(Heap Sort)



- 堆排序可以认为是选择排序的一种优化。

- 选择排序每一轮都在选择最大值

- 而选择最大值，最优的数据结构是堆

- 故用堆排序 优化 选择排序

  

##### 执行流程

- 对序列进行原地建堆 (heapify)
- 重复执行以下操作，直到堆的元素数量为1
  - 交换堆顶元素与尾元素
  - 堆的元素数量减1
  - 对 0 位置的元素进行 1 次 siftDown操作



代码如下:

```java

    protected void sort() {

        heapSize = array.length;

			  // 原地建堆
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
```

