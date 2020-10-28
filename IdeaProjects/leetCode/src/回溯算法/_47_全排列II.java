package 回溯算法;

import java.util.*;

/**
给定一个可包含重复数字的序列，返回所有不重复的全排列。

        示例:

        输入: [1,1,2]
        输出:
        [
        [1,1,2],
        [1,2,1],
        [2,1,1]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/permutations-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _47_全排列II {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return res;

        Arrays.sort(nums);

        Deque<Integer> path = new ArrayDeque<Integer>();
        int depth = 0;
        boolean[] used = new boolean[nums.length];
        dfs(nums,n,depth,path,used,res);

        return res;
    }

    public void dfs(int[] nums, int length,int depth, Deque<Integer>path, boolean[]used,List<List<Integer>> res){
        if (depth == length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            if (i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, length, depth + 1, path, used, res);

            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        _47_全排列II cls = new _47_全排列II();
        int[] nums = {1,1,2};
        cls.permuteUnique(nums);
    }
}
