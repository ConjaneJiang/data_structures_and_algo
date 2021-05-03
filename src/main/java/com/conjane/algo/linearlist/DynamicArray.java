package com.conjane.algo.linearlist;

import java.util.Arrays;

/**
 * 对比java.utils.ArrayList
 */
public class DynamicArray<E> {

    // 默认的数组容量
    private final static Integer DEFAULT_CAPACITY = 10;
    // 空数组对象
    private final static Object[] EMPTY_ELEMENTS = {};
    // 默认的空数组对象
    private final static Object[] DEFAULT_EMPTY_ELEMENTS = {};
    // 数组对象
    private Object[] elements;
    // 长度
    private int size;

    public DynamicArray(){
        this.elements = DEFAULT_EMPTY_ELEMENTS;
    }

    public DynamicArray(int initialCapacity){
        if (initialCapacity == 0){
            this.elements = EMPTY_ELEMENTS;
        }else if (initialCapacity > 0){
            this.elements = new Object[initialCapacity];
        }else {
            throw new IllegalArgumentException("initialCapacity must greater than 0");
        }
    }

    /**
     * 尾部添加元素
     * @param element
     */
    public void add(E element){
        add(size,element);
    }

    /**
     * 指定索引添加
     * @param index
     * @param element
     */
    public void add(int index,E element){
        ensureCapacity(size + 1);
        for (int i = size;i > index;i--){
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    public int size(){
        return this.size;
    }

    private void ensureCapacity(int capacity){
        int minCapacity = calculateCapacity(elements,capacity);
        if (minCapacity > elements.length){
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0){
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements,newCapacity);
        }

    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULT_EMPTY_ELEMENTS) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * 获取元素
     * @param index
     * @return
     */
    public E get(int index){
        rangeCheck(index);
        return (E)elements[index];
    }

    /**
     * 移除元素
     * @param index
     * @return
     */
    public E remove(int index){
        rangeCheck(index);
        E oldElement = (E) elements[index];
        for (int i = index;i < size;i++){
            elements[i] = elements[i + 1];
        }
        size--;
        return oldElement;
    }

    public boolean remove(E element){
        /**
         * 该数组可以存储null值，所以这边需要判断下是否移除的为null值
         */
        if (element == null){
            for (int i = 0;i < size;i++){
                if (null == elements[i]){
                    remove(i);
                    return true;
                }
            }
        }else{
            for (int i = 0;i < size;i++){
                /**
                 * 这里需要重写类的equals方法
                 */
                if (element.equals(elements[i])){
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 元素越界检查
     * @param index
     */
    private void rangeCheck(int index){
        /**
         * 如果获取元素的索引超出容量size大小，则抛出异常
         */
        if (index >= size){
            throw new IndexOutOfBoundsException("index = " + index + "size = " + size);
        }
    }

    /**
     * 打印字符串
     * @return
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < size;i++){
            builder.append(elements[i]).append(",");
        }
        String str = builder.toString();
        return str.substring(0,str.length() - 1);
    }
}
