
/*
* 增加虚拟头节点的链表,统一后边所有节点的处理逻辑
* */

package com.fx.single;

import com.fx.AbstractList;

public class SingleLinkList2<E> extends AbstractList<E> {

    private Node<E> first;

    public SingleLinkList2(){
        first = new Node<>(null,null);
    }

    /*
    * 清空链表
    * */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    /*
    * 获取index位置的元素
    * */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /*
    * 设置index位置的元素
    * */
    @Override
    public E set(int index, E element) {

        Node<E> node = node(index);     // 当前节点
        E oldE = node.element;    // 当前节点元素
        node.element = element;

        return oldE;
    }

    /*
    * 在index位置添加元素
    * */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node <E> perV = index == 0 ? first : node(index-1); // 前一个节点
        Node <E> newNode = new Node<>(element,perV.next); // 当前节点
        perV.next = newNode;

        size++;
    }

    /*
    * 移除index位置的元素
    * */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> preV = index == 0 ? first : node(index - 1);
        Node<E> node = preV.next;
        preV.next = preV.next.next;

        size --;
        return node.element;
    }

    /*
    * 获取某元素的索引
    * */
    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (node.element == null){
                    return i;
                }
                node = node.next;
            }
        }else{
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)){
                    return i;
                }
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index){
        rangeCheck(index);

        Node<E> node = first.next;
        while(index > 0){
            index --;
            node = node.next;
        }

        return node;
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(",[");
        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            if (i!=0){
                string.append(",");
            }

            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

    /*
    * 内部私有类
    * */
    public static class Node<E>{
        E element;
        Node <E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }
}
