package 数组;

/*
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

        此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

        注意:
        不能使用代码库中的排序函数来解决这道题。

        示例:

        输入: [2,0,2,1,1,0]
        输出: [0,0,1,1,2,2]
        进阶：

        一个直观的解决方案是使用计数排序的两趟扫描算法。
        首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
        你能想出一个仅使用常数空间的一趟扫描算法吗？
 */

/*

    三指针，红色指针是起始位置，蓝色指针是末尾位置，白色指针是与 0 交换的位置
        //               white red blue
        // 2 0 2 1 1 0    0     0   5
        // 0 0 2 1 1 2    0     0   4
        // 0 0 2 1 1 2    1     1   4
        // 0 0 2 1 1 2    2     2   4
        // 0 0 1 1 2 2    2     2   3
        // 0 0 1 1 2 2    2     3   3

* */

public class _75_颜色分类 {

    public static void swap(int[] nums ,int m , int n){
        int tmp = nums[m];
        nums[m] = nums[n];
        nums[n] = tmp;
    }

    public static void sortColors(int[] nums) {
        int red = 0;        // 起始位置
        int white = 0;      // 与0交换的位置
        int blue = nums.length - 1; // 末尾位置

        while (red <= blue){
            if (nums[red] == 2){
                // 蓝
                swap(nums,blue--,red);

            }else if (nums[red] == 1){
                // 白
                red ++;
            }else {
                // 红
                swap(nums,white++,red++);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};

        sortColors(nums);
    }
}
