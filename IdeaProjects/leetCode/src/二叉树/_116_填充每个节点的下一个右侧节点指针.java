package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

        struct Node {
        int val;
        Node *left;
        Node *right;
        Node *next;
        }
        填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

        初始状态下，所有 next 指针都被设置为 NULL。

        示例：

        输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

        输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

        解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
         

        提示：

        你只能使用常量级额外空间。
        使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _116_填充每个节点的下一个右侧节点指针 {

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
    public Node connect1(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            Node node = null;
            while (size -- > 0){
                Node tmp = queue.poll();
                tmp.next = node;
                node = tmp;
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
            }
        }
        return root;
    }

    /**
     *
     * 递归 dfs
     * 时间复杂度 : O(n)
     * 空间复杂度为 树高 , 因为题目中说明 递归的栈深度不算额外内存空间
     * 所以符合题目要求
     *
     * */
    public Node connect(Node root){
        if (root == null) return null;

        // 处理同一根节点的情况
        if (root.left != null)
            root.left.next = root.right;

        // 处理不在同一个根节点的情况
        if (root.right != null && root.next != null){
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;
    }

    public void test(){
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);

        Node res = connect(node);
        System.out.println(res);
    }

    public static void main(String[] args) {
        _116_填充每个节点的下一个右侧节点指针 cls = new _116_填充每个节点的下一个右侧节点指针();

        cls.test();
    }
}

