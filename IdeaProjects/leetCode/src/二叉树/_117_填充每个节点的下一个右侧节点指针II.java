package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
给定一个二叉树

        struct Node {
        int val;
        Node *left;
        Node *right;
        Node *next;
        }
        填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

        初始状态下，所有 next 指针都被设置为 NULL。

         

        进阶：

        你只能使用常量级额外空间。
        使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
         

        示例：



        输入：root = [1,2,3,4,5,null,7]
        输出：[1,#,2,3,#,4,5,7,#]
        解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
         

        提示：

        树中的节点数小于 6000
        -100 <= node.val <= 100

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _117_填充每个节点的下一个右侧节点指针II {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    /**
     *
     * 层序遍历
     * 串起来每一层的节点
     *
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     *
     * 不符合空间复杂度的要求 : 题目要求使用常量级的额外空间.
     *
     * */
    public _116_填充每个节点的下一个右侧节点指针.Node connect1(_116_填充每个节点的下一个右侧节点指针.Node root) {
        if (root == null) return null;
        Queue<_116_填充每个节点的下一个右侧节点指针.Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            _116_填充每个节点的下一个右侧节点指针.Node node = null;
            while (size -- > 0){
                _116_填充每个节点的下一个右侧节点指针.Node tmp = queue.poll();
                tmp.next = node;
                node = tmp;
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
            }
        }
        return root;
    }


    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            if (root.right != null) {
                // 若右子树不为空，则左子树的 next 即为右子树
                root.left.next = root.right;
            } else {
                // 若右子树为空，则右子树的 next 由根节点的 next 得出
                root.left.next = nextNode(root.next);
            }
        }
        if (root.right != null) {
            // 右子树的 next 由根节点的 next 得出
            root.right.next = nextNode(root.next);
        }
        // 先确保 root.right 下的节点的已完全连接，因 root.left 下的节点的连接
        // 需要 root.left.next 下的节点的信息，若 root.right 下的节点未完全连
        // 接（即先对 root.left 递归），则 root.left.next 下的信息链不完整，将
        // 返回错误的信息。可能出现的错误情况如下图所示。此时，底层最左边节点将无
        // 法获得正确的 next 信息：
        //                  o root
        //                 / \
        //     root.left  o —— o  root.right
        //               /    / \
        //              o —— o   o
        //             /        / \
        //            o        o   o
        connect(root.right);
        connect(root.left);
        return root;
    }

    public Node nextNode(Node root){
        if(root==null) return null;

        if(root.left!=null) return root.left;
        if(root.right!=null) return root.right;
        if(root.next!=null) return nextNode(root.next);
        return null;
    }

    public void test(){
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.right.left = new Node(6);
        node.right.right = new Node(7);

        Node res = connect(node);
        System.out.println(res);
    }

    public int findSecondMinimumValue(TreeNode root) {
        return Math.max(root.left.val, root.right.val);
    }

    public static void main(String[] args) {
        _117_填充每个节点的下一个右侧节点指针II cls = new _117_填充每个节点的下一个右侧节点指针II();

        cls.test();
    }
}
