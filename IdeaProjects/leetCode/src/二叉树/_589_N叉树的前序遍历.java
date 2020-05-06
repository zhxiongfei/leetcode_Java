package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class _589_N叉树的前序遍历 {

    ArrayList list = new ArrayList();
    public List<Integer> preorderRecursive(Node root) {

        if (root == null) return list;

        Node node = root;
        list.add(root.val);

        for (int i = 0; i < node.children.size(); i++) {
            Node n = node.children.get(i);
            preorderRecursive(n);
        }
        return list;
    }

    public List<Integer> preorder(Node root) {

        if (root == null) return list;

        return list;
    }
}
