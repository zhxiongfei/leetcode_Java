package 二叉树;

/*
如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。

        只有给定的树是单值二叉树时，才返回 true；否则返回 false。

         

        示例 1：



        输入：[1,1,1,1,1,null,1]
        输出：true
        示例 2：



        输入：[2,2,2,5,2]
        输出：false
         

        提示：

        给定树的节点数范围是 [1, 100]。
        每个节点的值都是整数，范围为 [0, 99] 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/univalued-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.LinkedList;
import java.util.Queue;

public class _965_单值二叉树 {

    // 递归
    public boolean univalTree(TreeNode node,int val){
        if (node == null) return true;
        if (node.val != val) return false;
        return univalTree(node.left,val) && univalTree(node.right,val);
    }

    // 迭代
    public boolean univalTree1(TreeNode node,int val){
        if (node == null) return true;

        Queue<TreeNode> q = new LinkedList();
        q.add(node);

        while (!q.isEmpty()){
            TreeNode cur = q.poll();
            if (cur.val != val) return false;

            if (cur.left != null) q.add(cur.left);
            if (cur.right != null) q.add(cur.right);
        }

        return true;
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        int val = root.val;

        return univalTree(root,val);
    }
}
