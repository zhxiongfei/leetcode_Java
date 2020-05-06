package 二叉树;

/*
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。

        示例 :
        给定二叉树

             1
            / \
           2   3
          / \
         4   5
        返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

        注意：两结点之间的路径长度是以它们之间边的数目表示。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.LinkedList;
import java.util.Queue;

public class _543_二叉树的直径 {

    public int height1(TreeNode node){
        if (node == null) return 0;

        return Math.max(height1(node.left),height1(node.right)) + 1;
    }

    public int height(TreeNode node){
        if (node == null) return 0;

        Queue <TreeNode>queue = new LinkedList();
        queue.add(node);

        int height = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                TreeNode n = queue.poll();
                if (n.left != null) queue.add(n.left);
                if (n.right != null) queue.add(n.right);

                size --;
            }

            height ++;
        }

        return height;
    }

    int max;
    public int diameterOfBinaryTree(TreeNode root) {

        if (root == null) return 0;
        int max = height(root.left) + height(root.right);

        int left  = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);

        if (left > right) return Math.max(max,left);
        return Math.max(max,right);
    }
}
