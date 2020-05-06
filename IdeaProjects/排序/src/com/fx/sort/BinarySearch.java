package com.fx.sort;

public class BinarySearch {

    // 查找v在数组中的位置
    public static int indexOf(int[] array,int v){
        if (array == null || array.length == 0) return -1;

        int begain = 0;
        int end = array.length - 1;

        while (begain < end){
            int mid = (begain + end) >> 1;
            if (array[mid] < v){
                begain = mid + 1;
            }else if (array[mid] > v){
                end = mid;
            }else{
                return mid;
            }
        }
        return -1;
    }

    // 查找v插入数组中的位置
    public static int insertionIndex(int[] array,int v){
        if (array == null || array.length == 0) return -1;

        int begain = 0;
        int end = array.length - 1;

        while (begain < end){
            int mid = (begain + end) >> 1;
            if (v < array[mid]){
                end = mid;
            }else{
                begain = mid + 1;
            }
        }
        return begain;
    }
}
