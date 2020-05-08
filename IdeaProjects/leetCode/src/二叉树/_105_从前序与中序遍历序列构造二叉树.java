package 二叉树;

/*
根据一棵树的前序遍历与中序遍历构造二叉树。

        注意:
        你可以假设树中没有重复的元素。

        例如，给出

        前序遍历 preorder = [3,9,20,15,7]
        中序遍历 inorder = [9,3,15,20,7]
        返回如下的二叉树：

        3
        / \
        9  20
        /  \
        15   7

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

public class _105_从前序与中序遍历序列构造二叉树 {

    HashMap<Integer,Integer> map = new HashMap<>(); // 用于存放中序遍历的 元素 -> 下标对应的字典
    int rootIdx = 0;                                // 存放前序遍历的数组中，root的下标
    int[] preorder = null;                          // 全局化 前序遍历数组

    private TreeNode buildTreeHelper(int startIdx, int endIdx){
        if (startIdx > endIdx) return null;

        TreeNode root = new TreeNode(preorder[rootIdx]);
        int idx = map.get(preorder[rootIdx]);

        rootIdx ++;

        // 左子树
        root.left = buildTreeHelper(startIdx,idx - 1);

        // 右子树
        root.right = buildTreeHelper(idx + 1, endIdx);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (inorder.length == 0 || preorder.length == 0 || inorder.length != preorder.length) return null;
        if (inorder.length == 1) return new TreeNode(inorder[0]);

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        this.preorder = preorder;

        return buildTreeHelper(0,preorder.length - 1);
    }

    public static void main(String[] args) {
        _105_从前序与中序遍历序列构造二叉树 cls = new _105_从前序与中序遍历序列构造二叉树();

        int[] inorder = {9,3,15,20,7};
        int[] preorder = {3,9,20,15,7};

        cls.buildTree(preorder,inorder);
    }
}
