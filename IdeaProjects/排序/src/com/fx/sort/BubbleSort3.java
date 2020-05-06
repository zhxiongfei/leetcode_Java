package com.fx.sort;

public class BubbleSort3 <T extends Comparable<T>> extends Sort <T>{

    // 优化 2,如果序列尾部已经局部有序,可以记录最后一次交换的位置，减少比较次数
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begain = 1; begain <= end; begain++) {
                if (cmp(begain,begain - 1) < 0){
                    swap(begain,begain - 1);
                    sortedIndex = begain;
                }
            }

            end = sortedIndex;
        }
    }
}
