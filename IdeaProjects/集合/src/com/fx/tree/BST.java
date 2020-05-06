package com.fx.tree;
import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BST() {
        this(null);
    }
    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element){
        elementNotNullCheck(element);

        if (root == null){
            root = createNode(element,null);
            size++;

            // 新添加节点之后的处理
            afterAdd(root);
            return;
        }

        // 添加的不是第一个节点
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        do{
            cmp = compare(element,node.element);
            parent = node;
            if (cmp > 0){   // 大于
                node = node.rightNode;
            }else if (cmp < 0){ // 小于
                node = node.leftNode;
            }else{  // 相等
                node.element = element;
                return;
            }
        }while (node != null);

        // 插入父节点的对应位置
        Node<E> newNode = createNode(element,parent);
        if (cmp > 0){
            parent.rightNode = newNode;
        }else{
            parent.leftNode = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterAdd(newNode);
    }

    /*
    * 添加node后，调整平衡时覆写
    * */
    protected void afterAdd(Node nodex){ }

    /*
     * 移除node后，调整平衡时覆写
     * */
    protected void afterRemove(Node node,Node replacement){ }

    protected Node createNode(E element,Node<E> parent){
        return new Node<>(element,parent);
    }

    public void remove(E element){
        remove(node(element));
    }

    public boolean contains(E element){
        return node(element) != null;
    }

    private void remove(Node<E> node){
        if (node == null) return;
        size --;
        if (node.hasTwoChildren()){
            // 度为2的节点
            // 找到前驱或者后继节点
            Node<E> s = successor(node);
            // 用前驱或者后继节点的值覆盖度为2的节点
            node.element = s.element;
            // 删除前驱或者后继
            node = s;
        }

        // 删除度为0 或者 度为1 的节点
        Node<E> replacement = node.leftNode != null ? node.leftNode : node.rightNode;
        if (replacement != null){
            // 度为1
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left right指向
            if (node.parent == null){       // node是度为1 的节点，并且是根节点
                root = replacement;
            }else if (node.parent.leftNode == node){
                node.parent.leftNode = replacement;
            }else{
                node.parent.rightNode = replacement;
            }
            afterRemove(node,replacement);

        }else if (node.parent == null){    // node是叶子节点 且 是根节点
            root = null;
            afterRemove(node,null);

        }else {
            // 度为0 叶子节点
            if (node == node.parent.leftNode){
                node.parent.leftNode = null;
            }else{
                node.parent.rightNode = null;
            }
            afterRemove(node,null);
        }
    }

    private Node<E> node(E element){
        Node <E>node = root;
        while (node != null){
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0){
                node = node.rightNode;
            }else {
                node = node.leftNode;
            }
        }
        return null;
    }

    /*
    * 返回值等于0表示相等。  大于0代表e1>e2.  小于0代表e1<e2
    * */
    private int compare(E e1,E e2){
        if (comparator != null){
            comparator.compare(e1,e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element){
        if (element == null){
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
