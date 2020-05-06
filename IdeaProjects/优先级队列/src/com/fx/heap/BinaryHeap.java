package com.fx.heap;

import java.util.Comparator;

// 二叉堆 完全二叉堆 底层结构一般使用数组 最大堆
public class BinaryHeap<E> extends AbstractHeap<E> implements Heap<E> {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[])new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(Comparator<E> comparator, E[] elements) {
        super(comparator);
        if (elements == null || elements.length == 0){
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }else{
            size = elements.length;
            int capacity = Math.max(elements.length,DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }

            heapify();
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);

        elements[size++] = element;
        siftUp(size-1);
    }

    private void heapify(){
        // 自上而下的上滤
//        for (int i = 1; i < size; i++) {
//            siftUp(i);
//        }

        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    // 上滤
    private void siftUp(int index){

        E element = elements[index];
        while (index > 0){
            int pIdx = (index - 1) >> 1;
            E parE = elements[pIdx];

            if (compare(element,parE) >= 0) break;
            elements[index] = parE;

            index = pIdx;
        }

        elements[index] = element;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        E root = elements[0];
        int lastIdx = --size;
        elements[0] = elements[lastIdx];
        elements[lastIdx] = null;

        siftDown(0);
        return root;
    }

    // 下滤
    private void siftDown(int index){
        E element = elements[index];

        // 非叶子节点 子节点比较 交换
        // index < 第一个叶子结点的索引
        // index < 非叶子结点的数量
        int half = size >> 1;
        while (index < half){
            // index的结点有两种情况
            // 只有左子节点
            // 左右子节点都有

            // 默认取出左子节点的值跟element比较
            int childIndex = index << 1 + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;
            if (rightIndex < size && compare(elements[rightIndex],child) > 0){
                child = elements[childIndex = rightIndex];
            }

            // 子节点小
            if (compare(element,child) >= 0) break;

            // 子结点大 交换
            elements[index] = child;


            index = childIndex;
        }

        elements[index] = element;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0){
            elements[0] = element;
            size ++;
        }else{
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void emptyCheck(){
        if (size == 0){
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }
}
