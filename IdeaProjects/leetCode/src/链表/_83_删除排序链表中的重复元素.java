/*
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

        示例 1:

        输入: 1->1->2
        输出: 1->2
        示例 2:

        输入: 1->1->2->3->3
        输出: 1->2->3

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

package 链表;

import java.util.HashSet;

public class _83_删除排序链表中的重复元素 {

    // 思路一：
    // 遍历链表
    // 用set存储遍历过的值
    // 当遍历当前元素 set中包含时，删除当前元素
    // 不包含时 遍历下一个元素，set中添加当前元素
    HashSet<Integer> set = new HashSet<>();
    public ListNode removeDuplicateNodes(ListNode head) {

        ListNode prev = null;
        ListNode node = head;
        while (node != null){
            if (set.contains(node.val)){
                // 删除node
                prev.next = node.next;
            }else {
                set.add(node.val);
                prev = node;
            }
            node = node.next;
        }

        return head;
    }

    public ListNode deleteDuplicates(ListNode head){
        ListNode current = head;
        while (current != null && current.next != null){
            if (current.val == current.next.val){
                current.next = current.next.next;
            }else {
                current = current.next;
            }
        }

        return head;
    }
}
