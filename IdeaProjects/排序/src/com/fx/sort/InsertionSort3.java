package com.fx.sort;

public class InsertionSort3<T extends Comparable<T>> extends Sort <T>  {

    @Override
    protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {

            T v = array[begain];
            int insertionIdx = insertionIndex(begain);
            for (int i = begain; i > insertionIdx; i--) {
                array[i] = array[i - 1];
            }
            array[insertionIdx] = v;
        }
    }

    // 查找v插入数组中的位置
    private int insertionIndex(int index){
        int begain = 0;
        int end = index;
        while (begain < end){
            int mid = (begain + end) >> 1;
            if (array[index].compareTo(array[mid]) < 0){
                end = mid;
            }else{
                begain = mid + 1;
            }
        }
        return begain;
    }
}
