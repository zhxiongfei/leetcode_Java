package 排序;

public class QuickSort extends Sort {


    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begain ,int end){
        // 至少有两个元素
        if (end - begain < 2) return;

        int mid = pivotIndex(begain, end);
        sort(begain, mid);
        sort(mid + 1, end);
    }

    int pivotIndex(int begain ,int end){

        int tmp = array[begain];
        boolean isright = true;

        end --;
        while (begain < end){
            // 从右往左找
            if (isright){
                if (array[end] > tmp){
                    end --;
                }else {
                    array[begain ++] = array[end];
                    isright = false;
                }
            }else{
                // 从左往右找
                if (array[begain] < tmp){
                    begain ++;
                }else {
                    array[end --] = array[begain];
                    isright = true;
                }
            }
        }

        array[begain] = tmp;
        return begain;
    }
}
