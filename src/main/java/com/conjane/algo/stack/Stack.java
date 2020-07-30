package com.conjane.algo.stack;

import java.util.Arrays;

public class Stack<E> {
    // 栈元素数组
    private Object[] elementData;
    // 栈元素个数
    private int elementCount;

    public Stack(){
        this(10);
    }

    /**
     * 初始化容量 10
     * @param initialCapacity
     */
    public Stack(int initialCapacity){
        this.elementData = new Object[initialCapacity];
    }

    /**
     * 添加元素
     * @param element
     */
    public void push(E element){
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = element;
    }

    private void ensureCapacityHelper(int capacity) {
        if (capacity - elementData.length > 0){
            grow(capacity);
        }
    }

    private void grow(int capacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        elementData = Arrays.copyOf(elementData,newCapacity);
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public E peek(){
        return (E)elementData[elementCount - 1];
    }

    /**
     * 弹出栈顶元素
     * @return
     */
    public E pop(){
        E element = (E)elementData[elementCount - 1];
        elementCount--;
        return element;
    }
}
