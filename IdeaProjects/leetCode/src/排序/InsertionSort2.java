package 排序;

/*
* 经观察我们发现
* 在从前往后遍历时
* 前边数据已经有序
* 所以我们在查找插入位置时
* 可以使用二分查找，logN 的复杂度就能找到插入位置
* */

public class InsertionSort2 extends Sort {

    @Override
    protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {
            int element = array[begain];

            // 插入的位置
            int index = binarySearch(begain);

            // 在 index 和 begain之间的元素都向后挪动一位
            for (int i = begain; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = element;
        }
    }

    private int binarySearch(int index){
        int begain = 0;
        int end = index;
        while (begain < end){
            int mid = (begain + end) >> 1;
            if (array[mid] < array[index]){
                begain = mid + 1;
            }else {
                end = mid;
            }
        }
        return begain;
    }
}
