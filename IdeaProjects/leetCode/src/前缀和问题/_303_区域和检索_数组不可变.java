package 前缀和问题;

public class _303_区域和检索_数组不可变 {

    int[]preSum = null;
    public _303_区域和检索_数组不可变(int[] nums) {
        int length = nums.length;
        if (length == 0) return;
        preSum = new int[length];

        preSum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            int num = nums[i];
            preSum[i] = num + preSum[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        if (preSum == null) return 0;
        return preSum[j] - (i > 0 ?  preSum[i - 1] : 0);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        _303_区域和检索_数组不可变 cls = new _303_区域和检索_数组不可变(nums);
        int res1 = cls.sumRange(2,4);
        System.out.println(res1);
    }

}
