package 二叉树;

import javax.management.QueryEval;
import javax.sound.sampled.Line;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
计算给定二叉树的所有左叶子之和。

        示例：

        3
        / \
        9  20
        /  \
        15   7

        在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sum-of-left-leaves
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _404_左叶子和 {

    /**
     * 看到题目第一反应，bfs可以解决
     * <p>
     * 深度遍历二叉树,当遍历到某节点，其左子节点不为空，且其左子节点为叶子节点(无左右子节点)时
     * 最终结果 + 当前节点左子节点的值
     * <p>
     * 再分别继续遍历其左右子树，符合条件的节点， 加其值
     * <p>
     * 一开始代码是按照方法一的方式去写的。 提交后，报错了。 查看具体的测试用例是
     * [-9,-3,2,null,4,4,0,-6,null,-5]
     * <p>
     * 经过将数组图形化转成二叉树查看后，发觉貌似少了一种情况
     * 方法一中，当root节点的left节点为空时，直接return， 认为root节点上没有符合条件的节点
     * 但是我们发现，当root节点的右子节点的子节点中，有左节点且为叶子节点时，这种情况漏掉了
     * <p>
     * 然后我们改成了方法而
     * 只有当root为空时，我们才认为root节点上，没有符合条件的节点，直接return
     * 当root.left不为空，且其为叶子节点时，累加其值。
     * 最终经过修改，顺利通过了所有测试用力
     * 且 用时击败了 100%的用户
     **/
    int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {

        // 方法一 错误示范
        // if (root == null || root.left == null) return result;
//        if (root.left != null && root.left.right == null)
//            result += root.left.val;

        // 方法二 正确方法
        if (root == null) return result;
        if (root.left != null && root.left.left == null && root.left.right == null)
            result += root.left.val;

        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);

        return result;
    }


    /**
     * 上边方法是递归，既然是dfs解决的问题
     * 那么使用迭代应该同样可以解决问题
     * <p>
     * 顺便复习下，迭代二叉树遍历
     */

    //迭代 中序遍历 因为写二叉搜索树的题目，用到中序遍历的情况特别多， 所以最熟悉的就是中序遍历 😳
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (root.left != null && root.left.left == null && root.left.right == null)
                result += root.left.val;

            root = root.right;
        }
        return result;
    }

    // 迭代 前序遍历 说实话，一下子没想起来，在稿纸上自己画一画先
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && node.left.left == null && node.left.right == null)
                result += node.left.val;

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

    // 迭代 后序遍历
    public int sumOfLeftLeaves3(TreeNode root) {
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if ((node.left == null && node.right == null) || (pre != null && (pre == node.left || pre == node.right))) {
                // 叶子节点 或者 上一个访问的节点是此节点的子节点时 出栈
                if (node.left != null && node.left.left == null && node.left.right == null)
                    result += node.left.val;

                pre = node;
                stack.pop();
            } else {
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
        return result;
    }

    // 迭代 层序遍历
    public int sumOfLeftLeaves4(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            if (node.left != null && node.left.left == null && node.left.right == null)
                result += node.val;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }
}