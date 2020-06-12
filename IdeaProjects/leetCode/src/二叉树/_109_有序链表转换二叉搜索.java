package 二叉树;

/*
给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

        本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

        示例:

        给定的有序链表： [-10, -3, 0, 5, 9],

        一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

        0
        / \
        -3   9
        /   /
        -10  5

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class _109_有序链表转换二叉搜索 {

    /**
     *
     * 看到题目，比较容易想到思路
     * 有序链表，所以链表中间节点一定是二叉搜索树的root节点
     * 找到root后， 分治策略，分别找左右两遍的中心节点
     *
     * 所以题目第一步，找链表中心节点
     * 找到中心节点后
     * 将链表分为两部分 左右两遍分别递归查找 左右的中心节点
     *
     * */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;
    }

}
