package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
给定一个二叉树，在树的最后一行找到最左边的值。

        示例 1:

        输入:

        2
        / \
        1   3

        输出:
        1
         

        示例 2:

        输入:

        1
        / \
        2   3
        /   / \
        4   5   6
        /
        7

        输出:
        7
         

        注意: 您可以假设树（即给定的根节点）不为 NULL。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-bottom-left-tree-value
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _513_找树左下角的值 {

    /**
     *
     * bfs
     * 二叉树层序遍历
     * 每遍历一层时，将每一层第一个值赋值给 targetNode
     *
     * */
    public void bfs(TreeNode root, TreeNode targetNode){
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){

            int size = queue.size();
            targetNode.val = queue.peek().val;
            while (size-- > 0){
                TreeNode node = queue.poll();

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        TreeNode targetNode = new TreeNode(root.val);
        bfs(root, targetNode);
        return targetNode.val;
    }

    /**
     *
     * dfs
     * 深度优先搜索
     *
     * 用 max 记录遍历的层级, 初始默认为 -1,代表还没有遍历
     * 用 res 记录最终结果.
     *
     * dfs过程中, 如果是叶子节点，并且 目前层级 max < depth.
     *    此时找到了，下一个较大结果, max = depth; res = root.val
     *
     * 再分别遍历 root 的左右子节点
     *
     * */
    int max = -1;
    int res;
    public void dfs(TreeNode root, int depth){
        if (root == null) return;

        if (root.left == null && root.right == null && max < depth){
            max = depth;
            res = root.val;
        }

        dfs(root, depth + 1);
        dfs(root, depth + 1);
    }

    public int findBottomLeftValue1(TreeNode root) {

        dfs(root, 0);
        return res;
    }

}
