package com.fx;

public class ArrayList<E> extends AbstractList<E> {

    /*
     * 所有元素
     * */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

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

    /*
    * 获取index位置的元素
    * */
    public E get(int index){
        rangeCheck(index);

        return elements[index];
    }

    /*
    * 在index位置插入一个元素
    * */
    public void add(int index, E element){
        rangeCheckForAdd(index);
        ensureCapatity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    /*
    * 设置index位置的元素
    * */
    public E set(int index,E element){
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /*
    * 删除index位置的元素
    * */
    public E remove(int index){
        rangeCheck(index);

        E tmp = elements[index];
        for (int i = index+1; i < size; i++) {
            elements[i-1] = elements[i];
        }

        elements[--size] = null;
        return tmp;
    }

    /*
    * 查看元素的索引
    * */
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
