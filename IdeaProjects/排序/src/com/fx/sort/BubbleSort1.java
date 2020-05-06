package com.fx.sort;

public class BubbleSort1 <T extends Comparable<T>> extends Sort <T>{

    /*
     * 依次比较两个相邻元素,
     * */
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begain = 1; begain <= end; begain++) {
                if (cmp(begain,begain - 1) < 0){
                    swap(begain,begain - 1);
                }
            }
        }
    }
}
