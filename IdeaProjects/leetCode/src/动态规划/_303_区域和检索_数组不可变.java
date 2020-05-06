package 动态规划;

/*
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

        示例：

        给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

        sumRange(0, 2) -> 1
        sumRange(2, 5) -> -1
        sumRange(0, 5) -> -3
        说明:

        你可以假设数组不可变。
        会多次调用 sumRange 方法。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/range-sum-query-immutable
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _303_区域和检索_数组不可变 {

    public int[] _nums;
    private int[] _sum;
    public _303_区域和检索_数组不可变(int[] nums) {
        _nums = nums;
        _sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            _sum[i + 1] = _sum[i] + nums[i];
        }
    }

    // 暴力计算
    // 时间复杂度O(n ^ 2)
    // 空间复杂度 O(1)
    public int sumRange1(int i, int j) {
        int res = _nums[i];
        for (int k = i+1; k <= j; k++) {
            res += _nums[k];
        }
        return res;
    }

    // 思路2 ： 用hashmap存储每个i,j对的值。
    // 在构造函数中预先计算每个i,j对 对应的value。 时间复杂度 O(n ^ 2)
    // sumRange时，从hasmMap中取出对应的值。   时间复杂度 O(1)
    // 空间复杂度 O(n ^ 2)

    // 思路3 ： 预先存储值
    // 在构造函数中预先计算每个i+1对 对应的value。 时间复杂度 O(n)
    // sumRange时， _sum[j + 1] - _sum[i]。   时间复杂度 O(1)
    // 空间复杂度 O(n)
    public int sumRange(int i, int j) {
        return _sum[j + 1] - _sum[i];
    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */