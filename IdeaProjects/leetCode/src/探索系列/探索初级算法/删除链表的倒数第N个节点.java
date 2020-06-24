package 探索系列.探索初级算法;

public class 删除链表的倒数第N个节点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;

        ListNode prev = new ListNode(-1);
        prev.next = head;
        ListNode curN = head;

        int m = 0;
        while (curN.next != null){
            m ++;
            if (m >= n){
                prev = prev.next;
            }
            curN = curN.next;
        }

        if (prev.next == curN){
            // 删除的是最后一个
            prev.next = null;
        } else if (prev.next == head){
            // 删除的是第一个
            head = head.next;
        }else{
            prev.next = prev.next.next;
        }

        return head;
    }
}
