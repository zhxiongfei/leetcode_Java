package Interview;

/*
编写一个函数，检查输入的链表是否是回文的。

         

        示例 1：

        输入： 1->2
        输出： false
        示例 2：

        输入： 1->2->2->1
        输出： true
         

        进阶：
        你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/palindrome-linked-list-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;
import java.util.Stack;

public class _面试题_02_06_回文链表 {

    // 使用栈，加入前一半元素，再与后一半元素逐个对比
    // 时间复杂度 O(N) 遍历一遍链表元素
    // 空间复杂度 O(N) 使用了额外的栈存储控件
    public boolean isPalindrome1(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;

        // 前一半入栈
        while (fast != null && fast.next != null){

            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        // 说明节点总数是 单数
        if (fast != null) slow = slow.next;

        // 栈中元素和后一半元素对比
        // 不一致， return false
        // 全部一致， return true
        while (slow != null){
            if (stack.pop() != slow.val) return false;
            slow = slow.next;
        }

        return true;
    }

    // 前边相同，找到链表中间节点
    // 翻转中间节点
    // 依次比较头结点 和 中间节点
    // 有不一致的则 return false
    // 全部都一致，则 return true
    // 时间复杂度 : O(N) 遍历一整遍 链表
    // 空间复杂度 : O(1) 没有使用额外内存控件
    public boolean isPalindrome(ListNode head) {

        // 找到中间元素
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 说明节点总数是 单数
        if (fast != null) slow = slow.next;

        // 翻转slow
        ListNode newSlow = null;
        while (slow != null){
            ListNode tmp = slow.next;
            slow.next = newSlow;
            newSlow = slow;
            slow = tmp;
        }

        while (newSlow != null){

            if (head.val != newSlow.val) return false;

            head = head.next;
            newSlow = newSlow.next;
        }

        return true;
    }


    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
//        node.next.next = new ListNode(3);
//        node.next.next.next = new ListNode(2);
//        node.next.next.next.next = new ListNode(1);

        _面试题_02_06_回文链表 cls = new _面试题_02_06_回文链表();
        cls.isPalindrome(node);
    }

}


