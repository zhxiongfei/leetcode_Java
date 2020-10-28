package 回溯算法;

import java.util.*;

/**
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

        candidates 中的每个数字在每个组合中只能使用一次。

        说明：

        所有数字（包括目标数）都是正整数。
        解集不能包含重复的组合。 
        示例 1:

        输入: candidates = [10,1,2,7,6,1,5], target = 8,
        所求解集为:
        [
        [1, 7],
        [1, 2, 5],
        [2, 6],
        [1, 1, 6]
        ]
        示例 2:

        输入: candidates = [2,5,2,1,2], target = 5,
        所求解集为:
        [
          [1,2,2],
          [5]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/combination-sum-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _40_组合总和II {

    /**
     * 回溯算法
     * 本题的难点，在于如何去除重复解
     *
     * https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
     * */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;

        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs(candidates, res, path, used, 0, 0, target);

        return res;
    }

    public void dfs(int[] candidates,List<List<Integer>> res,Deque<Integer> path,boolean[] used, int begin,int sum, int target){
        if (sum == target){
            res.add(new ArrayList<>(path));
            return;
        }
        if (sum > target){
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (sum + candidates[i] > target || used[i]) continue;

            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            used[i] = true;

            dfs(candidates, res, path, used, i + 1,sum + candidates[i], target);

            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _40_组合总和II cls = new _40_组合总和II();

        int[] nums = {10,1,2,7,1,6,5};
        cls.combinationSum2(nums, 8);
    }
}
