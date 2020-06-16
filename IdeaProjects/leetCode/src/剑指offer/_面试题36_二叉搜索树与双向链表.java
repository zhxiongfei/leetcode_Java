package 剑指offer;

/*
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

         

        为了让您更好地理解问题，以下面的二叉搜索树为例：

         



         

        我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

        下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

         



         

        特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

public class _面试题36_二叉搜索树与双向链表 {

    /**
     * 观察题目
     * 可以看出，二叉搜索树转为双向链表之后，为二叉树中序遍历的结果
     * 所以 dfs中序遍历二叉树
     * <p>
     * 记录 pre指针 和 head指针
     * pre表示 当前节点的 上一个
     * head表示 头节点
     *
     * dfs 深度优先遍历 中序遍历
     * 当 pre 为空时， 为第一次遍历，头节点设置成 cur, 且把node.left = pre.pre = cur
     * 当 pre 不为空时, 不是第一次遍历，把pre 和 cur串起来，之后pre = cur
     */
    Node pre = null, head = null;

    public Node treeToDoublyList1(Node root) {
        if (root == null) return null;

        // dfs
        dfs(root);
        // 最后 head 为头节点， pre 为尾节点， 把head 和 pre串起来
        head.left = pre;
        pre.right = head;

        // head 就是链表当头节点
        return head;
    }

    void dfs(Node cur) {
        if (cur == null) return;
        dfs(cur.left);

        if (pre != null) {
            // 当 pre 不为空时, 不是第一次遍历，把pre 和 cur串起来，之后pre = cur
            pre.right = cur;
        } else {
            // 为第一次遍历，头节点设置成 cur, 且把node.left = pre.pre = cur
            head = cur;
        }
        cur.left = pre;
        pre = cur;

        dfs(cur.right);
    }
}