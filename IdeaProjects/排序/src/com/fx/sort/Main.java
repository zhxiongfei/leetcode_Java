package com.fx.sort;

import com.fx.tools.Asserts;
import com.fx.tools.Integers;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){

//        int[] array = {2,4,6,8,10};
//        Asserts.test(BinarySearch.indexOf(array,3) == -1);

        Integer[] array = Integers.random(10000,1,1000000);
        testSorts(array,
                new SelectionSort(),
                new HeapSort(),
                new BubbleSort3(),
                new InsersionSort1(),
                new InsertionSort2(),
                new InsertionSort3(),
                new MergeSort(),
                new QuickSort()
        );
    }

    static void testSorts(Integer[] array,Sort... sorts){
        for (Sort sort : sorts){
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);

            Asserts.test(Integers.isAscOrder(newArray));
        }

        Arrays.sort(sorts);
        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
