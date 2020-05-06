package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _107_二叉树的层次遍历II {

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList list = new ArrayList();
        if (root == null) return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        TreeNode node = root;
        int height = 1;
        while (!q.isEmpty()){
            ArrayList a = new ArrayList();
            int count = q.size();
            while (count > 0){
                node = q.remove();
                if (node.left != null)  q.add(node.left);
                if (node.right != null) q.add(node.right);
                a.add(node.val);
                count --;
            }
            list.add(0,a);
            height ++;
        }
        System.out.println(list.size());
        return list;
    }
}
