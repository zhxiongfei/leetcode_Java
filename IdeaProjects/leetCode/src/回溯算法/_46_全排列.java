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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class _46_全排列 {

    /**
     *
     * 回溯算法
     *
     *
     * */
    List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        Stack<Integer> stack = new Stack<>();
        backtrack(nums, stack);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, Stack<Integer> stack) {
        // 触发结束条件
        if (stack.size() == nums.length) {
            res.add(new LinkedList(stack));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (stack.contains(nums[i]))
                continue;
            // 做选择
            stack.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, stack);
            // 取消选择
            stack.pop();
        }
    }
}
