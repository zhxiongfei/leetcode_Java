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
public class BubbleSort extends Sort {

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
    }

    public static void main(String[] args) {

        Integer[] nums = {4,2,3,1,0,5};
        BubbleSort cls = new BubbleSort();
        cls.sort(nums);
    }

}
