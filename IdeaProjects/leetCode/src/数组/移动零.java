package 数组;

public class 移动零 {

    public void moveZeroes(int[] nums) {

        if (nums == null || nums.length <= 1) return;

        int left = 0, right = 0, count = 0;
        while (right < nums.length){
            if (nums[right] == 0) {
                right ++;
                count ++;
                continue;
            }
            nums[left ++] = nums[right ++];
        }

        while (count > 0){
            nums[nums.length - count] = 0;
            count --;
        }
    }

}
