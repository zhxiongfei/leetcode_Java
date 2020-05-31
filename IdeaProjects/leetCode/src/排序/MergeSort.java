package 排序;

/*
*
* 归并排序
* 算法流程：
*   先将数组分割，分割到不能再分割位置(只有一个元素)
*   再合并，将分割后的数组一次合并，直到合并成一个数组
* */

public class MergeSort extends Sort{
    private Integer[] leftArray;

    @Override
    protected void sort() {
        leftArray = new Integer[array.length >> 1];
        sort(0, array.length);
    }

    /*
    * 对数组进行分割
    * 左闭右开 [begain, end)
    * */
    private void sort(int begain, int end) {
        if (end - begain < 2) return;

        int mid = (begain + end) >> 1;
        sort(begain, mid);
        sort(mid, end);

        merge(begain, mid, end);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  }

    private void merge(int begain, int mid, int end){

        int li = 0, le = mid - begain;  // 左边数组 起始/结束位置
        int ri = mid, re = end;         // 右边数组 起始/结束位置
        int ai = begain;                // array的索引

        // 拷贝左边数组到leftArray
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begain + i];
        }

        while (li < le){
            if (ri < re && cmpElement(array[ri], leftArray[li]) < 0){
                array[ai ++] = array[ri ++];    // 拷贝右边数组到array
            }else {
                array[ai ++] = leftArray[li ++];// 拷贝左边数组到array
            }
        }// cmp 位置改为 <= 会失去平衡性
    }

}
