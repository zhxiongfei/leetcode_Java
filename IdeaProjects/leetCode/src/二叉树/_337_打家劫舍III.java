package 二叉树;

import java.util.HashMap;

public class _337_打家劫舍III {

    /**
     *
     * 递归
     * 递归函数的作用是 : 求出 包含 root 节点 能抢劫的最大金额
     *
     * */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        // money 代表 包含 root节点的 能盗取的最大值
        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }
        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }
        return Math.max(money, rob(root.left) + rob(root.right));
    }

    /**
     *
     * 记忆化子问题
     *
     * */
    public int rob1(TreeNode root){
        HashMap<TreeNode, Integer> map = new HashMap<>();

        return rob(root, map);
    }

    public int rob(TreeNode root, HashMap<TreeNode, Integer> map){
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);

        int money = root.val;
        if (root.left != null){
            money += (rob(root.left.left, map) + (rob(root.left.right, map)));
        }

        if (root.right != null){
            money += (rob(root.right.left, map) + (rob(root.right.right, map)));
        }

        int res = Math.max(money, rob(root.left, map) + rob(root.right, map));
        map.put(root, res);
        return res;
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.right = new TreeNode(3);
        node.right.right = new TreeNode(1);

        _337_打家劫舍III cls = new _337_打家劫舍III();
        int res = cls.rob(node);
        System.out.println(res);
    }

}
