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

    public void inOrder(Node<E> rootNode, Visitor<E> visitor) {
        preOrder(rootNode.leftNode, visitor);
        visitor.visit(rootNode.element);
        preOrder(rootNode.rightNode, visitor);
    }

    // 后序遍历：左 右 根
    public void postOrder(Node<E> rootNode) {
        preOrder(rootNode.leftNode);
        preOrder(rootNode.rightNode);
        System.out.println(rootNode.element);
    }

    // 带有遍历方式的后序遍历
    public void postOrder(Node<E> rootNode, Visitor<E> visitor) {
        postOrder(rootNode.leftNode, visitor);
        postOrder(rootNode.rightNode, visitor);
        visitor.visit(rootNode.element);
    }

    // 层序遍历
    public void levelOrder(Node<E> rootNode) {
        if (rootNode == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        // 首先根节点入队
        queue.offer(rootNode);
        // 循环检测队中是否存在元素
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.element);
            // 检查左节点是否存在，存在则入队
            if (node.leftNode != null) {
                queue.offer(node.leftNode);
            }
            // 检查右节点是否存在，存在则入队
            if (node.rightNode != null) {
                queue.offer(node.rightNode);
            }
        }
    }

    // 求二叉树高度
    public int height() {
        return height(this.rootNode);
    }

    // 递归方式求二叉树高度
//    private int height(Node node) {
//        if (node == null) return 0;
//        return Math.max(height(node.leftNode), height(node.rightNode));
//    }

    // 层序遍历方式求二叉树高度
    private int height(Node node){
        LinkedList<Node> queue = new LinkedList();
        queue.offer(node);
        int size = 1;
        int height = 0;
        while(!queue.isEmpty()){
            queue.poll();
            size = size - 1;
            if (node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if (node.rightNode != null){
                queue.offer(node.rightNode);
            }
            if (size == 0){
                size = queue.size();
                height = height + 1;
            }
        }
        return height;
    }

    // 是否是完全二叉树
    public boolean isComplete(){
        if (rootNode == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(rootNode);

        boolean mustBeLeaf = false;

        while (!queue.isEmpty()){
            Node node = queue.poll();
            // 节点必须是叶子结点，但是该节点又不是叶子节点，则不是完全二叉树
            if (mustBeLeaf && (node.rightNode != null || node.leftNode != null)){
                return false;
            }
            // 如果左节点，右节点不为空，则入队
            if (node.leftNode != null) {
                queue.offer(node.leftNode);
            }else if(node.rightNode != null){
                return false;
            }

            if (node.rightNode != null){
                queue.offer(rootNode.rightNode);
            }else {
                mustBeLeaf = false;
            }
        }
        return true;
    }

    // 翻转二叉树
    public Node reverse(){
        return reverse(rootNode);
    }

    private Node reverse(Node rootNode){
        if (rootNode == null) return rootNode;

        Node tempNode = rootNode.leftNode;
        rootNode.leftNode = rootNode.rightNode;
        rootNode.rightNode = tempNode;

        reverse(rootNode.leftNode);
        reverse(rootNode.rightNode);
        return rootNode;
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
