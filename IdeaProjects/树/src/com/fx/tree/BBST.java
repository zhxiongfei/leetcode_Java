package com.fx.tree;
import java.util.Comparator;

public class BBST <E> extends BST <E>{

    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateLeft(Node<E> grand){
        Node parent = grand.rightNode;
        Node child = parent.leftNode;

        grand.rightNode = child;
        parent.leftNode = grand;

        // 更改指向
        afterRotate(grand,parent,child);
    }

    protected void rotateRight(Node<E> grand){
        Node parent = grand.leftNode;
        Node child = parent.rightNode;

        grand.leftNode = child;
        parent.rightNode = grand;

        // 更改指向
        afterRotate(grand,parent,child);
    }

    // 旋转后更改parent指针指向
    protected void afterRotate(Node grand,Node parent,Node child){

        parent.parent = grand.parent;
        // 更改parent父节点
        if (grand.isLeftChild()){
            grand.parent.leftNode = parent;
        }else if (grand.isRightChild()){
            grand.parent.rightNode = parent;
        }else{
            root = parent;
        }

        if (child != null){
            child.parent = grand;
        }

        grand.parent = parent;
    }
}
