package com.fx;

import com.fx.AbstractList;

public class LinkList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    /*
     * 内部私有类
     * */
    private static class Node<E>{
        E element;
        Node <E> prev;  // 前一个元素
        Node <E> next;  // 下一个元素

        public Node(Node<E> prev,E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (prev != null){
                sb.append(prev.element);
            }else {
                sb.append("null");
            }
            sb.append("_").append(element).append("_");
            if (next != null){
                sb.append(next.element);
            }else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

    /*
     * 清空链表
     * */
    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
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

        if (index == size){
            Node<E> prev = last;
            last  = new Node<>(prev,element,null);
            if (prev == null){      // 链表的第一个元素
                first = last;
            }else{
                prev.next = last;
            }
        }else{
            Node<E> next = node(index);
            Node<E> prev = next.prev;

            Node<E> newNode = new Node<>(prev,element,next);
            next.prev = newNode;
            if (prev == null){      // index == 0
                first = newNode;
            }else {
                prev.next = newNode;
            }
        }

        size++;
    }

    /*
     * 移除index位置的元素
     * */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null){ // 删除的是第一个
            first = next;
        }else{
            prev.next = next;
        }

        if (next == null){ // 删除的是最后一个
            last = prev;
        }else{
            next.prev = prev;
        }

        size --;
        return node.element;
    }

    /*
     * 获取某元素的索引
     * */
    @Override
    public int indexOf(E element) {
        if (element == null){
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null){
                    return i;
                }
                node = node.next;
            }
        }else{
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)){
                    return i;
                }
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对应的节点对象
     * @param index
     * @return
     */
    private Node<E> node(int index){
        rangeCheck(index);

        if (index < (size>>1)){
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else{
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(",[");
        Node<E> node = first;
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
}
