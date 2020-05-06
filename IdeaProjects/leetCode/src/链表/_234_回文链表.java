package 链表;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/*
请判断一个链表是否为回文链表。

        示例 1:

        输入: 1->2
        输出: false
        示例 2:

        输入: 1->2->2->1
        输出: true
        进阶：
        你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/palindrome-linked-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _234_回文链表 {

    // 使用栈
    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;
        Stack <Integer>s = new Stack();

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            s.push(slow.val);

            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) slow = slow.next;

        while (slow != null){
            int val = s.pop();
            if (val != slow.val) return false;

            slow = slow.next;
        }

        return s.isEmpty();
    }

    public static void main(String[] args){
        int[] nums = {1,2};
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            cur.next = node;
            cur = cur.next;
        }

        System.out.println(isPalindrome2(head));
    }

    // 使用数组
    public static boolean isPalindrome1(ListNode head) {

        ArrayList list = new ArrayList();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }

        System.out.println(list);
        int begain = 0;
        int end = list.size() - 1;

        while (begain <= end){
            if (!list.get(begain).equals(list.get(end))) return false;
            begain ++;
            end --;
        }

        return true;
    }

    // 还可以反转链表
    public static boolean isPalindrome2(ListNode head){

        ListNode middel = middle(head);
        ListNode revertHead = revert(middel);

        while (revertHead != null){
            if (revertHead.val != head.val) return false;

            head = head.next;
            revertHead = revertHead.next;
        }

        return true;
    }

    public static ListNode middle(ListNode head){

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) slow = slow.next;
        return slow;
    }

    public static ListNode revert(ListNode node){

        ListNode newHead = null;
        while (node != null){
            ListNode tmp = node.next;
            node.next = newHead;
            newHead = node;
            node = tmp;
        }

        return newHead;
    }

}
