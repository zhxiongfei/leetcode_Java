package 排序;
import tools.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] array = Integers.random(10000,1,20000);
        testSorts(array,
                new MergeSort(),
                new SelectionSort(),
                new HeapSort(),
                new BubbleSort2(),
                new InsertionSort(),
                new InsertionSort1(),
                new InsertionSort2(),
                new QuickSort()
        );
    }

    static void testSorts(Integer[] array,Sort... sorts){
        for (Sort sort : sorts){
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);

            Asserts.test(Integers.isAscOrder(newArray));
        }

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
