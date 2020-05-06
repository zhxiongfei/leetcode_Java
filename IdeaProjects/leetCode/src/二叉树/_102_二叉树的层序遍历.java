package 二叉树;

/*
给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

         

        示例：
        二叉树：[3,9,20,null,null,15,7],

        3
        / \
        9  20
        /  \
        15   7
        返回其层次遍历结果：

        [
        [3],
        [9,20],
        [15,7]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102_二叉树的层序遍历 {

    public List<List<Integer>> levelOrder(TreeNode root){
        ArrayList list = new ArrayList();

        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode node = root;
        while (!queue.isEmpty()){
            ArrayList a = new ArrayList();
            int count = queue.size();
            while (count > 0){
                node = queue.remove();

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                a.add(node.val);
                count --;
            }

            list.add(a);
        }

        return list;
    }
}
