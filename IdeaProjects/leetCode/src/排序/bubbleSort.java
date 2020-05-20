package 排序;

/*
*
* 冒泡排序
* 依次比较两个相邻元素
* 如果前者比后者大，交换两者
* 一轮比较完毕，则最后一个元素为最大者
* 再进行第二轮比较，[0 , nums.length - 1]
* .
* .
* .
* 直到每一位末尾
* */
public class bubbleSort extends Sort {

    // 冒泡排序
    @Override
    protected void sort() {

        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (cmp(j, j+1) > 0){
                    swap(j, j+1);
                }
            }
        }


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


        // 优化
        // 记录交换位置
        // 局部交换时，后边的有序了，后边的不
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

    public static void main(String[] args) {

        Integer[] nums = {4,2,3,1,0,5};
        bubbleSort cls = new bubbleSort();
        cls.sort(nums);
    }

}
