package 回溯算法;

/*
给定一个 没有重复 数字的序列，返回其所有可能的全排列。

        示例:

        输入: [1,2,3]
        输出:
        [
        [1,2,3],
        [1,3,2],
        [2,1,3],
        [2,3,1],
        [3,1,2],
        [3,2,1]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/permutations
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class _46_全排列 {

    // 主函数，输入一组不重复的数字，返回它们的全排列
    List<List<Integer>> permute(int[] nums){
        List<List<Integer>>res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return res;

        // 路径栈
        Deque<Integer> path = new ArrayDeque<Integer>();

        // 是否使用过的数组
        boolean[] used = new boolean[nums.length];

        // 回溯
        dfs(nums, n, 0, path, used, res);

        return res;
    }

    /**
     * 回溯
     * @param nums    输入全排列的数组
     * @param length  需要全排列的数组的长度
     * @param depth   遍历的深度
     * @param path    记录遍历的路径
     * @param used    是否访问过
     * @param res     存储结果的数组
     */
    public void dfs(int[] nums, int length, int depth, Deque<Integer>path, boolean[] used, List<List<Integer>>res){
        if (depth == length){
            // 如果 depth == length 说明已经遍历到最后一层。 将path添加到结果中
            res.add(new ArrayList<>(path));
            return;
        }

        // 回溯选择
        for (int i = 0; i < nums.length; i++) {
            // 如果选择过，则进度下层循环
            if (used[i] == true) continue;

            // 访问路径添加当前数组
            path.addLast(nums[i]);
            // 设置为访问过
            used[i] = true;
            // 进入下层选择

            System.out.println("递归之前" + path);

            dfs(nums, length, depth + 1, path, used, res);

            System.out.println("递归之后" + path);

            // 状态充值
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _46_全排列 cls = new _46_全排列();
        int[] nums = {1,2,3};
        cls.permute(nums);
    }
}
