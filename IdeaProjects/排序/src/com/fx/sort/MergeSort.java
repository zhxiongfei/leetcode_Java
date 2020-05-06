package com.fx.sort;


import java.util.Comparator;

public class MergeSort <T extends Comparable<T>> extends Sort <T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0,array.length);
    }

    /*
    * 对[begain,end)范围进行归并排序
    * */
    private void sort(int begain,int end){
        if (end - begain < 2) return;

        int mid = (begain + end) >> 1;
        sort(begain,mid);
        sort(mid,end);

        merge(begain,mid,end);
    }

    /*
    * 将[begain,mid) , [mid, end) 范围合并成一个有序序列
    * */
    private void merge(int begain,int mid,int end){
        // 左边数组的起始索引 （新数组）
        int li = 0;
        int le = mid - begain;

        // 右边数组 基于array
        int ri = mid;
        int re = end;

        int ai = begain;    // array 的索引

        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begain + i];
        }

        // 左边结束，整个归并结束
        while (li < le){
            if (ri < re && cmp(array[ri],leftArray[li]) < 0){
                array[ai ++] = array[ri ++];
            }else{
                array[ai ++] = leftArray[li ++];
            }
        }
    }
}
