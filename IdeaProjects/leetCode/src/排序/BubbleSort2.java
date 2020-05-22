package 排序;

public class BubbleSort2 extends Sort{
    @Override
    protected void sort() {
        // 优化
        // 记录交换位置
        // 局部交换时，后边的有序了，后边的不再比较
        for (int i = array.length - 1; i >= 0; i--) {
            // 当完全有序时，一轮扫描结束
            int sortedIdx = 0;
            for (int j = 0; j < i; j++) {
                if (cmp(j, j+1) > 0){
                    swap(j, j+1);

                    sortedIdx = j + 1;
                }
            }
            i = sortedIdx;
        }
    }
}
