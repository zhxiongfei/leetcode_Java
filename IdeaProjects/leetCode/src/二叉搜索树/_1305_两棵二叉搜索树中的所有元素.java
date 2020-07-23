package 二叉搜索树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _1305_两棵二叉搜索树中的所有元素 {


    public List<Integer> dfs(TreeNode root, List<Integer> list){
        if (root == null) return list;

        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);

        return list;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // root1 = [2,1,4], root2 = [1,0,3]
        List<Integer> l1 = dfs(root1, new LinkedList<>());
        List<Integer> l2 = dfs(root2, new LinkedList<>());

        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < l1.size() || j < l2.size()){
            int n1 = i < l1.size() ? l1.get(i) : Integer.MAX_VALUE;
            int n2 = j < l2.size() ? l2.get(j) : Integer.MAX_VALUE;

            if (n1 > n2) {
                j ++;
                res.add(n2);
            }else {
                i ++;
                res.add(n1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);

        _1305_两棵二叉搜索树中的所有元素 cls = new _1305_两棵二叉搜索树中的所有元素();
        cls.getAllElements(root1, root2);
    }

}
