---
title: "_剑指offer_52_两个链表的第一个公共节点"
date: 2020-06-02T15:08:04+08:00
draft: true
tags: ["数据结构", "链表"]
---

# [面试题52. 两个链表的第一个公共节点](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)

> 输入两个链表，找出它们的第一个公共节点。
>
> 如下面的两个链表：
>
> 
>
> 在节点 c1 开始相交。
>
>  
>
> 示例 1：
>
> 
>
> 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
> 输出：Reference of the node with value = 8
> 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
>
>
> 示例 2：
>
> 
>
> 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
> 输出：Reference of the node with value = 2
> 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
>
>
> 示例 3：
>
> 
>
> 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
> 输出：null
> 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
> 解释：这两个链表不相交，因此返回 null。
>
>
> 注意：
>
> 如果两个链表没有交点，返回 null.
> 在返回结果后，两个链表仍须保持原有的结构。
> 可假定整个链表结构中没有循环。
> 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
> 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 题解：

### 思路一: 暴力法

简单粗暴，思路简单不多做解释了。

代码如下: 

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA != null){

            ListNode nodeB = headB;
            while (nodeB != null){
                if (headA == nodeB) return nodeB;
                nodeB = nodeB.next;
            }

            headA = headA.next;
        }
        return null;
    }
```

复杂度分析: 

时间复杂度: O(m * n) 

空间复杂度: O(1) 没有用到额外的空间复杂度

![屏幕快照 2020-06-02 下午3.30.32](https://tva1.sinaimg.cn/large/007S8ZIlly1gfdzd81rk1j30o006kdgm.jpg)



### 思路二： Set存储

- 拿到这个题目，第一反应是， 遍历一个链表，用set存储，遍历过的节点
- 再次遍历第二个链表，遍历过程中，如果set中包含， 则为相交的第一个节点，直接返回即可
- 遍历到末尾，一直不包含时， 返回null.

代码如下:

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        HashSet<ListNode> set = new HashSet<>();
        while (headA != null){
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null){
            if (set.contains(headB)) return headB;
            headB = headA.next;
        }
        return null;
    }
```

复杂度分析:

时间复杂度 : O(m + n)

空间复杂度 : O(m)

![屏幕快照 2020-06-02 下午3.07.24](https://tva1.sinaimg.cn/large/007S8ZIlly1gfdz5ihyxpj30ns062gmc.jpg)



### 思路三： 双指针

**但是发现此解法一个问题**, 如果链表A特别长, 而相交节点，在很靠前的位置， 遍历了一整遍链表是不是有点耗时太长了？

能不能再做一些优化？

接下来，我们是这优化一下。

具体思路如下图所示：

![WechatIMG343](https://tva1.sinaimg.cn/large/007S8ZIlly1gfdzun5i5kj30u0199gqi.jpg)

代码如下:

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == headB) return headA;
        
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        
        while (nodeA != null || nodeB != null){
            
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
            
            if (nodeA == nodeB) return nodeA;
        }
        return null;
    }
```

![image-20200602155622127](https://tva1.sinaimg.cn/large/007S8ZIlly1gfe00wpbwqj30n206aaas.jpg)

复杂度分析 :

时间复杂度 : O(m + n)

空间复杂度 : O(1)



## 常用思路

链表题解中，总有一些非常神奇的思路, **求链表的中间节点，判断链表是否有环，找出环形链表入环的节点, 链表倒数第K个节点**等用到的**快慢指针**, 本题 **两个链表的第一个公共节点** 的双指针.

说实话，第一次接触这种题目，不容易想到这种思路, 总是会最先想到暴力法 和 set存储等非最优解。 看到最优解时，觉着非常神奇。 哇，原来这种方法轻松解决问题。

从暴力解法 直到最优解的探索过程中，体会着一些清奇的思路, 打开以后做题的思路。

这种感觉不错～