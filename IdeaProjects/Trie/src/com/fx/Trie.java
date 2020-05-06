package com.fx;

import java.util.HashMap;

public class Trie<V> {

    private int size;
    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        size = 0;
        root = null;
    }

    public V get(String key){

        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }

    boolean contains(String key){
        Node<V> node = node(key);
        return node != null && node.word;
    }

    V add(String key, V value){
        keyCheck(key);

        if (root == null){
            root = new Node<>(null);
        }

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            boolean emptyChildren = node.children == null;
            Node<V> childNode = emptyChildren ? null : node.children.get(c);
            if (childNode == null){
                childNode = new Node<>(node);
                childNode.character = c;
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c,childNode);
            }
            node = childNode;
        }

        if (node.word) { // 已经存在这个单词
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        // 新增一个单词
        node.word = true;
        node.value = value;
        size++;
        return null;
    }

    V remove(String key){
        // 找到最后一个节点
        Node<V> node = node(key);
        // 如果不是单词结尾，不用作任何处理
        if (node == null || !node.word) return null;
        size--;
        V oldValue = node.value;

        // 如果还有子节点
        if (node.children != null && !node.children.isEmpty()) {
            node.word = false;
            node.value = null;
            return oldValue;
        }

        // 如果没有子节点
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            parent.children.remove(node.character);
            if (parent.word || !parent.children.isEmpty()) break;
            node = parent;
        }

        return oldValue;
    }

    boolean startsWith(String prefix){
        return node(prefix) != null;
    }

    private Node<V> node(String key){
        keyCheck(key);

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty()) return null;
            char c = key.charAt(i);
            node = node.children.get(c);
        }

        return node;
    }

    private void keyCheck(String key){
        if (key == null || key.length() == 0){
            throw new IllegalArgumentException("Key must not be empty");
        }
    }

    private static class Node<V>{
        Node<V> parent;
        HashMap<Character,Node<V>> children;
        Character character;
        V value;
        boolean word;       // 是否为单词结尾（是否为一个完整的单词）

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }

    static void test1() {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("小码哥", 5);
        Asserts.test(trie.size() == 5);
        Asserts.test(trie.startsWith("do"));
        Asserts.test(trie.startsWith("c"));
        Asserts.test(trie.startsWith("ca"));
        Asserts.test(trie.startsWith("cat"));
        Asserts.test(trie.startsWith("cata"));
        Asserts.test(!trie.startsWith("hehe"));
        Asserts.test(trie.get("小码哥") == 5);
        Asserts.test(trie.remove("cat") == 1);
        Asserts.test(trie.remove("catalog") == 3);
        Asserts.test(trie.remove("cast") == 4);
        Asserts.test(trie.size() == 2);
        Asserts.test(trie.startsWith("小"));
        Asserts.test(trie.startsWith("do"));
        Asserts.test(!trie.startsWith("c"));
    }

    public static void main(String[] args){
        test1();
    }
}
