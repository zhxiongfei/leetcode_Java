package 回溯算法;

import java.util.*;

/**
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

        candidates 中的数字可以无限制重复被选取。

        说明：

        所有数字（包括 target）都是正整数。
        解集不能包含重复的组合。 
        示例 1：

        输入：candidates = [2,3,6,7], target = 7,
        所求解集为：
        [
        [7],
        [2,2,3]
        ]
        示例 2：

        输入：candidates = [2,3,5], target = 8,
        所求解集为：
        [
          [2,2,2,2],
          [2,3,3],
          [3,5]
        ]
         

        提示：

        1 <= candidates.length <= 30
        1 <= candidates[i] <= 200
        candidate 中的每个元素都是独一无二的。
        1 <= target <= 500

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/combination-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _39_组合总和 {

    /**
     * 回溯算法
     * 此题目比一般回溯算法较难的地方在于 --> 不记顺序
     * 单纯回溯记录顺序的话，会造成答案的重复
     *
     * 遇到这一类相同元素不计算顺序的问题，
     * 我们在搜索的时候就需要 按某种顺序搜索。
     * 具体的做法是：每一次搜索的时候设置 下一轮搜索的起点 begin
     *
     * 参考 : https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs(candidates, path, target, 0, 0, res);

        return res;
    }

    public void dfs(int[] candidates, Deque<Integer>path, int target, int sum, int begin, List<List<Integer>> res){
        if (target == sum){
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < sum){
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (sum + candidates[i] > target) continue;

            path.addLast(candidates[i]);
            dfs(candidates, path, target, sum + candidates[i], i, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _39_组合总和 cls = new _39_组合总和();
        int[] candidates = {2,3,5};
        cls.combinationSum(candidates, 8);
    }
}
