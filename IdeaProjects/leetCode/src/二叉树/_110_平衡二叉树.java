package 二叉树;

/*
给定一个二叉树，判断它是否是高度平衡的二叉树。

        本题中，一棵高度平衡二叉树定义为：

        一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

        示例 1:

        给定二叉树 [3,9,20,null,null,15,7]

        3
        / \
        9  20
        /  \
        15   7
        返回 true 。

        示例 2:

        给定二叉树 [1,2,2,3,3,null,null,4,4]

        1
        / \
        2   2
        / \
        3   3
        / \
        4   4
        返回 false 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/balanced-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 时间复杂度 n^2 有待实现时间复杂度更低的。
public class _110_平衡二叉树 {

    public int height(TreeNode node) {

        if (node == null) return 0;
        int height = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            int count = q.size();
            while (count > 0) {
                TreeNode n = q.remove();

                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
                count--;
            }
            height++;
        }

        return height;
    }

    public boolean isBalanced(TreeNode node) {
        if (node == null) return true;

        return Math.abs(height(node.left) - height(node.right)) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }
}
