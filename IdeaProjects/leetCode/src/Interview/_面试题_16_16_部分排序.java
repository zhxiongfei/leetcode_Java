package Interview;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。

        示例：

        输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
        输出： [3,9]
        提示：

        0 <= len(array) <= 1000000]
 */
public class _面试题_16_16_部分排序 {

    // 先排序，分别找到 左右 和 原数组不一样的index
    public static int[] subSort1(int[] array) {

        int[] clone = array.clone();
        Arrays.sort(array);

        int left = -1;
        int right = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != clone[i]){
                left = i;
                break;
            }
        }

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != clone[i]){
                right = i;
                break;
            }
        }

        return new int[]{left,right};
    }

    // 1 5 4 3 2 6 7 2 8
    // 从左到右 找 逆序对

    public static int[] subSort(int[] array){

        if (array == null || array.length == 0) return new int[]{-1,-1};

        int count = array.length;
        int max = array[0];
        int min = array[count - 1];
        int left = - 1;
        int right = -1;

        for (int i = 1; i < count; i++) {
            if (max > array[i]){
                right = i;
            }else {
                max = array[i];
            }
        }

        for (int i = count - 2; i >= 0; i--) {
            if (min < array[i]){
                left = i;
            }else {
                min = array[i];
            }
        }

        return new int[]{left,right};
    }

    public static void main(String[] args) {

    }
}
