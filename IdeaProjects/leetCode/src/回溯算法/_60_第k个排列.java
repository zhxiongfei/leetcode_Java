package 回溯算法;

import java.util.*;

/**
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

        按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

        "123"
        "132"
        "213"
        "231"
        "312"
        "321"
        给定 n 和 k，返回第 k 个排列。

        说明：

        给定 n 的范围是 [1, 9]。
        给定 k 的范围是[1,  n!]。
        示例 1:

        输入: n = 3, k = 3
        输出: "213"
        示例 2:

        输入: n = 4, k = 9
        输出: "2314"

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/permutation-sequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _60_第k个排列 {

    /**
     * 暴力法
     * 暴力回溯， 全排列
     * 找到全排列中的第 k-1 个
     *
     * 思路没问题，但是时间复杂度太高，超时了.
     *
     * */

    /**
     * 优化一
     * 剪枝当 res 数组的个数 == k时。 递归结束
     * 优化完之后，通过了，但是击败 8%
     *
     * */
    public String getPermutation1(int n, int k) {
        List<String>res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n + 1];
        dfs1(res,sb,0, used,n,k);
        return res.get(k -1);
    }

    public void dfs1(List<String>res, StringBuilder sb,int depth, boolean[] used, int n, int k){

        /**优化一*/
        if (res.size() == k) return;
        if (depth == n){
            res.add(sb.toString());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i]) continue;

            used[i] = true;
            sb.append(i);

            dfs1(res, sb, depth + 1, used, n, k);

            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 优化二
     * 发现其实可以根据 k 来判断出 组合
     * 而不必须暴力回溯
     *
     * 如果 n 为 9， 那么 k 的范围就是 [1, 362880].
     * 如果是暴力回溯，需要 362880次递归循环，时间复杂度特别高
     *
     * 举个例子
     * 例如 n 为 4, k为8
     * n 为 4 的话，一共是有 24种组合
     * 1开头的6个， 2开头的6个，3开头的6个，4开头的6个
     * 我们可以根据 k 为 8 就可以判断出最终结果在以 2 开头的第二个
     *
     * 所以使用剪枝
     * 在dfs过程中, 如果全排列个数 < k 则说明不在此分支中，剪枝
     *
     * 参考 https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
     *
     * */
    public String getPermutation2(int n, int k) {
        boolean[] used = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        dfs2(sb, used,0,n,k);
        return sb.toString();
    }

    public void dfs2(StringBuilder sb,boolean[] used,int depth, int n, int k){
        if (depth == n){
            return;
        }
        int cnt = factorial(n - 1 - depth);
        for (int i = 1; i <= n; i++) {
            if (used[i]) continue;

            // 所求排列一定在叶子节点处得到，进入每一个分支，可以根据已经选定的数的个数，进而计算还未选择的数的个数
            // 然后计算阶乘，就知道这个分支的路径总数

            // 如果 k 大于 这个分支的路径总数，直接跳过这个分支，称为剪枝
            // 如果 k 小于等于 路径总数，则说明所求的全排列一定在这个分支的子路径中，递归求解
            if (cnt < k){
                k -= cnt;
                continue;
            }
            sb.append(i);
            used[i] = true;
            dfs2(sb, used, depth + 1, n, k);

            // 注意1，这里不可以回溯，算法设计是一下子来到叶子结点，没有回头的过程
            // 注意2，这个要家return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    public static int factorial(int number) {
        if (number <= 1) return 1;
        else return number * factorial(number - 1);
    }

    /**
     *
     * 方法三
     * 迭代代替递归
     *
     * */
    public String getPermutation3(int n, int k) {
        final int[] factorials = {1,1,2,6,24,120,720,5040,40320,362880};
        boolean[] used = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            // 计算第 n - 1个数字
            // 需要的路径总数
            int cnt = factorials[i];

            for (int j = 1; j <= n; j++) {
                if (used[j]) continue;

                if (k > cnt){
                    k -= cnt;
                    continue;
                }

                used[j] = true;
                sb.append(j);
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        _60_第k个排列 cls = new _60_第k个排列();
        String string = cls.getPermutation2(4,8);
        System.out.println(string);
    }

}
