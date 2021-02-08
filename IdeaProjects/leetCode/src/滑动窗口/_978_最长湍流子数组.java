package 滑动窗口;

/**
当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
        若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
        或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
        也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
        返回 A 的最大湍流子数组的长度。

        示例 1：
        输入：[9,4,2,10,7,8,8,1,9]
        输出：5
        解释：(A[1] > A[2] < A[3] > A[4] < A[5])

        示例 2：
        输入：[4,8,12,16]
        输出：2

        示例 3：
        输入：[100]
        输出：1
         
        提示：
        1 <= A.length <= 40000
        0 <= A[i] <= 10^9

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _978_最长湍流子数组 {

    // [9,4,2,10,7,8,8,1,9]
    /**
     * 滑动窗口
     * 从左到右开始滑动窗口
     * left : 窗口左边界
     * right: 窗口右边界
     * res  : 存放最终结果
     * greater : 上一次摆动是否是上升
     *
     * 当窗口满足摆动条件{
     *     1, 上次上升， 这次下降
     *     2, 上次下降， 这次下降
     * }时 -> 窗口扩大,更新结果
     * 否则 -> 窗口缩小
     *
     * */
    public int maxTurbulenceSize1(int[] arr) {
        if (arr.length == 1) return 1;

        int res,left = 0, right = 1, length = arr.length;
        res = arr[right] != arr[left] ? 2 : 1;
        boolean greater = arr[right] > arr[left];

        while (right < length - 1){
            int num = arr[++right];
            if (num >= arr[right - 1] && greater ||
                    num <= arr[right - 1] && !greater){
                left = right - 1;
            }else {
                res = Math.max(res, right - left + 1);
            }
            greater = arr[right] > arr[right - 1];
        }
        return res;
    }

    /**
     * 动态规划
     * */
    public int maxTurbulenceSize(int[] arr){
        int len = arr.length;
        if (len < 2) {
            return len;
        }

        // 以 arr[i] 结尾，并且 arr[i - 1] < arr[i] 的湍流子数组的长度
        int[] increased = new int[len];
        // 以 arr[i] 结尾，并且 arr[i - 1] > arr[i] 的湍流子数组的长度
        int[] decreased = new int[len];

        increased[0] = 1;
        decreased[0] = 1;
        int res = 1;
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] < arr[i]) {
                increased[i] = decreased[i - 1] + 1;
                decreased[i] = 1;
            } else if (arr[i - 1] > arr[i]) {
                decreased[i] = increased[i - 1] + 1;
                increased[i] = 1;
            } else {
                increased[i] = 1;
                decreased[i] = 1;
            }

            res = Math.max(res, Math.max(increased[i], decreased[i]));
        }
        return res;
    }

    public static void main(String[] args) {
        _978_最长湍流子数组 cls = new _978_最长湍流子数组();
        int[] arr = {9,4,2,10,7,8,8,1,9};
        int res = cls.maxTurbulenceSize1(arr);
        System.out.println(res);
    }
}
