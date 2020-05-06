package com.fx.sort;

public class InsersionSort1<T extends Comparable<T>> extends Sort <T>{

    @Override
    protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {
            int cur = begain;
            while (cur > 0 && cmp(cur,cur - 1) < 0){
                swap(cur,cur - 1);
                cur --;
            }
        }
    }
}
