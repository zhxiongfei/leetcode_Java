package com.fx.sort;

public class InsertionSort2<T extends Comparable<T>> extends Sort <T> {

    @Override
    protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {
            int cur = begain;
            T element = array[cur];
            while (cur > 0 && cmp(element,array[cur - 1]) < 0){
                array[cur] = array[cur - 1];
                cur --;
            }
            array[cur] = element;
        }
    }

}
