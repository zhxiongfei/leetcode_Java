package com.fx.circle;

import com.fx.AbstractList;

public class CircleLinkList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    public void reset(){
        current = first;
    }

    public void next(){
        if (current == null) return;

        current = current.next;
    }

    // remove cuerent节点
    public E remove(){
        if (current == null) return null;

        Node<E> node = current.next;
        E element = remove(current);
        if (size == 0){
            current = null;
        }else{
            current = node;
        }

        return  element;
    }

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

        if (index == size){         // 往最后面添加元素
            Node<E> prev = last;
            last = new Node<>(prev,element,first);
            if (prev == null){      // 链表的第一个元素
                first = last;
                first.prev = first;
                first.next = first;
            }else{
                prev.next = last;
                first.prev = last;
            }
        }else{
            Node<E> next = node(index);
            Node<E> prev = next.prev;

            Node<E> newNode = new Node<>(prev,element,next);
            next.prev = newNode;
            if (prev == last){      // index == 0  插入第一个元素
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
        return remove(node);
    }

    // 1 2 3 4 5 6
    private E remove(Node<E> node){
        if (size == 1){
            first = null;
            last = null;
        }else{
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (prev == last){ // 删除的是第一个
                first = next;
            }

            if (next == first){ // 删除的是最后一个
                last = prev;
            }
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
