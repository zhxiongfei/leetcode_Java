package 探索系列.探索初级算法;

public class 回文链表 {

    public static boolean isPalindrome(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) slow = slow.next;

        ListNode tmp = reverse(slow);
        while (tmp != null){
            if (head.val != tmp.val) return false;
            head = head.next;
            tmp = tmp.next;
        }

        return true;
    }

    public static ListNode reverse(ListNode head){
        ListNode newHead = null;

        while (head != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }

        return newHead;
    }

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(2);
//        node.next.next.next.next = new ListNode(1);

        isPalindrome(node);
    }

}
