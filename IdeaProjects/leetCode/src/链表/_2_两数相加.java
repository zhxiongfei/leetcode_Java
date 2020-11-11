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
import java.util.List;
import java.util.Set;

public class _2_两数相加 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(-1);
        ListNode cur = newNode;

        int carry = 0;
        while (l1 != null || l2 != null){

            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;

            int sum = v1 + v2 + carry;
            carry = 0;
            if (sum >= 10){
                sum -= 10;
                carry = 1;
            }

            cur.next = new ListNode(sum);
            cur = cur.next;
        }
        if (carry > 0){
            cur.next = new ListNode(1);
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
}