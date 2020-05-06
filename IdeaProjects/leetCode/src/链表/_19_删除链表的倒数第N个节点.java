package 链表;

/*
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

        示例：

        给定一个链表: 1->2->3->4->5, 和 n = 2.

        当删除了倒数第二个节点后，链表变为 1->2->3->5.
        说明：

        给定的 n 保证是有效的。

        进阶：

        你能尝试使用一趟扫描实现吗？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

//让前面的指针先移动n步，之后前后指针共同移动直到前面的指针到尾部为止
//设立预先指针 pre
//设预先指针 pre 的下一个节点指向 head，设前指针为 start，后指针为 end，二者都等于 start 先向前移动n步
//之后 start 和 end 共同向前移动，此时二者的距离为 n，当 start 到尾部时，end 的位置恰好为倒数第 n 个节点

public class _19_删除链表的倒数第N个节点 {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
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

    public static void main(String[] args){

        // listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]

        ListNode headA = new ListNode(1);
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(4);
        ListNode a4 = new ListNode(5);
//        headA.next = a1;
//        a1.next = a2;
//        a2.next = a3;
//        a3.next = a4;

        ListNode head = removeNthFromEnd(headA,1);

        if (head != null){
            while (head != null){
                System.out.println(head.val);
                head = head.next;
            }
        }
    }
}
