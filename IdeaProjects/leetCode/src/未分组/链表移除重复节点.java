package 未分组;

import java.util.ArrayList;
import java.util.HashSet;

public class 链表移除重复节点 {

    public static ListNode removeDuplicateNodes(ListNode head) {
        // 虚拟头节点
        HashSet<Integer> set = new HashSet<>();

        ListNode cur = head;
        ListNode pre = new ListNode(-1);
        while(head != null){
            if(set.contains(head.val)){
                // 重复 jump
                pre.next = cur.next;
            }else{
                // 不重复 数组中添加 串连链表
                set.add(head.val);
                pre = cur;
            }

            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next = new ListNode(3);
        node.next = new ListNode(3);
        node.next = new ListNode(2);
        node.next = new ListNode(1);

        removeDuplicateNodes(node);
    }
}
