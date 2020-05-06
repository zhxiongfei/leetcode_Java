package 数组;

public class _189_旋转数组 {

    // 方法1 分步旋转
    public void rotate1(int[] nums, int k) {
        k = k % nums.length;

        for (int i = 0; i < k; i++) {
            int tmp = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }

            nums[0] = tmp;
        }
    }

    // 方法2 额外空间
    public static void rotate2(int[] nums, int k) {

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[(nums.length - k + i) % nums.length];
        }

        for (int i = 0; i < res.length; i++) {
            nums[i] = res[i];
        }
    }

    // 方法3 备份k个元素
    public static void rotate3(int[] nums, int k) {

        k = k % nums.length;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = nums[nums.length - k + i];
        }

        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = res[i];
        }
    }

    // 方法3 反转
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums,0,nums.length - 1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length - 1);
    }

    public static void reverse(int[] nums, int start ,int end){
        while (start < end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;

            start ++;
            end --;
        }
    }

    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,6,7};
        rotate(nums,3);
        if (nums.length != 0){

        }
    }
}
