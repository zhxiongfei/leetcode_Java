/*
删除链表中等于给定值 val 的所有节点。

示例:

输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5

https://leetcode-cn.com/problems/remove-linked-list-elements/
* */

package 链表;

import java.util.List;

public class _203_移除链表元素 {

//    输入: 1->2->6->3->4->5->6, val = 6
//    输出: 1->2->3->4->5

//    1,1 ,val = 1

//    1,2,2,1 val = 2;

//    1  val = 1
    public ListNode removeElements(ListNode head, int val) {

        if (head == null) return null;
        if (head.next == null && head.val == val) return null;

        ListNode prev = null;
        ListNode node = head;
        ListNode next = node.next;

        while (node != null){
            if (node.val == val){ // 相等 - 删除
                if (prev == null){
                    head = next;

                }else{
                    prev.next = next;
                }

                node = node.next;
                if (node == null){
                    next = null;
                }else{
                    next = node.next;
                }
                continue;
            }
            if (next == null){
                break;
            }
            // 不相等 下轮循环
            prev = node;
            node = node.next;
            if (node == null){
                next = null;
            }else{
                next = node.next;
            }
        }

        return head;
    }

    public ListNode removeElements1(ListNode head, int val){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            if (cur.val == val){

                if(cur.next == null){
                    if(pre == null) return null;
                    pre.next = null;
                    break;
                }
                cur.val = cur.next.val;
                cur.next= cur.next.next;
                continue;
            }

            pre = cur;
            cur = cur.next;
        }

        return head;
    }


    //    输入: 1->2->6->3->4->5->6, val = 6
    //    输出: 1->2->3->4->5
    public static ListNode removeElements2(ListNode head, int val){
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;

        while (head != null){
            if (head.val != val){
                cur.next =  head;
                cur = head;
            }

            head = head.next;
        }

        cur.next = null;
        return newHead.next;
    }

    public static void main(String[] args) {

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        removeElements2(n1,6);
    }
}
