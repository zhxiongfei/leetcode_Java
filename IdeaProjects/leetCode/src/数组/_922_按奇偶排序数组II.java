package 数组;

/**
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
        对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

        你可以返回任何满足上述条件的数组作为答案。
        示例：

        输入：[4,2,5,7]
        输出：[4,5,2,7]
        解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
         
        提示：

        2 <= A.length <= 20000
        A.length % 2 == 0
        0 <= A[i] <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _922_按奇偶排序数组II {

    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    public int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        // i指向偶数的指针
        // j指向奇数的指针
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            // i += 2 保证了 i 位置定是偶数
            if (A[i] % 2 == 1) {
                // 当 i 位置为奇数时
                while (A[j] % 2 == 1) {
                    // j += 2 保证了 j 位置定是奇数
                    // 此步骤是找到 奇数位置不对的数字
                    j += 2;
                }
                // i 不为偶数，j 不为奇数 --> 交换
                swap(A, i, j);
            }
        }
        return A;
    }

    public static void main(String[] args) {
        _922_按奇偶排序数组II cls = new _922_按奇偶排序数组II();
        int[] nums = {1,2,3,4};
        cls.sortArrayByParityII(nums);
    }
}
