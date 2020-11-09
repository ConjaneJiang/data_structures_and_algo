package com.conjane.algo.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {

    private int size;

    private Node<E> rootNode;

    private Comparator<E> comparator;

    BinarySearchTree(Comparator comparator) {
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
                // 新值覆盖旧值
                tempNode.element = element;
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

    // 前序遍历：根 左 右
    public void preOrder(Node<E> rootNode) {
        System.out.println(rootNode.element);
        preOrder(rootNode.leftNode);
        preOrder(rootNode.rightNode);
    }

    // 带有遍历方式的前序遍历
    public void preOrder(Node<E> rootNode, Visitor visitor) {
        visitor.visit(rootNode.element);
        preOrder(rootNode.leftNode, visitor);
        preOrder(rootNode.rightNode, visitor);
    }

    // 中序遍历：左 根 右
    public void inOrder(Node<E> rootNode) {
        preOrder(rootNode.leftNode);
        System.out.println(rootNode.element);
        preOrder(rootNode.rightNode);
    }

    // 带有遍历方式的中序遍历
    public void inOrder(Node<E> rootNode,Visitor<E> visitor){
        preOrder(rootNode.leftNode,visitor);
        visitor.visit(rootNode.element);
        preOrder(rootNode.rightNode,visitor);
    }

    // 后序遍历：左 右 根
    public void postOrder(Node<E> rootNode) {
        preOrder(rootNode.leftNode);
        preOrder(rootNode.rightNode);
        System.out.println(rootNode.element);
    }

    // 带有遍历方式的后序遍历
    public void postOrder(Node<E> rootNode,Visitor<E> visitor){
        postOrder(rootNode.leftNode,visitor);
        postOrder(rootNode.rightNode,visitor);
        visitor.visit(rootNode.element);
    }

    // 层序遍历
    public void levelOrder(Node<E> rootNode) {
        if (rootNode == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll().element);
            if (rootNode.leftNode != null) {
                queue.offer(rootNode.leftNode);
            }
            if (rootNode.rightNode != null) {
                queue.offer(rootNode.rightNode);
            }
        }
    }

    // 检查是否为null
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    // 比较e1，e2两个值大小
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    static class Node<E> {
        E element;
        Node<E> leftNode;
        Node<E> rightNode;

        public Node(E element) {
            this.element = element;
        }
    }

    public interface Visitor<E> {
        void visit(E element);
    }
}
