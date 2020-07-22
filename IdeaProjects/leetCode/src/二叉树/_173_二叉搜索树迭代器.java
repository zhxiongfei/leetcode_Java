package 二叉树;
import java.util.LinkedList;
import java.util.List;

/**
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

        调用 next() 将返回二叉搜索树中的下一个最小的数。

         

        示例：



        BSTIterator iterator = new BSTIterator(root);
        iterator.next();    // 返回 3
        iterator.next();    // 返回 7
        iterator.hasNext(); // 返回 true
        iterator.next();    // 返回 9
        iterator.hasNext(); // 返回 true
        iterator.next();    // 返回 15
        iterator.hasNext(); // 返回 true
        iterator.next();    // 返回 20
        iterator.hasNext(); // 返回 false
         

        提示：

        next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
        你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-search-tree-iterator
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _173_二叉搜索树迭代器 {

    List<Integer> list;
    public void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        System.out.println(root.val);
        dfs(root.right);
    }

    public _173_二叉搜索树迭代器(TreeNode root) {
        list = new LinkedList<>();
        dfs(root);
    }

    /** @return the next smallest number */
    public int next() {
        return list.remove(0);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return list.isEmpty();
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(7);
        node.left = new TreeNode(3);
        node.right = new TreeNode(15);
        node.right.left = new TreeNode(9);
        node.right.right = new TreeNode(20);

        _173_二叉搜索树迭代器 cls = new _173_二叉搜索树迭代器(node);
        cls.next();
    }
}
