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
public class bubbleSort {

    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 冒泡排序
    public void bubbleSort(int[] nums){

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]){
                    swap(nums, j, j+1);
                }
            }
        }
    }

    // 优化
    // 全排序时， 则不再遍历
    public void bubbleSort1(int[] nums){

        for (int i = nums.length - 1; i >= 0; i--) {
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]){
                    swap(nums, j, j+1);

                    sorted = false;
                }
            }
            if (sorted) break;
        }

        System.out.println(nums);
    }

    // 优化
    // 记录交换位置
    // 局部交换时，后边的有序了，后边的不
    public void bubbleSort2(int[] nums){

        for (int i = nums.length - 1; i >= 0; i--) {
            // 当完全有序时，一轮扫描结束
            int sortedIdx = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]){
                    swap(nums, j, j+1);

                    sortedIdx = j + 1;
                }
            }
            i = sortedIdx;
        }

        System.out.println(nums);
    }

    public static void main(String[] args) {

        int[] nums = {4,2,3,1,0,5};
        bubbleSort cls = new bubbleSort();
        cls.bubbleSort2(nums);
    }

}
