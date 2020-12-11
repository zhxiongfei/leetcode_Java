package 二叉搜索树;

import java.util.Stack;

public class _538_把二叉搜索树转换为累加树 {

    /**
     *
     * 递归
     * 做递归类题目, 思路是先想清楚 递归函数的作用
     * coverBST 的作用是 将root转化为 累加树
     *
     * 二叉搜索树的特点是, 右子树所有元素的值 > root.val > 左子树所有元素的值
     *
     * 所以, 递归函数先将 root的右子树转为累加树
     * */
    int add = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;

        convertBST(root.right);
        root.val += add;

        add = root.val;
        convertBST(root.left);
        return root;
    }

    /**
     * 反向中序遍历
     * */
    public TreeNode convertBST1(TreeNode root) {
        if(root == null) return null;

        TreeNode node = root;
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null){

            while (node != null){
                stack.add(node);
                node = node.right;
            }

            node = stack.pop();
            sum += node.val;
            node.val = sum;
            node = node.left;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(2);
        node.right = new TreeNode(13);

        _538_把二叉搜索树转换为累加树 cls = new _538_把二叉搜索树转换为累加树();
        TreeNode resNode = cls.convertBST1(node);
        System.out.println(resNode);
    }
}
