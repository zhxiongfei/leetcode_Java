package Interview;

/*
给定一个有环链表，实现一个算法返回环路的开头节点。
        有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。


        示例 1：

        输入：head = [3,2,0,-4], pos = 1
        输出：tail connects to node index 1
        解释：链表中有一个环，其尾部连接到第二个节点。

        示例 2：

        输入：head = [1,2], pos = 0
        输出：tail connects to node index 0
        解释：链表中有一个环，其尾部连接到第一个节点。

        示例 3：

        输入：head = [1], pos = -1
        输出：no cycle
        解释：链表中没有环。

        进阶：
        你是否可以不用额外空间解决此题？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/linked-list-cycle-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;

public class _面试题_02_08_环路检测 {

    // set存储遍历过的节点
    // head节点，依次往下遍历
    // 遍历当前节点时，如果set中包含，当前节点就是 环路节点
    // 遍历完链表，没有set中包含的，说明没环。 返回null
    // 因为用到了额外set存储空间，所以时间复杂度比较高
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(N)
    // 题干中进阶 要求 ： 不使用额外存储空间
    // 接下来我们尝试使用 O(1) 的时间复杂度 解决此题目
    public ListNode delegeCycle1(ListNode head){

        HashSet<ListNode> set = new HashSet<>();
        while (head != null){
            if (set.isEmpty()){
                set.add(head);
                head = head.next;
                continue;
            }

            if (set.contains(head)) return head;
            set.add(head);

            head = head.next;
        }
        return null;
    }

    // 快慢指针
    // 快指针一次走两步，慢指针一次走两步
    // 遍历链表， 条件当快指针不为空 且 快指针.next不为空时
    // 遍历过程中如果slow == fast了，就说明链表有环
    // 相等时，将slow指针置为head。 slow和fast均每次走一步，相遇时，即为环路节点
    // 遍历完毕，一直没有相遇，无环，返回null
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(1)
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast){
                break;
            }
        }

        if (fast == null) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
