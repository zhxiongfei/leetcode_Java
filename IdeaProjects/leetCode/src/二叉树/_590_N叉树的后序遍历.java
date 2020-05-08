package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _590_N叉树的后序遍历 {

    // 递归
    ArrayList list = new ArrayList();
    public List<Integer> postorderRecursive(Node root) {

        if (root == null) return list;

        Node node = root;
        for (int i = 0; i < node.children.size(); i++) {
            Node n = node.children.get(i);
            postorderRecursive(n);
        }
        list.add(root.val);

        return list;
    }

    // 迭代
    public List<Integer> postorder(Node root){
        ArrayList list = new ArrayList();
        if (root == null) return list;

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node pre = null;

        while (!stack.isEmpty()){
            Node node = stack.peek();

            if (node.children.size() == 0 || node.children.contains(pre)){
                // 如果是叶子节点 或者 上一次访问的节点是此节点的子节点时  出栈访问
                list.add(node.val);

                pre = node;
                stack.pop();
            }else {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }

        return list;
    }
}
