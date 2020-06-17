package 剑指offer;


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

    /// 复习此题目时，可以跟 面试题 04.10一起看

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
     * 子结构 与 子树的区别在于
     * 为其子树时， 则 A中存在某一个子树与B完全一样
     * 而子结构位，A中存在某一个子节点 与 B中节点一样 可以不完全相同
     * 下边解法是 在 04.10基础上的修改版本
     *
     *
     * */

    // 判断 树 B 是否是树 A的子结构
    public boolean contains(TreeNode A, TreeNode B){
        if (B == null) return true;
        if (A == null) return false;
        if (A.val != B.val) return false;

        return contains(A.left, B.left) && contains(A.right, B.right);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 题目中说 约定空树不是任意一个树的子结构
        // 所以 当 B == null 或者 A == null时，返回 false
        if (B == null || A == null) return false;

        // 当 A树 包含 B中所有节点时，B是子结构
        if (contains(A,B)) return true;

        // 递归分别查找 A.left 和 A.right
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }
}
