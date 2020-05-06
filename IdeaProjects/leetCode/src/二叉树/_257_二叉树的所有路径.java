package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回所有从根节点到叶子节点的路径。

        说明: 叶子节点是指没有子节点的节点。

        示例:

        输入:

        1
        /   \
        2     3
        \
        5

        输出: ["1->2->5", "1->3"]

        解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-tree-paths
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class _257_二叉树的所有路径 {

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return null;
        ArrayList <String>list = new ArrayList<>();

        Stack<TreeNode> node_stack = new Stack<TreeNode>();
        Stack<String> path_stack = new Stack<>();

        node_stack.push(root);
        path_stack.add(Integer.toString(root.val));

        TreeNode node;
        String path;
        while (!node_stack.isEmpty()){

            node = node_stack.pop();
            path = path_stack.pop();

            if (node.left == null && node.right == null){
                list.add(path);
            }

            if (node.left != null){
                node_stack.push(node.left);
                path_stack.add(path+"->"+Integer.toString(node.left.val));
            }

            if (node.right != null){
                node_stack.push(node.right);
                path_stack.add(path+"->"+Integer.toString(node.right.val));
            }
        }
        return list;
    }
}
