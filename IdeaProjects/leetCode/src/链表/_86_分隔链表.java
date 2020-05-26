package 链表;

/*
给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

        你应当保留两个分区中每个节点的初始相对位置。

        示例:

        输入: head = 1->4->3->2->5->2, x = 3
        输出: 1->2->2->4->3->5

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/partition-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _86_分隔链表 {

    //申请两个新链表more和less。more用来存储>=界定值的值。less用来存储小于界定值的值。
    //然后将less的next节点指向more的头部。

    public static ListNode partition(ListNode head, int x) {

        if (head == null || head.next == null) return head;

        ListNode more = new ListNode(-1);
        ListNode moreHead = more;

        ListNode less = new ListNode(-1);
        ListNode lessHead = less;

        while (head != null){
            if (head.val < x){
                less.next = head;
                less = less.next;
            }else{
                more.next = head;
                more = more.next;
            }

            head = head.next;
        }

        more.next = null;

        less.next = moreHead.next;
        return lessHead.next;
    }

    public static void main(String[] args){

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        ListNode n7 = new ListNode(1);

        n1.next = n2;n2.next = n3; n3.next = n4; n4.next = n5; n5.next = n6;n6.next = n7;

        ListNode res = partition1(n1,3);
        if (res != null){

        }
    }


    public static ListNode partition1(ListNode head, int x) {

        ListNode before = new ListNode(-1);
        ListNode beforeHead = before;
        ListNode after = new ListNode(-1);
        ListNode afterHead = after;

        while (head != null){
            int val = head.val;
            if (val < x){
                before.next = head;
                before = before.next;
            }else {
                after.next = head;
                after = after.next;
            }

            head = head.next;
        }

        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;
    }



}
