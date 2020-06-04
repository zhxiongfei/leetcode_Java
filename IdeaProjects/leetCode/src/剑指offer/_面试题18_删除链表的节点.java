package 剑指offer;

public class _面试题18_删除链表的节点 {

    public ListNode deleteNode(ListNode head, int val) {

        ListNode prev = null;
        ListNode node = head;
        while (node != null){
            if (node.val == val){
                if (prev == null) {
                    head = head.next;
                    return head;
                }
                prev.next = node.next;
            }
            prev = node;
            node = node.next;
        }
        return head;
    }
}
