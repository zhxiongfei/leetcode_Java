package 剑指offer;

/*
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

         

        示例 1：

        输入：head = [1,3,2]
        输出：[2,3,1]
         

        限制：

        0 <= 链表长度 <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class _面试题06_从尾到头打印链表 {


    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }

        int[] res = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()){
            res[i++] = stack.pop();
        }
        return res;
    }

        // 链表先反转，再打印
    public int[] reversePrint1(ListNode head) {

        ArrayList<Integer> list = new ArrayList<>();
        ListNode newHead = reverse(head);
        while (newHead != null){
            list.add(newHead.val);
            newHead = newHead.next;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 头插法
     * */
    public ListNode reverse1(ListNode head){
        ListNode newHead = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    /**
     * 递归
     */
    private ListNode reverse(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
