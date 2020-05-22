package 二叉树;

import java.util.HashMap;

public class 从前序与中序遍历序列构造二叉树 {

    /*
    *
    * 思路：
    * 二叉树前序遍历的第一个元素就是 根节点元素
    * 找到根节点在 中序遍历结果中的index.
    * [0, index - 1] 为根节点的左子树
    * [index + 1, len - 1] 为根节点的右子树
    *
    * 。。。
    *
    * 递归分别找根节点的左右子节点
    *
    * 直至末尾
    *
    * 1， 用 map 存储 中序遍历 <value : index>
    * 2,  初始值 rootIdx = 0 (根节点)
    * 3,  取出 map中前序数组首元素的下标
    * 4， [0, idx - 1]为左子树
    * 5,  [idx + 1, len -1]为右子树
    *
    * */
    HashMap<Integer,Integer> map = new HashMap<>();
    int rootIdx = 0;
    int[] preorder = null;

    private TreeNode buildTreeHelper(int startIdx, int endIdx){
        if (startIdx > endIdx) return null;

        TreeNode root = new TreeNode(preorder[rootIdx]);
        int idx = map.get(root.val);

        rootIdx ++;

        // 左子树
        root.left = buildTreeHelper(startIdx, idx - 1);
        root.right = buildTreeHelper(idx + 1, endIdx);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        this.preorder = preorder;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeHelper(0, preorder.length - 1);
    }

}
