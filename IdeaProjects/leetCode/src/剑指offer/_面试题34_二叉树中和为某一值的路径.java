package 剑指offer;

/*
输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

         

        示例:
        给定如下二叉树，以及目标和 sum = 22，

             5
            / \
           4   8
          /   / \
        11   13  4
        /  \    / \
        7   2  5   1
        返回:

        [
        [5,4,11,2],
        [5,8,4,5]
        ]
         

        提示：

        节点总数 <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _面试题34_二叉树中和为某一值的路径 {

    // 结果数组
    LinkedList<List<Integer>> res = new LinkedList<>();

    // 路径
    LinkedList<Integer> path = new LinkedList<>();

    public void dfs(TreeNode node, int sum){
        if (node == null) return;

        path.add(node.val);
        sum -= node.val;

        if (sum == 0 && node.left == null && node.right == null)
            res.add(new LinkedList<>(path));

        dfs(node.left, sum);
        dfs(node.right, sum);

        // 回溯
        path.removeLast();
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        dfs(root, sum);
        return res;
    }


}

