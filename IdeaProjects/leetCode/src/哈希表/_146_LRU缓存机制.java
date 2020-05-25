package 哈希表;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/*
* 题目主要使用，哈希表，双向链表
* 使用虚拟头节点，虚拟尾节点
* 虚拟头节点指向的元素是最近使用的元素
* 虚拟尾节点指向的元素是最不常使用的元素
*
* put 时
*   如果 map 中包含， 则取出，value重新赋值， map重新赋值， 并将此节点移至头部
*   如果 map 中不包含，则重新创建, 加入头部
*       加入后，如果map的 size > capacity， 则尾部元素删除
*
* get 时
*   如果 map 中不包含，直接return -1
*   如果 map 中包含， 则取出节点，并将此节点移动至头部
* */
public class _146_LRU缓存机制 {

    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {};

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /*
    *
    * 关键字 : key, value对
    * 模式识别 : 一旦出现 key , value 就要想到哈希表
    * 改变数据的访问时间
    * */
    HashMap<Integer,DLinkedNode> map;
    LinkedList list;
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public _146_LRU缓存机制(int capacity) {
        map = new HashMap<>();
        list = new LinkedList();

        this.capacity = capacity;
        this.size = 0;

        // 虚拟头节点
        // 虚拟头节点指向最近使用元素
        // 虚拟尾节点指向最近未使用元素
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        DLinkedNode node = map.get(key);
        // 将node 移动 到 head指向的元素
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)){
            // 包含 元素
            DLinkedNode node = map.get(key);
            node.value = value;
            map.put(key,node);
            // 移动node到 head指向的元素
            moveToHead(node);
            return;
        }

        // 不包含
        // 字典中保存键值对
        // 新节点 加入队尾
        DLinkedNode newNode = new DLinkedNode(key,value);
        map.put(key, newNode);
        addToHead(newNode);
        size ++;

        if (size > capacity){
            // size 已经 超过 最大容量
            // 删除 tail 指向的元素
            removeLastNode();
            size --;
        }
    }

    // 移动元素 至 队头
    private void moveToHead(DLinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    // 删除末尾元素
    private void removeLastNode(){

        map.remove(tail.prev.key);

        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }

    private void addToHead(DLinkedNode node){
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    public static void main(String[] args) {
        _146_LRU缓存机制 cls = new _146_LRU缓存机制(2);
        cls.put(2,1);
        cls.put(2,2);
        int v1 = cls.get(2);
        cls.put(1,1);
        cls.put(4,1);
        int v2 = cls.get(2);

        System.out.println(cls);
    }
}
