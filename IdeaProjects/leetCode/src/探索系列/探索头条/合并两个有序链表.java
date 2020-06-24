package 探索系列.探索头条;

public class 合并两个有序链表 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode newHead = new ListNode(-1);
        ListNode node = newHead;
        while (l1 != null || l2 != null){

            if (l1 == null){
                node.next = l2;
                break;
            }

            if (l2 == null){
                node.next = l1;
                break;
            }

            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 < v2){
                node.next = l1;
                l1 = l1.next;
            }else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(4);

        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);

        mergeTwoLists(n1, n2);
    }

}
