package 链表;

/**
给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

        请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

        示例 1:

        输入: 1->2->3->4->5->NULL
        输出: 1->3->5->2->4->NULL
        示例 2:

        输入: 2->1->3->5->6->4->7->NULL
        输出: 2->3->6->7->1->5->4->NULL
        说明:

        应当保持奇数节点和偶数节点的相对顺序。
        链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/odd-even-linked-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _328_奇偶链表 {

    /**
     *
     * 刚刚写过 86.分隔链表
     * 感觉如果使用额外存储空间的话，跟分隔链表没有什么区别
     * 一个奇数链表，一个偶数链表分别存储奇数节点 和 偶数节点
     * 最后再把 奇数节点 和 偶数节点串起来就可以了
     *
     * 但是题目要求，原地修改. 如何不使用额外的内存空间，做到原地修改呢?
     *
     * */
    public static ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode oddHead = new ListNode(-1);
        ListNode oddNode = oddHead;

        ListNode evenHead = new ListNode(-1);
        ListNode evenNode = evenHead;

        boolean flag = true;
        while (head != null){
            if (flag){
                evenNode.next = head;
                evenNode = evenNode.next;
            }else {
                oddNode.next = head;
                oddNode = oddNode.next;
            }
            flag = !flag;
            head = head.next;
        }
        oddNode.next = evenHead.next;
        evenNode.next = null;

        return oddHead;
    }

    public static ListNode oddEvenList(ListNode head){
        // 链表为空，或者只有一个节点，直接return
        if (head == null || head.next == null) return head;
        // odd 奇数节点     用来串起来奇数节点
        // even 偶数节点    用来串起来偶数节点
        // evenHead 偶数节点的头节点    用来保存偶数节点的头节点
        ListNode odd = head, even = head.next, evenHead = even;

        // 当偶数节点不为空，且 偶数节点有下一个元素
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);

        oddEvenList(node);
    }
}
