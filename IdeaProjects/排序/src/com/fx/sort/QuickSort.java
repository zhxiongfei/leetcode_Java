package com.fx.sort;

/*
 找出轴点元素的位置  轴点左右分别递归快速排序

 最坏复杂度 ： 如果轴点左右元素数量极度不均匀 O(n^2)
 平均复杂度 ： O*logN

 不稳定排序

 如果在比较时，如果该用<=,>= 会使轴点元素分割的子序列嫉妒不均匀。 导致最坏时间复杂度。
 */

import com.fx.tools.Integers;

public class QuickSort<T extends Comparable<T>> extends Sort <T>  {

    @Override
    protected void sort() {
        sort(0,array.length);
    }

    private void sort(int begain, int end){
        if (end - begain < 2) return;

        int middle = pivotIndex(begain,end);
        sort(begain,middle);
        sort(middle + 1,end);
    }

    private int pivotIndex(int begain,int end){

        T tmp = array[begain];
        boolean isRight = true;

        end --;
        while (begain < end){
            if (isRight){
                if (cmp(array[end],tmp) < 0){
                    array[begain++] = array[end];
                    isRight = false;
                }else {
                    end --;
                }
            }else {
                if (cmp(array[begain],tmp) > 0){
                    array[end--] = array[begain];
                    isRight = true;
                }else{
                    begain ++;
                }
            }
        }

        array[begain] = tmp;
        return begain;
    }
}
