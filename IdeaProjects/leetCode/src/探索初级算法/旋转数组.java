package 探索初级算法;

/*
输入: [1,2,3,4,5,6,7] 和 k = 3
        输出: [5,6,7,1,2,3,4]
        解释:
        向右旋转 1 步: [7,1,2,3,4,5,6]
        向右旋转 2 步: [6,7,1,2,3,4,5]
        向右旋转 3 步: [5,6,7,1,2,3,4]
 */

public class 旋转数组 {

    // 方法1 分步旋转
    // 时间复杂度 O(N * K)
    // 空间复杂度 O(1)
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

    // 方法2
    // 备份 k 个元素
    // 时间复杂度 O(N)
    // 空间复杂度 O(K)
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        if (nums.length < k) {
            // 保证 k < 数组长度
            rotate(nums, k % nums.length);
            return;
        }

        // 备份 k个元素
        int[] tmp = new int[k];
        for (int i = nums.length - k; i < nums.length; i++) {
            tmp[i + k - nums.length] = nums[i];
        }

        for (int i = nums.length - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }

    // 方法3
    // 整体反转 数组
    // 再分别反转 前k个 和 后 N-k个
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        if (nums.length < k) {
            // 保证 k < 数组长度
            rotate2(nums, k % nums.length);
            return;
        }

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k);
        reverse(nums, k+1, nums.length - 1);
    }

    public void reverse(int[] nums, int left, int right){
        while (left < right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;

            left ++;
            right --;
        }
    }
}