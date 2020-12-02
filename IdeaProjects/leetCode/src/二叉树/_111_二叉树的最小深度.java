package 二叉树;

/**
给定一个二叉树，找出其最小深度。

        最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

        说明: 叶子节点是指没有子节点的节点。

        示例:

        给定二叉树 [3,9,20,null,null,15,7],

         3
        / \
       9  20
          /  \
         15   7
        返回它的最小深度  2.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.LinkedList;
import java.util.Queue;

public class _111_二叉树的最小深度 {

    // 递归
    /**
     * DFS
     * */
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;

        return Math.min(minDepth(root.left),minDepth(root.right)) + 1;
    }

    // 非递归
    // 跟求最大深底不同的是， 当队列中取出的左右节点都为空时，直接return height+1
    /**
     * BFS
     * */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int height = 0;
        while (!queue.isEmpty()){

            int size = queue.size();
            while (size > 0){
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) return height+1;

                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
                size --;
            }

            height ++;
        }

        return height;
    }
}
