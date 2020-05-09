package 二叉搜索树;

import 堆._451_根据字符出现频率排序;

/*
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
说明： 要求算法时间复杂度为 O(h)，h 为树的高度。

示例:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
   / \
  4   6
 /     \
2       7

另一个正确答案是 [5,2,6,null,4,null,7]。

    5
   / \
  2   6
   \   \
    4   7

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _450_删除二叉搜索树中的节点 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val > key){
            // 在左子树
            root.left = deleteNode(root.left,key);
        }else if (root.val < key){
            // 在右子树
            root.right = deleteNode(root.right,key);
        }else {
            // 相等 -> 找到了
            if (root.left == null && root.right == null) {
                // 如果是叶子节点
                root  = null;
            } else if (root.right != null) {
                // 如果有右子节点
                // 找后继，用后继节点把 当前节点替换，然后再删除后继节点
                root.val = successor(root).val;
                root.right = deleteNode(root.right,root.val);
            } else {
                // 有左子节点
                // 找前驱，用前驱节点把 当前节点替换，然后再删除前驱节点
                root.val = predecessor(root).val;
                root.left = deleteNode(root.left,root.val);
            }
        }

        return root;
    }

    // 二叉搜索树的中序遍历
    // 中序遍历是递增排序的序列，中序遍历的遍历次序是 left -> Node -> right

    // 后继节点
    // 是右子树上最左边的节点
    // 是其中序遍历的后一个节点
    private TreeNode successor(TreeNode root){
        root = root.right;
        while (root.left != null) root = root.left;
        return root;
    }

    // 前驱节点
    // 是左子树上最右边的节点
    // 是其中序遍历的前一个节点
    private TreeNode predecessor(TreeNode root){
        root = root.left;
        while (root.right != null) root = root.right;
        return root;
    }

    public static void main(String[] args) {

        _450_删除二叉搜索树中的节点 test = new _450_删除二叉搜索树中的节点();

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(3);
        node.right = new TreeNode(6);
        node.left.left = new TreeNode(2);
        node.left.right = new TreeNode(4);
        node.right.right = new TreeNode(7);

        node = test.deleteNode(node,3);
        if (node != null){

        }
    }
}
