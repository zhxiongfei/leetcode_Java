package 剑指offer;

/*
0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。

        例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

         

        示例 1：

        输入: n = 5, m = 3
        输出: 3
        示例 2：

        输入: n = 10, m = 17
        输出: 2
         

        限制：

        1 <= n <= 10^5
        1 <= m <= 10^6

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;

/**

 约瑟夫问题
 这个问题是以弗拉维奥·约瑟夫命名的，他是1世纪的一名犹太历史学家。
 他在自己的日记中写道，他和他的40个战友被罗马军队包围在洞中。
 他们讨论是自杀还是被俘，最终决定自杀，并以抽签的方式决定谁杀掉谁。
 约瑟夫斯和另外一个人是最后两个留下的人。约瑟夫斯说服了那个人，他们将向罗马军队投降，不再自杀。
 约瑟夫斯把他的存活归因于运气或天意，他不知道是哪一个。
 —— 【约瑟夫问题】维基百科

 */

public class _剑指_Offer_62_圆圈中最后剩下的数字 {

    // 定义链表 node 节点
    public class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    /**
     *
     *
     * 这道题最常见的解决思路是 单向循环链表
     *
     * 从链表头节点开始遍历，遍历到第 3 个时，删除。并且从第4个接着遍历
     *
     *
     * 下标这种解法，使用链表解决，超时了...
     *
     * 此题目中  n,m的取值范围分别是 :
     *         1 <= n <= 10^5
     *         1 <= m <= 10^6
     *
     *
     * 我们从 m开始删除节点，最终剩下1个节点。需要删除 m-1 次
     * 而每次查找，删除的节点，需要 n 次的查找
     * 所以整体时间复杂度 : O(m * n)
     *
     * 极限情况，会 new 10^5 个 node 节点，循环 10 ^ 6次
     * 好无疑问，耗时会很长
     *
     *
     * */
    public int lastRemaining1(int n, int m) {
        if (n == 0) return 0;
        if (m == 1) return n-1;
        Node head = new Node(0);
        Node node = head;
        for (int i = 1; i < n; i++) {
            node.next = new Node(i);
            node = node.next;
        }
        node.next = head;

        Node current = head;
        while(head.next != head){
            for(int i = 1; i < m; i++){
                current = head;
                head = head.next;
            }
            current.next = current.next.next;
            head = current.next;
        }

        return head.val;
    }


    /**
     *
     * ArrayList
     *
     * */
    public int lastRemaining(int n, int m) {
        if (n == 0) return 0;
        if (m == 1) return n-1;

        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    /**
     *
     * 数学解法
     *
     * */
    public int lastRemaining2(int n, int m) {
        if (n == 0) return 0;
        if (m == 1) return n-1;

        int res = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i < n; i++) {
            res = (res + m) % i;
        }
        return res;
    }


    public static void main(String[] args) {
        _剑指_Offer_62_圆圈中最后剩下的数字 cls = new _剑指_Offer_62_圆圈中最后剩下的数字();
        cls.lastRemaining(12,20);
    }

}
