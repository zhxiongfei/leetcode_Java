package com.fx;

import com.fx.tree.BinaryTree;
import com.fx.tree.RBTree;

import java.util.Comparator;

public class TreeSet<E> implements Set<E> {
    private RBTree<E> tree;

    public TreeSet() {
        this(null);
    }

    public TreeSet(Comparator<E> comprator) {
        tree = new RBTree<>(comprator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {

    }
}
