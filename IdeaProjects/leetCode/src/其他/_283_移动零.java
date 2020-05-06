package 其他;

/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

        示例:

        输入: [0,1,0,3,12]
        输出: [1,3,12,0,0]
        说明:

        必须在原数组上操作，不能拷贝额外的数组。
        尽量减少操作次数。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/move-zeroes
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _283_移动零 {

    // 暴力解法 时间复杂度 n ^ 2
    public void moveZeroes1(int[] nums) {

        int len = nums.length;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] == 0 && i != len - 1){
                for (int j = 0; j < len - i - 1; j++) {
                    nums[i + j] = nums[i + j + 1];
                }

                nums[len - 1] = 0;
            }
        }
    }

    private void swap(int[] nums, int i ,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 增加一个指针j。 i指向元素不为0时，j复制为此元素。 最后补齐Zero
    public void moveZeroes2(int[] nums) {

        int len = nums.length;
        int j = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] != 0){
                nums[j++] = nums[i];
            }
        }

        for (int i = j; i < len; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes(int[] nums) {

        int len = nums.length;
        int j = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] != 0){
                nums[j] = nums[i];
                if (i != j){
                    nums[i] = 0;
                }
                j ++;
            }
        }

        for (int i = j; i < len; i++) {
            nums[i] = 0;
        }
    }
}
