package 剑指offer;

public class _面试题18_删除链表的节点 {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;

        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null){
            if (cur.val == val){
                prev.next = cur.next;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        return head;
    }

}
