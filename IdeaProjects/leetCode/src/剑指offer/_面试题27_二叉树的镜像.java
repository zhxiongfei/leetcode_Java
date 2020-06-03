package 剑指offer;

/*
请完成一个函数，输入一个二叉树，该函数输出它的镜像。

        例如输入：

             4
           /   \
          2     7
         / \   / \
        1   3 6   9
        镜像输出：

             4
           /   \
          7     2
         / \   / \
        9   6 3   1

         

        示例 1：

        输入：root = [4,2,7,1,3,6,9]
        输出：[4,7,2,9,6,3,1]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题27_二叉树的镜像 {

    /*
    *
    * 二叉树的镜像
    * 其实就是二叉树的反转
    *
    * 再进一步， 就是二叉树的遍历
    * 前序/中序/后序/层序， 递归/迭代都可以实现。
    * 其它解法， 可以看二叉树的遍历来复习
    *
    * */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(7);

        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);

        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(9);

        mirrorTree(node);
    }
}
