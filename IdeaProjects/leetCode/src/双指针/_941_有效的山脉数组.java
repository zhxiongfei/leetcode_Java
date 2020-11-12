package 双指针;

/**
给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
        让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
        A.length >= 3
        在 0 < i < A.length - 1 条件下，存在 i 使得：
        A[0] < A[1] < ... A[i-1] < A[i]
        A[i] > A[i+1] > ... > A[A.length - 1]
        示例 1：

        输入：[2,1]
        输出：false
        示例 2：

        输入：[3,5,5]
        输出：false
        示例 3：

        输入：[0,3,2,1]
        输出：true

        提示：
        0 <= A.length <= 10000
        0 <= A[i] <= 10000 

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/valid-mountain-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _941_有效的山脉数组 {

    /**
     * 线性扫描
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * 时间击败 : 45%
     * */
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length <3) return false;

        int n1 = A[0], n2 = A[1];
        if (n1 > n2) return false;
        boolean increase = true;
        int prev = n2;

        for (int i = 2; i < A.length; i++) {
            int num = A[i];
            if (num == prev) return false;
            // 下降后再上升 --> false
            if (num > prev && !increase) return false;
            if (num < prev) increase = false;
            prev = num;
        }
        // 一直上升
        return !increase;
    }

    /**
     * 双指针
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * 时间击败 100%
     * */
    public boolean validMountainArray1(int[] A) {
        if (A == null || A.length <3) return false;

        int left = 0, right = A.length - 1;
        // 从左向右找波峰
        while (left < right && A[left + 1] > A[left]) left ++;
        // 避免了全上升，全下降的情况
        if (left == right || left == 0) return false;

        // 从右向左找波峰
        while (right > 0 && A[right - 1] > A[right]){
            right --;
            if (left > right) return false;
        }

        return left == right;
    }

    public static void main(String[] args) {
        _941_有效的山脉数组 cls = new _941_有效的山脉数组();
        int[] nums = {0,3,2,1};
        boolean res = cls.validMountainArray1(nums);
        System.out.println(res);
    }

}
