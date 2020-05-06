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

public class _83_删除排序链表中的重复元素 {

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if(head == null || head.next == null) return head;

            int val = head.val;

            ListNode prev = head;
            ListNode next = head.next;

            while (next != null){

                if (next.val == val){
                    if (next.next != null){

                        next.val = next.next.val;
                        next.next = next.next.next;
                        continue;
                    }else{
                        // 最后一个
                        prev.next = null;
                        return head;
                    }
                }

                prev = next;
                val = next.val;
                next = next.next;
            }

            return head;
        }
    }

    // 输入: 1,1,1,1,1,1,1,2
    public ListNode deleteDuplicates1(ListNode head){
        ListNode current = head;
        while (current != null && current.next != null){
            if (current.val == current.next.val){
                current.next = current.next.next;
            }else{
                current = current.next;
            }
        }

        return head;
    }
}
