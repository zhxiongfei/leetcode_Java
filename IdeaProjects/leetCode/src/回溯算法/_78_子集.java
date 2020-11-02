package 回溯算法;

import java.util.*;

/**
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

        说明：解集不能包含重复的子集。

        示例:

        输入: nums = [1,2,3]
        输出:
        [
        [3],
          [1],
          [2],
          [1,2,3],
          [1,3],
          [2,3],
          [1,2],
          []
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/subsets
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _78_子集 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[nums.length + 1];

        dfs(res, path, used, nums, 0, 0);

        return res;
    }

    public void dfs(List<List<Integer>> res, Deque<Integer> path, boolean[] used, int[] nums, int depth, int begin){
        res.add(new ArrayList<>(path));
        if (depth == nums.length){
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            int num = nums[i];
            if (used[num]) continue;

            used[num] = true;
            path.addLast(num);

            dfs(res, path, used, nums, depth + 1, i + 1);

            used[num] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _78_子集 cls = new _78_子集();
        int[] nums = {1,2,3};

        cls.subsets(nums);
    }
}
