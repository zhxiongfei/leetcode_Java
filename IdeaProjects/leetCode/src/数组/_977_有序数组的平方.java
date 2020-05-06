package 数组;

/*
给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

         

        示例 1：

        输入：[-4,-1,0,3,10]
        输出：[0,1,9,16,100]
        示例 2：

        输入：[-7,-3,2,3,11]
        输出：[4,9,9,49,121]
         

        提示：

        1 <= A.length <= 10000
        -10000 <= A[i] <= 10000
        A 已按非递减顺序排序。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Arrays;

public class _977_有序数组的平方 {

    // 先 平方， 再排序。 时间复杂度 O(N * log(N))
    public int[] sortedSquares1(int[] A) {

        int[] resArr = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            resArr[i] = A[i] * A[i];
        }

        Arrays.sort(resArr);
        return resArr;
    }

    // 找到 非正数末尾位置，和正数开始位置，依次比较
    public static int[] sortedSquares(int[] A){

        int left = 0;       // 最大负数
        int right = 0;      // 最小非负数

        while (right < A.length && A[right] < 0){
            right ++;
        }
        left = right - 1;

        int[] res = new int[A.length];
        int index = 0;
        while (left >= 0 && right < A.length){

            int leftV = A[left] * A[left];
            int rightV = A[right] * A[right];

            if (leftV > rightV){
                // 左边大
                res[index] = rightV;
                right ++;
            }else {
                // 右边大
                res[index] = leftV;
                left --;
            }
            index ++;
        }

        // 左边已到头
        while(right < A.length){
            res[index] = A[right] * A[right];
            index ++;
            right ++;
        }

        // 右边到头
        while(left >= 0){
            res[index] = A[left] * A[left];
            left --;
            index ++;
        }

        return res;
    }

    // 从后往前
    public static int[] sortedSquares3(int[] A) {
        int left = 0, right = A.length - 1, index = A.length - 1;

        int[] res = new int[A.length];
        while (left <= right){

            int leftVal = A[left] * A[left];
            int rightVal = A[right] * A[right];

            if (leftVal > rightVal){
                res[index --] = leftVal;
                left ++;
            }else {
                res[index --] = rightVal;
                right ++;
            }
        }

        return res;
    }

    public static void main(String[] args){
        int[] array = {0,2};
        sortedSquares3(array);
    }
}
