package com.fx.sort;

public class BubbleSort2 <T extends Comparable<T>> extends Sort <T>{

    // 优化 1,已经有序，停止排序
    @Override
    protected void sort() {

        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begain = 1; begain <= end; begain++) {
                if (cmp(begain,begain - 1) < 0){
                    swap(begain,begain - 1);
                    sorted = false;
                }
            }

            if (sorted) break;
        }
    }
}
