package 排序;

/*
*
* 选择排序
* 找出数组中的最大者，和数组末尾元素进行交换
* 执行完一轮后， 数组末尾的元素就是最大元素
* 依次找出剩余元素中最大者，交换
* */
public class SelectionSort {

    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void selectionSort(int[] nums){
        for (int i = nums.length - 1; i > 0; i--) {
            int maxIdx = 0;
            for (int j = 1; j <= i; j++) {
                if (nums[j] > nums[maxIdx])
                    maxIdx = j;
            }
            swap(nums,maxIdx, i);
        }
    }

    public static void main(String[] args) {
        SelectionSort cls = new SelectionSort();
        int[] nums = {4,2,3,1,5,8,7,6};
        cls.selectionSort(nums);
    }
}
