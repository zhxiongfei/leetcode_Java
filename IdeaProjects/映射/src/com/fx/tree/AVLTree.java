package com.fx.tree;

import java.util.Comparator;

// 在二叉搜索树的基础上增加自平衡功能
public class AVLTree<E> extends BBST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node node) {
        // Node n = node.parent.parent... 直到找到失衡节点
        while ((node = node.parent) != null){
            if (isBalance(node)){
                // 平衡 更新高度
                updateHeight(node);
            }else{
                // 不平衡 恢复平衡
                // 高度最低的不平衡节点 恢复平衡
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRotate(Node grand, Node parent, Node child) {
        super.afterRotate(grand,parent,child);
        updateHeight(grand);
        updateHeight(parent);
    }

    // 删除的时候只会影响父节点的平衡
    @Override
    protected void afterRemove(Node node,Node replacement) {
        while ((node = node.parent) != null){
            if (isBalance(node)){
                updateHeight(node);
            }else{
                // 有可能导致所有的祖先节点失衡，所以不用break
                rebalance(node);
            }
        }
    }

    private boolean isBalance(Node<E> node){

        return  Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    private void rebalance(Node<E> grand){
        // 高度最低的不平衡节点
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()){
            if (node.isLeftChild()){
                // L L  grand右旋
                rotateRight(grand);
            }else{
                // L R  parent左旋 grand右旋
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{
            if (node.isLeftChild()){
                // R L  parent右旋 grand左旋
                rotateRight(parent);
                rotateLeft(grand);
            }else{
                // R R  grand左旋
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected Node createNode(E element, Node<E> parent) {
        return new AVLNode(element,parent);
    }

    private class AVLNode<E> extends Node<E>{

        int height = 1;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        int balanceFactor(){
            int leftHeight = leftNode == null ? 0 : ((AVLNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode)rightNode).height;

            return leftHeight - rightHeight;
        }

        public void updateHeight(){

            int leftHeight = leftNode == null ? 0 : ((AVLNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>)rightNode).height;

            height = Math.max(leftHeight,rightHeight) + 1;
        }

        public Node<E> tallerChild(){

            int leftHeight = leftNode == null ? 0 : ((AVLNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>)rightNode).height;

            if (leftHeight > rightHeight) return leftNode;
            if (leftHeight < rightHeight) return rightNode;
            return isLeftChild() ? leftNode : rightNode;
        }
    }
}
