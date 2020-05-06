
// 双端队列 double ended queue
package com.fx;

public class Deque<E>{

    private List<E> list = new LinkList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void  clear(){
        list.clear();
    }

    // 尾部入队
    public void enQueueRear(E element){
        list.add(element);
    }

    // 尾部出队
    public E dequeueRear(){
        return list.remove(list.size() - 1);
    }

    // 头部入队
    public void enQueueFront(E elemnet){
        list.add(0,elemnet);
    }

    // 头部出队
    public E deQueueFront(){
        return list.remove(0);
    }

    // 头元素
    public E front(){
        return list.get(0);
    }

    // 尾元素
    public E rear(){
        return list.get(list.size() - 1);
    }
}
