package 二叉树;

import 链表.ListNode;

import java.util.HashMap;

/*
根据一棵树的中序遍历与后序遍历构造二叉树。

        注意:
        你可以假设树中没有重复的元素。

        例如，给出

        中序遍历 inorder = [9,3,15,20,7]
        后序遍历 postorder = [9,15,7,20,3]
        返回如下的二叉树：

        3
        / \
        9  20
        /  \
        15   7

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _106_从中序与后序遍历序列构造二叉树 {

    // map中存放中序遍历的节点和索引值
    HashMap<Integer,Integer> map = new HashMap<>();
    int[] postorder = null;
    int rootIdx = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if (inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;
        if (inorder.length == 1) return new TreeNode(inorder[0]);

        this.postorder = postorder;
        this.rootIdx = postorder.length - 1;
        // 将中序遍历的节点和索引值存入数组
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }

        return buildTreeHelper(0,inorder.length - 1);
    }


    private TreeNode buildTreeHelper(int is, int ie){

        // 左边索引 > 右边索引 -> 没有元素
        if (is > ie) return null;

        // 后序遍历最后一位是根节点，或者根节点的索引
        int idx = map.get(postorder[rootIdx]);

        // 根节点
        TreeNode node = new TreeNode(postorder[rootIdx]);

        rootIdx --;
        // 右子树 ：  【idx + 1, inorder.length - 1】
        node.right = buildTreeHelper(idx + 1, ie);
        // 左子树 ：  【0 ， idx-1】
        node.left = buildTreeHelper(is,idx - 1);

        return node;
    }

    public static void main(String[] args) {

        _106_从中序与后序遍历序列构造二叉树 cls = new _106_从中序与后序遍历序列构造二叉树();

        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        TreeNode node = cls.buildTree(inorder,postorder);
        if (node == null){

        }
    }
}
