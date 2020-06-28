package 剑指offer;

/*
给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。

         

        示例:

        输入: [1,2,3,4,5]
        输出: [120,60,40,30,24]
         

        提示：

        所有元素乘积之和不会溢出 32 位整数
        a.length <= 100000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _剑指_Offer_66_构建乘积数组 {

    /**
     * * 这道题看起来非常容易，直接把数组中所有元素的乘积算出来
     * 计算某个位置的值时，直接用总的乘积 / 当前元素的值即可得到最终结果
     * 但是题目中有明确要求，不得使用除法
     * 这就有一点困难了
     */

    /**
     *
     * 暴力法
     * 暴力法很容易想到
     * 当计算 i 位置的值时，取出 除i位置以外的元素，计算乘积
     *
     * 时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(1)
     *
     * */
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) return a;

        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int cur = 1;
            for (int j = 0; j < a.length; j++) {
                if (i == j) continue;

                cur += a[j];
            }
            res[i] = cur;
        }
        return res;
    }

    /**
     *
     * 分别计算出某元素 前边所有元素的积 和 后边所有元素的积
     * 两者的乘积，就是要求的除自身以外的子数组的乘积
     *
     * 我们使用 prev数组 存储 i 元素 前边所有元素的乘积
     * next数组 存储 i 元素 后边所有元素的乘积
     *
     * 最终的结果数组 res[i] = prev[i] * next[i]
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     * */
    public int[] constructArr1(int[] a) {

        if (a == null || a.length == 0) return a;

        int[] prev = new int[a.length];
        prev[0] = 1;
        for (int i = 1; i < a.length; i++) {
            prev[i] = a[i - 1] * prev[i - 1];
        }

        int[] next = new int[a.length + 1];
        next[a.length] = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            next[i] = a[i + 1] * next[i + 1];
        }

        int[] res = new int[a.length];
        for (int i = 0; i < prev.length; i++) {
            res[i] = prev[i] * next[i];
        }
        return res;
    }

    /**
     *
     * 优化
     * 1，空间复杂度
     * 题目中进阶有要求
     * 是否可以 O(1) 空间复杂度解决问题？
     *
     * 2, 循环次数
     * 上述解法，我们遍历了3遍数组
     * 是否可以少遍历一遍数组？
     *
     * 下边这种解法，我们尝试进行上述两点的优化
     *
     * [1,2,3,4,5]
     * [120,60,40,30,24]
     *
     * [1,1,2,6,24]
     * [,30,24]
     * */
    public int[] constructArr12int2(int[] a) {

        if (a == null || a.length == 0) return a;

        int[] res = new int[a.length];
        res[0] = 1;
        for (int i = 1; i < a.length; i++) {
            res[i] = a[i - 1] * res[i - 1];
        }

        int R = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            res[i] = res[i - 1] * R;
            R *= res[i];
        }
        return res;
    }
}
