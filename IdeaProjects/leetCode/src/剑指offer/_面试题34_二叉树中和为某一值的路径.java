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

import java.util.*;

public class _面试题34_二叉树中和为某一值的路径 {

    /**
     * 回溯
     * */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs(res,root,path,sum);
        return res;
    }

    public void dfs(List<List<Integer>> res,TreeNode node,Deque<Integer> path,int sum){
        if (node == null) return;
        if (sum == node.val && node.left == null && node.right == null){
            List<Integer> tmp = new ArrayList<>(path);
            tmp.add(node.val);
            res.add(tmp);
            return;
        }

        path.addLast(node.val);
        dfs(res,node.left,path, sum - node.val);
        dfs(res,node.right,path, sum - node.val);
        path.removeLast();
    }


}

