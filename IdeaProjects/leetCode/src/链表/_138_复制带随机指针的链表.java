package 链表;

import java.util.HashMap;
import java.util.Queue;

/**
给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

        要求返回这个链表的 深拷贝。 

        我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

        val：一个表示 Node.val 的整数。
        random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
         

        示例 1：



        输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
        输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
        示例 2：



        输入：head = [[1,1],[2,1]]
        输出：[[1,1],[2,1]]
        示例 3：



        输入：head = [[3,null],[3,0],[3,null]]
        输出：[[3,null],[3,0],[3,null]]
        示例 4：

        输入：head = []
        输出：[]
        解释：给定的链表为空（空指针），因此返回 null。
         

        提示：

        -10000 <= Node.val <= 10000
        Node.random 为空（null）或指向链表中的节点。
        节点数目不超过 1000 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _138_复制带随机指针的链表 {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 记忆化递归
     * hashmap存储遍历过的节点
     * */
    HashMap <Node, Node> visitedMap = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        if (visitedMap.containsKey(head)) return visitedMap.get(head);

        Node newHead = new Node(head.val);
        visitedMap.put(head, newHead);
        newHead.next = copyRandomList(head.next);
        newHead.random = copyRandomList(head.random);

        return newHead;
    }

    public static void main(String[] args) {

        _138_复制带随机指针的链表 cls = new _138_复制带随机指针的链表();
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        cls.copyRandomList(node1);
    }
}
