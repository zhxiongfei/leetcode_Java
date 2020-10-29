package 回溯算法;

import java.util.*;

/**
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

        示例:

        输入: n = 4, k = 2
        输出:
        [
        [2,4],
        [3,4],
        [2,3],
        [1,2],
        [1,3],
        [1,4],
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/combinations
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _77_组合 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[n + 1];

        dfs(n, k, 0, 1, used, path, res);

        return res;
    }

    public void dfs(int n, int k, int count, int begin, boolean[] used, Deque<Integer>path, List<List<Integer>>res){
        if (count == k){
            res.add(new ArrayList<>(path));
            return;
        }

        /**
         * 一开始设置i的上限是 <= n
         * i <= n
         * 时间击败 50%的人
         *
         * 优化 :
         * 事实上，如果 n = 7, k = 4
         * 从 5 开始搜索就没有意义了。
         * 因为即使把5算上，也只有 5 6 7三个数字了
         * 凑不出 4 个数字
         *
         * */
        for (int i = begin; i <= (n - (k - path.size()) + 1); i++) {
            if (used[i]) continue;
            path.addLast(i);
            used[i] = true;

            dfs(n, k, count+1, i + 1, used, path, res);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        _77_组合 cls = new _77_组合();
        cls.combine(4,2);
    }

}
