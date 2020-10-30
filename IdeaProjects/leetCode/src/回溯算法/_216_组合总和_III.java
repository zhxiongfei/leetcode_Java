package 回溯算法;

import java.util.*;

/**
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

        说明：

        所有数字都是正整数。
        解集不能包含重复的组合。 
        示例 1:

        输入: k = 3, n = 7
        输出: [[1,2,4]]
        示例 2:

        输入: k = 3, n = 9
        输出: [[1,2,6], [1,3,5], [2,3,4]]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/combination-sum-iii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _216_组合总和_III {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[10];

        dfs(res,path,used,0, 0, 1,k,n);

        return res;
    }

    public void dfs(List<List<Integer>> res,Deque<Integer> path,boolean[] used, int depth, int sum, int begin, int k, int n){
        if (depth == k && sum == n){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i <= 9; i++) {
            if (used[i]) continue;

            /**
             * 剪枝优化
             * 思路 当 sum + i 大于 需要求的总和时 --> 剪枝
             * 优化前 击败 60%
             * 优化后 击败 100%
             * */
            if (sum + i > n) continue;

            used[i] = true;
            path.addLast(i);

            dfs(res, path, used, depth + 1, sum + i, i + 1, k, n);

            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _216_组合总和_III cls = new _216_组合总和_III();
        cls.combinationSum3(3, 9);
    }
}
