package com.fx;

public class ArrayList<E> {

    /*
     * 元素数量
     * */
    private int size;

    /*
     *
     * 存放元素
     * */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity){
        if (capacity < DEFAULT_CAPACITY) capacity = DEFAULT_CAPACITY;

        elements = (E[]) new Object[capacity];
    }

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    /*
    * 清除所有的元素
    * */
    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public E get(int index){
        rangeCheck(index);

        return elements[index];
    }

    public void add(E element) {
        add(size,element);
    }

    public void add(int index, E element){
        rangeCheckForAdd(index);
        ensureCapatity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    public E set(int index,E element){
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    public E remove(int index){
        rangeCheck(index);

        E tmp = elements[index];
        for (int i = index+1; i < size; i++) {
            elements[i-1] = elements[i];
        }

        elements[--size] = null;
        return tmp;
    }

    public int indexOf(E element){
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        }else{
            for (int i = 0; i < size; i++) {
                if (elements[i] == element) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /*
    * 扩容
    * */
    private void ensureCapatity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);   // 新的容量是旧的容量的1.5倍
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    private void outOfBounds(int index){
        throw new IndexOutOfBoundsException("index:"+index+"size:"+size);
    }

    private void rangeCheck(int index){
        if (index < 0 || index >= size){
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index){
        if (index < 0 || index > size){
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");

        for (int i = 0; i < size; i++) {
            if (i != 0) builder.append(",");
            builder.append(elements[i]);
        }

        builder.append("]");
        return builder.toString();
    }
}
