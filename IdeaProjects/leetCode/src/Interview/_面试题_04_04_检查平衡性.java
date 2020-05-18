package Interview;

/*
实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。


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
        链接：https://leetcode-cn.com/problems/check-balance-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 */

import java.util.LinkedList;
import java.util.Queue;

public class _面试题_04_04_检查平衡性 {

    // 思路一：
    // 从头节点开始，出节点高度，所有节点的左右节点高度差<=1时，平衡
    // 否则不平衡

    // 二叉树层序遍历，求节点高度
    public int height1(TreeNode root){
        if (root == null) return 0;

        int height = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){

                TreeNode node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                size --;
            }

            height ++;
        }
        return height;
    }

    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;

        boolean isbalance = Math.abs(height(root.left) - height(root.right)) <= 1;
        return isBalanced1(root.left) && isBalanced1(root.right) && isbalance;
    }

    // 思路二：
    // 上述方法计算height存在大量冗余
    // 每次调用height时，同时计算其子树的高度
    // 但是自底向上计算，每个子树的高度只会计算一次
    // 可以递归先计算当前节点子节点高度
    // 然后通过子节点高度判断当前节点是否平衡
    public int height(TreeNode root){
        if (root == null) return 0;

        int l = height(root.left);
        if (l == -1) return -1;

        int r = height(root.right);
        if (r == -1) return -1;

        return Math.abs(r - l) <= 1 ? Math.max(l,r) + 1 : -1;
    }

    public boolean isBalanced(TreeNode root){
        return height(root) != -1;
    }
}
