package com.fx.tree;

import com.fx.Set;

import javax.print.attribute.standard.NumberUp;
import java.util.TreeSet;

public class BinaryTree<E> {

    protected int size;
    protected Node<E> root;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    // 前驱节点
    protected Node<E> predecessor(Node<E> node){
        if (node == null) return null;

        // 前驱结点在左子树当中
        // node左子树不为空
        // node.left.right.right.right... 直到 null
        Node<E> p = node.leftNode;
        if (p != null){
            while (p.rightNode != null){
                p = p.rightNode;
            }
            return p;
        }

        // 左子树为空 parent ！= null
        // 找parent 直到是parent的右子树为止
        while (node.parent != null && node == node.parent.leftNode){
            node = node.parent;
        }

        // node.parent == null
        // 或者
        // node == node.parent.right
        return node.parent;
    }

    protected Node<E> successor(Node<E> node){
        if (node == null) return null;

        // 前驱结点在右子树当中
        // node右子树不为空
        // node.right.left.left.left... 直到 null
        Node<E> p = node.rightNode;
        if (p != null){
            while (p.leftNode != null){
                p = p.leftNode;
            }
            return p;
        }

        // 从父节点 祖父节点中寻找前驱结点
        // 右子树为空 parent ！= null
        // 找parent 直到是parent的左子树为止
        while (node.parent != null && node == node.parent.rightNode){
            node = node.parent;
        }

        // node.parent == null
        // 或者
        // node == node.parent.left
        return node.parent;
    }

    public int height(){
        int h = 0;



        return h;
    }

    protected static class Node<E> {
        E element;
        Node<E> leftNode;   // 左子节点
        Node<E> rightNode;  // 右子节点
        Node<E> parent;     // 父节点

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf(){
            return leftNode == null && rightNode == null;
        }

        public boolean hasTwoChildren(){
            return leftNode != null && rightNode != null;
        }

        public boolean isLeftChild(){
            return parent != null && this == parent.leftNode;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.rightNode;
        }

        public Node<E> sibling(){
            if (isLeftChild()){
                return parent.rightNode;
            }
            if (isRightChild()){
                return parent.leftNode;
            }
            return null;
        }
    }
}
