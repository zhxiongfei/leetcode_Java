package 探索初级算法;

public class 反转链表 {

    public ListNode reverseList1(ListNode head) {

        ListNode newHead = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode node = reverseList(head.next);
        node.next.next = head;
        head.next = null;

        return node;
    }

}
