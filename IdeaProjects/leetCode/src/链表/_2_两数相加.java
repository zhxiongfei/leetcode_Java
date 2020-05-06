package 链表;

/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

        如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

        您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        示例：

        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/add-two-numbers
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class _2_两数相加 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode newNode = new ListNode(-1);
        ListNode curNode = newNode;

        int cnt = 0;    // 进位
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int sum = v1 + v2 + cnt;

            ListNode tmp = new ListNode((sum) % 10);
            cnt = sum / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

            curNode.next = tmp;
            curNode = tmp;
        }
        if (cnt == 1) {
            curNode.next = new ListNode(1);
        }
        return newNode.next;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = null;

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = null;

        ListNode res = addTwoNumbers(l1, l2);
        if (res != null) {
            System.out.println(res);
        }
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(-1);
        ListNode curNode = newNode;
        while (l1 != null || l2 != null){
            if (l1 == null){
                curNode.next = l2;
                break;
            }
            if (l2 == null){
                curNode.next = l1;
                break;
            }
            if (l1.val <= l2.val){
                curNode.next = l1;
                l1 = l1.next;
            }else {
                curNode.next = l2;
                l2 = l2.next;
            }
            curNode = curNode.next;
        }
        return newNode.next;
    }
}