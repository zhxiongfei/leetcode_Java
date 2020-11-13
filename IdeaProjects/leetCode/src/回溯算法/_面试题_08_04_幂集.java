package 回溯算法;

import java.util.*;

/**
幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
        说明：解集不能包含重复的子集。
        示例:
        输入： nums = [1,2,3]
        输出：
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
        链接：https://leetcode-cn.com/problems/power-set-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _面试题_08_04_幂集 {

    /**
     * 回溯算法
     * */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[nums.length];
        dfs(res, path,0, used, nums);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int start, boolean[] used, int[] nums){
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.addLast(nums[i]);
            dfs(res, path,i + 1, used, nums);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 动态规划
     *
     * 输入 0 -->[[]]
     * 输入 1 -->[[],[1]]
     * 输入 2 -->[[],[1],[2],[1,2]]
     * 输入 3 -->[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 可以获得 递推公式： f(n) = f(n-1) + [i+[num] for i in f(n-1)]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        for (int num : nums) {
            for (List<Integer> list : res) {
                list.add(num);
                res.add(new ArrayList<>(list));
            }
        }
        return res;
    }

     public static void main(String[] args) {
        _面试题_08_04_幂集 cls = new _面试题_08_04_幂集();
        int[] nums = {1,2,3};
        cls.subsets(nums);
    }
}
