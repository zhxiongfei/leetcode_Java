package 回溯算法;

import java.util.*;

/**
给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

        说明：解集不能包含重复的子集。

        示例:

        输入: [1,2,2]
        输出:
        [
        [2],
        [1],
        [1,2,2],
        [2,2],
        [1,2],
        []
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/subsets-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _90_子集II {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Deque<Integer> path = new ArrayDeque<Integer>();

        dfs(res,nums, path, 0);

        return res;
    }

    public void dfs(List<List<Integer>> res, int[] nums, Deque<Integer> path, int begin){
        res.add(new ArrayList<>(path));

        for (int i = begin; i < nums.length; i++) {
            if (i > begin && nums[i-1] == nums[i]) continue;

            path.addLast(nums[i]);
            dfs(res,nums, path, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _90_子集II cls = new _90_子集II();
        int[] nums = {1,2,2};
        cls.subsetsWithDup(nums);
    }
}
