package 剑指offer;

import java.util.LinkedList;
import java.util.Queue;

/**
输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

        B是A的子结构， 即 A中有出现和B相同的结构和节点值。

        例如:
        给定的树 A:

             3
            / \
           4   5
          / \
         1   2
        给定的树 B：

           4 
          /
         1
        返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

        示例 1：

        输入：A = [1,2,3], B = [3,1]
        输出：false
        示例 2：

        输入：A = [3,4,5,1,2], B = [4,1]
        输出：true
        限制：

        0 <= 节点个数 <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题26_树的子结构 {

    /**
     *
     * 本来想着，跟 面试题 04.10. 检查子树 一模一样
     * 无非是检查子树
     * 要么两棵树 A 和 B 一样。 要么 递归检查 A.left 和 B一样。或者 A.right 和 B一样.
     * 提交后，此测试用例未通过。
     * [10,12,6,8,3,11]
     * [10,12,6,8]
     *
     * 因为 B 不是 A的子树，但是却是 A 的子结构.
     * 发现： 奥，原来子结构 和 子树不一样
     *
     * 下边解法是 与 04.10一样的错误解答.
     *
     * */
    public boolean isSame(TreeNode A, TreeNode B){
        if (A == null && B == null) return true;
        if (A == null || B == null) return false;

        if (A.val != B.val) return false;
        return isSame(A.left, B.left) && isSame(A.right, B.right);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;

        if (isSame(A,B)) return true;

        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     *
     * 既然判断子树不能解决问题，那么我们怎么解决这个问题呢
     * 我的下一种思路是， 遍历二叉树 dfs/bfs 前中后序遍历 递归/非递归 任意一个
     * 分别遍历 A 和 B。 并将结果拼成 StringA 和 StringB
     * 最后判断 StringA 是否包含 StringB
     *
     * */

    StringBuilder sb = new StringBuilder();
    public String dfs(TreeNode A){
        if (A == null) return sb.toString();

        dfs(A.left);
        sb.append(A.val);
        dfs(A.right);

        return sb.toString();
    }

    public String levelTraversal(TreeNode A){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(A);

        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            sb.append(node.val);

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return sb.toString();
    }

    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;

        String stringA = levelTraversal(A);
        sb.replace(0,sb.length(),"");

        String stringB = levelTraversal(B);
        return stringA.contains(stringB);
    }

    //[10,12,6,8,3,11]
    //[10,12,6,8]
    public static void main(String[] args) {
        TreeNode A = new TreeNode(10);
        A.left = new TreeNode(12);
        A.left.left = new TreeNode(8);
        A.left.right = new TreeNode(3);

        A.right = new TreeNode(6);
        A.right.left = new TreeNode(11);

        TreeNode B = new TreeNode(10);
        B.left = new TreeNode(12);
        B.left.left = new TreeNode(8);
        B.right = new TreeNode(6);

        _面试题26_树的子结构 cls = new _面试题26_树的子结构();
        cls.isSubStructure1(A, B);
    }
}
