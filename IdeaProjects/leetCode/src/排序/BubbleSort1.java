package 排序;

public class BubbleSort1 extends Sort{

    @Override
    protected void sort() {
        // 优化
        // 全排序时， 则不再遍历
        for (int i = array.length - 1; i >= 0; i--) {
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (cmp(j, j+1) > 0){
                    swap(j, j+1);

                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }
}
