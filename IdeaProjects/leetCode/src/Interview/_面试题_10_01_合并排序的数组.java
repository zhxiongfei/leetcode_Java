package Interview;

/*
给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。

        初始化 A 和 B 的元素数量分别为 m 和 n。

        示例:

        输入:
        A = [1,2,3,0,0,0], m = 3
        B = [2,5,6],       n = 3

        输出: [1,2,2,3,5,6]
        说明:

        A.length == n + m

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sorted-merge-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_10_01_合并排序的数组 {

    // 思路：
    // 双指针，m 和 n 分别指向 数组A 和 B的末尾
    // 从后往前开始比较，较大者放入 m + n - 1位置
    // 较大数组的指针向后移动一位
    // 直到m 和 n两个指针都没有指向元素时停止
    // 当m为0时，说明B的元素已全部合并到A中，直接break
    // 当n为0时，说明A中元素已完全合并，依次把B中的元素合并到A中
    // 排序后的数组放在A中，因A的存储空间是足够的
    // 时间复杂度 : O(m + n)
    // 空间复杂度 : O(1)
    public void merge(int[] A, int m, int[] B, int n) {
        while (m > 0 || n > 0){
            if (m == 0){
                A[n - 1] = B[n - 1];
                n --;
                continue;
            }

            if (n == 0){
                break;
            }

            int a = A[m - 1];
            int b = B[n - 1];

            if (a > b){
                A[m + n - 1] = a;
                m --;
            }else {
                A[m + n - 1] = b;
                n --;
            }
        }
    }

}
