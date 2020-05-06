package com.fx.tree;

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    private Node<E> color(Node<E> node, boolean color){
        if (node == null) return null;
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    @Override
    protected Node createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    @Override
    protected void afterAdd(Node node) {
        Node parent = node.parent;

        if (parent == null){            // 根节点 或者 上溢到达了根节点
            black(node);
            return;
        }

        if (isBlack(parent)) return;    // 父节点是黑色 不用处理

        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) {  // 叔父节点是红色  上溢
            // 父节点 和 叔父节点热色成黑色
            black(parent);
            black(uncle);

            // 祖父节点染色成红色，重新添加
            afterAdd(red(grand));
            return;
        }

        // 父节点是红色， 叔父节点是黑色
        if (parent.isLeftChild()){
            if (node.isLeftChild()){
                // L L
                black(parent);
            }else{
                // L R
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else{
            if (node.isLeftChild()){
                // R L
                black(node);
                rotateRight(parent);
            }else{
                // R R]
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node node,Node replacement) {

        // 删除的是红色节点，直接删除，不用做任何操作
        if (isRed(node)) return;

        // 拥有一个red 子节点的black节点
        if (isRed(replacement)){
            black(replacement);
            return;
        }

        Node <E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是 black 叶子节点 下溢
        // 判断被删除的node是左还是右
		boolean left = parent.leftNode == null || node.isLeftChild();
		Node<E> sibling = left ? parent.rightNode : parent.leftNode;
		if (left) { // 被删除的节点在左边，兄弟节点在右边
			if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateLeft(parent);
				// 更换兄弟
				sibling = parent.rightNode;
			}

			// 兄弟节点必然是黑色
			if (isBlack(sibling.leftNode) && isBlack(sibling.rightNode)) {
				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色，兄弟要先旋转
				if (isBlack(sibling.rightNode)) {
					rotateRight(sibling);
					sibling = parent.rightNode;
				}

				color(sibling, colorOf(parent));
				black(sibling.rightNode);
				black(parent);
				rotateLeft(parent);
			}
		} else { // 被删除的节点在右边，兄弟节点在左边
			if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateRight(parent);
				// 更换兄弟
				sibling = parent.leftNode;
			}

			// 兄弟节点必然是黑色
			if (isBlack(sibling.leftNode) && isBlack(sibling.rightNode)) {
				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色，兄弟要先旋转
				if (isBlack(sibling.leftNode)) {
					rotateLeft(sibling);
					sibling = parent.leftNode;
				}

				color(sibling, colorOf(parent));
				black(sibling.leftNode);
				black(parent);
				rotateRight(parent);
			}
		}
    }

    private static class RBNode<E> extends Node<E>{
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}
