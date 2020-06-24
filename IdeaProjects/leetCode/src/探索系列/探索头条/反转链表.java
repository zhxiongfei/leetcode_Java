package 探索系列.探索头条;

public class 反转链表 {

    public ListNode reverseList(ListNode head) {

        ListNode newHead = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

}
