package 二叉搜索树;

/*
二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。

        返回转换后的单向链表的头节点。

        注意：本题相对原题稍作改动

         

        示例：

        输入： [4,2,5,1,3,null,6,0]
        输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binode-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 面试题17_12_BiNode {

    // 二叉树中序遍历
    // 创建一个List存放各个节点， 再串起来称为新的单向链表
    public List<TreeNode> dfs(TreeNode root){

        List<TreeNode> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);

                root = root.left;
            }

            root = stack.pop();
            list.add(root);

            root.left = null;
            root = root.right;
        }

        return list;
    }

    public TreeNode convertBiNode1(TreeNode root) {

        if (root == null) return null;

        List<TreeNode> list = dfs(root);

        TreeNode head = new TreeNode(-1);
        TreeNode node = head;
        for (int i = 0; i < list.size(); i++) {
            node.right = list.get(i);
            node = node.right;
        }

        return head.right;
    }

    // 题目要求在原始的二叉搜索树上直接修改，我们上述做法是构建了新的二叉树。
    // 接下来尝试，在原始的二叉搜索树上修改
    // 迭代
    public TreeNode convertBiNode2(TreeNode root) {
        if (root == null) return null;

        TreeNode head = new TreeNode(-1);
        TreeNode tmp = head;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);

                root = root.left;
            }

            root = stack.pop();

            tmp.right = root;
            tmp = tmp.right;

            root.left = null;
            root = root.right;
        }

        return head.right;
    }

    // 递归
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;

        TreeNode head = new TreeNode(-1);
        TreeNode tmp = head;

        convertBiNode(root.left);

        tmp.right = root;
        tmp = tmp.right;

        root.left = null;

        convertBiNode(root.right);
        return head.right;
    }

}
