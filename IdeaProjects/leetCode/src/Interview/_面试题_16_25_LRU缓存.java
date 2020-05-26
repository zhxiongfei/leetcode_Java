package Interview;

/*
设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。

        它应该支持以下操作： 获取数据 get 和 写入数据 put 。

        获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
        写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

        示例:

        LRUCache cache = new LRUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/lru-cache-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import javax.sound.sampled.DataLine;
import java.util.HashMap;
import java.util.LinkedList;

public class _面试题_16_25_LRU缓存 {

    // 双向链表节点
    class DLinkListNode{
        int key;
        int value;
        DLinkListNode prev;
        DLinkListNode next;

        public DLinkListNode() {}

        public DLinkListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer,DLinkListNode> map;
    LinkedList<DLinkListNode> list;

    DLinkListNode head;
    DLinkListNode tail;
    int size;
    int capacity;
    public _面试题_16_25_LRU缓存(int capacity) {

        this.capacity = capacity;
        this.size = 0;

        map = new HashMap<>();
        list = new LinkedList<>();

        head = new DLinkListNode();
        tail = new DLinkListNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        DLinkListNode node = map.get(key);

        // 并将 node 移动到 头部
        moveNodeToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            // map中包含
            DLinkListNode node = map.get(key);
            node.value = value;

            map.put(key,node);
            moveNodeToHead(node);
        }else {
            // map 中不包含
            DLinkListNode node = new DLinkListNode(key,value);
            map.put(key,node);

            addToHead(node);
            size ++;

            if (size > capacity){
                removeTail();
                size --;
            }
        }
    }

    // 将节点移动到头部
    public void moveNodeToHead(DLinkListNode node){

        node.prev.next = node.next;
        node.next.prev = node.prev.prev;

        head.next.prev = node;
        node.next = head.next;

        head.next = node;
        node.prev = head;
    }

    // 将节点添加到头部
    public void addToHead(DLinkListNode node){

        head.next.prev = node;
        node.next = head.next;

        head.next = node;
        node.prev = head;
    }

    // 移除尾部元素
    public void removeTail(){

        map.remove(tail.prev.key);

        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }
}
