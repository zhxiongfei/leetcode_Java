package 二叉树;

import java.util.Stack;

/**
给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。

         

        示例 ：

        输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]

        5
        / \
        3    6
        / \    \
        2   4    8
         /        / \
        1        7   9

        输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

        1
          \
           2
            \
             3
              \
               4
                \
                 5
                  \
                   6
                    \
                     7
                      \
                       8
                        \
        9
         

        提示：

        给定树中的结点数介于 1 和 100 之间。
        每个结点都有一个从 0 到 1000 范围内的唯一整数值。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/increasing-order-search-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _897_递增顺序查找树 {

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack();
        TreeNode newHead = new TreeNode(-1);
        TreeNode newNode = newHead;
        while (!stack.empty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            newNode.right = new TreeNode(root.val);
            newNode = newNode.right;
            root = root.right;
        }
        return newHead.right;
    }

    TreeNode newHead = new TreeNode(-1);
    TreeNode newNode = newHead;
    public TreeNode dfs(TreeNode root) {
        if (root == null) return null;

        dfs(root.left);

        newNode.right = new TreeNode(root.val);
        newNode = newNode.right;

        dfs(root.right);

        return newHead.right;
    }

    public static void main(String[] args) {
        // [5,3,6,2,4,null,8,1,null,null,null,7,9]

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(3);
        node.left.left = new TreeNode(2);
        node.left.right = new TreeNode(4);
        node.left.left.left = new TreeNode(1);

        node.right = new TreeNode(6);
        node.right.right = new TreeNode(8);
        node.right.right.left = new TreeNode(7);
        node.right.right.right = new TreeNode(9);

        _897_递增顺序查找树 cls = new _897_递增顺序查找树();
        cls.increasingBST(node);
    }
}
