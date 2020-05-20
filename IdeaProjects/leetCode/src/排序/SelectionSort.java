package 排序;

import java.awt.desktop.SystemSleepEvent;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/*
*
* 选择排序
* 找出数组中的最大者，和数组末尾元素进行交换
* 执行完一轮后， 数组末尾的元素就是最大元素
* 依次找出剩余元素中最大者，交换
* */
public class SelectionSort extends Sort{

    @Override
    protected void sort() {
        for (int i = array.length - 1; i > 0; i--) {
            int maxIdx = 0;
            for (int j = 1; j <= i; j++) {
                if (cmp(j, maxIdx) > 0){
                    maxIdx = j;
                }
            }
            swap(maxIdx, i);
        }
    }

    public static void main(String[] args) {
        SelectionSort cls = new SelectionSort();
        Integer[] nums = {4,2,3,1,5,8,7,6};

        cls.sort(nums);

        if (nums == null){

        }
    }
}
