package Interview;

/*
检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。

        如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。

        示例1:

        输入：t1 = [1, 2, 3], t2 = [2]
        输出：true
        示例2:

        输入：t1 = [1, null, 2, 4], t2 = [3, 2]
        输出：false
        提示：

        树的节点数目范围为[0, 20000]。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/check-subtree-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_04_10_检查子树 {

    // 思路一：
    // 二叉树中序遍历后，保存为字符串
    // 判断t1生成的串，是否包含t2生成的串
    StringBuilder sb = new StringBuilder();
    public String inorderTraversal(TreeNode node){
        if (node == null) return sb.toString();

        inorderTraversal(node.left);
        sb.append(node.val);
        inorderTraversal(node.right);

        return sb.toString();
    }

    public boolean checkSubTree1(TreeNode t1, TreeNode t2) {

        String s1 = inorderTraversal(t1);
        sb.delete(0,s1.length());

        String s2 = inorderTraversal(t2);
        return s1.contains(s2);
    }

    // 思路二：
    // t2 是 t1的子树，有三种情况。 1，t1与t2一样。2，t1.左子树中有与t2相等的树。 3，t1.右子树中有与t2相等的树
    // 不满足以上三种情况，则t2不为t1的子树
    // 以下递归实现
    public boolean isSameTree(TreeNode t1, TreeNode t2){
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;

        return isSameTree(t1.left,t2.left) && isSameTree(t1.right, t2.right);
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) return true;
        if (t1 == null) return false;

        if (isSameTree(t1,t2) == true) return true;

        boolean left = checkSubTree(t1.left, t2);
        boolean right = checkSubTree(t1.right, t2);

        return left || right;
    }

}
