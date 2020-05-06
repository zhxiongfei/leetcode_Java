package com.fx.sort;

public class SelectionSort <T extends Comparable<T>> extends Sort <T>{

    // 选择排序
    /*
     * 从序列中找到最大的那个元素，然后与最末尾的元素交换位置
     * 执行完一轮后，最末尾的元素就是最大的元素。
     * */
    @Override
    protected void sort() {
        for (int end = array.length-1; end > 0; end--) {
            int idx = 0;
            for (int begain = 1; begain <= end; begain++) {
                if (cmp(begain,idx) > 0){
                    idx = begain;
                }
            }
            swap(end,idx);
        }
    }
}
