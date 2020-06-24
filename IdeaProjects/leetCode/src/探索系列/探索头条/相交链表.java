package 探索系列.探索头条;

public class 相交链表 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != null || nodeB != null){
            if (nodeA == null) nodeA = headB;
            if (nodeB == null) nodeB = headA;

            if (nodeA == nodeB) return headA;

            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

}
