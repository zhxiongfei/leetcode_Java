package 数组;

/*
对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。

        给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。

         

        示例 1：

        输入：A = [1,2,0,0], K = 34
        输出：[1,2,3,4]
        解释：1200 + 34 = 1234
        示例 2：

        输入：A = [2,7,4], K = 181
        输出：[4,5,5]
        解释：274 + 181 = 455
        示例 3：

        输入：A = [2,1,5], K = 806
        输出：[1,0,2,1]
        解释：215 + 806 = 1021
        示例 4：

        输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
        输出：[1,0,0,0,0,0,0,0,0,0,0]
        解释：9999999999 + 1 = 10000000000
         

        提示：

        1 <= A.length <= 10000
        0 <= A[i] <= 9
        0 <= K <= 10000
        如果 A.length > 1，那么 A[0] != 0

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

public class _989_数组形式的整数加法 {

    /**
     *
     * 思路
     * 将 K 转化为 int[] 数组 B
     *
     * 保证 传入参数 A的长度 >= B的长度
     * 所以当 A.length < B.length时, 递归调用,并把 A，B交换
     *
     * 考虑到 K 为溢出的情况，需要把 K 转化为 int[] 数组
     * 两个数组再相加
     *
     * 依次相加 两个数组的每一位
     *
     * */
    public static List<Integer> addToArrayForm(int[] A, int[] B) {

        // 两个数组相加，最后结果放入 list 中
        // 其实有很多大数相加问题。 两个链表相加。 两个字符串相加。此题目为两个数组相加
        List<Integer> list = new ArrayList<>();
        int carry = 0, i = A.length - 1, j = B.length - 1;
        while (carry > 0 || i >= 0){
            int v1 = i < 0 ? 0 :  A[i --];
            int v2 = j < 0 ? 0 : B[j --];

            int sum = v1 + v2 + carry;
            carry = sum >= 10 ? 1 : 0;
            sum %= 10;

            list.add(0, sum);
        }
        return list;
    }

    public static List<Integer> addToArrayForm(int[] A, int K) {

        // 求出 K 转换为数组后的 位数
        int len = 0;
        while(K >= Math.pow(10, len)) {
            len++;
        }
        // 初始化 B 数组
        int[] B = new int[len];
        int idx = len - 1;
        while (K > 0){
            int tmp = K % 10;
            B[idx --] = tmp;
            K /= 10;
        }

        // 保证 A的长度 >= B的长度
        if (A.length < B.length){
            return addToArrayForm(B, A);
        }
        return addToArrayForm(A, B);
    }

    /**
     *
     * 不考虑 K 溢出的情况
     * 我们可以对以上的想法做一个小变化，让它实现起来更容易 —— 我们将整个加数加入数组表示的数的最低位
     *
     * */
    public List<Integer> addToArrayForm1(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        for(int i = A.length-1 ; i >= 0 || K > 0 ; i--){
            if(i >= 0)
                K += A[i];
            res.add(0, K % 10);
            K = K/10;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {1,2,0,0};
        addToArrayForm(nums, 34);
    }

}
