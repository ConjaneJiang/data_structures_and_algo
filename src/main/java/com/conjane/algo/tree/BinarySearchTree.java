package com.conjane.algo.tree;

import java.util.Comparator;

public class BinarySearchTree<E> {

    private int size;

    private Node<E> rootNode;

    private Comparator<E> comparator;

    BinarySearchTree(Comparator comparator){
        this.comparator = comparator;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (rootNode == null) {
            rootNode = new Node<>(element);
        }
        Node parentNode = null;
        Node tempNode = rootNode;
        int compareResult = 0;
        while (tempNode != null) {
            compareResult = compare(element, rootNode.element);
            parentNode = tempNode;
            if (compareResult > 0) {
                tempNode = tempNode.rightNode;
            } else if (compareResult < 0) {
                tempNode = tempNode.leftNode;
            } else {
                return;
            }
        }
        Node node = new Node(element);
        if (compareResult > 0) {
            parentNode.rightNode = node;
        }
        if (compareResult < 0) {
            parentNode.leftNode = node;
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    // 比较e1，e2两个值大小
    private int compare(E e1, E e2) {
        if (comparator != null){
            return comparator.compare(e1,e2);
        }
        return ((Comparable)e1).compareTo(e2);
    }

    static class Node<E> {
        E element;
        Node<E> leftNode;
        Node<E> rightNode;

        public Node(E element) {
            this.element = element;
        }
    }
}
