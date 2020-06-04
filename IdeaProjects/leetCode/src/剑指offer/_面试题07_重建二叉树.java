package 剑指offer;

/*
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

         

        例如，给出

        前序遍历 preorder = [3,9,20,15,7]
        中序遍历 inorder = [9,3,15,20,7]
        返回如下的二叉树：

        3
        / \
        9  20
        /  \
        15   7
         

        限制：

        0 <= 节点个数 <= 5000

         

        注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

public class _面试题07_重建二叉树 {

    /*
    *
    *
    * 二叉树的前序遍历结果是 中 左 右
    * 二叉树的中序遍历结果是 左 中 右
    *
    * 所以根据中序遍历结果，我们可以找到根节点
    * 而根据前序遍历结果 和 根节点，我们可以把将前序遍历的结果分别分割成 根节点的 左右子树
    *
    * 详解 ： https://zhangxiongfeiv.github.io/post/106.从中序与后序遍历构造二叉树/
    *
    * */
    // 获取中序遍历字典
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] preorder;
    int rootIdx;
    public void buildMap(int[] inorder){
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        buildMap(inorder);

        this.preorder = preorder;
        this.rootIdx = 0;
        return buildTreeHelper(0, preorder.length - 1);
    }

    private TreeNode buildTreeHelper(int is, int ie){
        // 左边索引 > 右边索引 没有元素
        if (is > ie) return null;

        // 根节点
        TreeNode root = new TreeNode(preorder[rootIdx]);

        int index = map.get(preorder[rootIdx]);

        rootIdx ++;

        root.left = buildTreeHelper(is, index - 1);
        root.right = buildTreeHelper(index + 1, ie);
        return root;
    }

}
