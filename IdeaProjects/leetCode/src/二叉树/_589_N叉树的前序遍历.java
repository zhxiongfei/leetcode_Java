package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){

            Node node = stack.pop();
            list.add(node.val);

            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }

        return list;
    }
}
