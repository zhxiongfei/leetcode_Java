package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class _590_N叉树的后序遍历 {

    ArrayList list = new ArrayList();
    public List<Integer> postorder(Node root) {

        if (root == null) return list;

        Node node = root;
        for (int i = 0; i < node.children.size(); i++) {
            Node n = node.children.get(i);
            postorder(n);
        }
        list.add(root.val);

        return list;
    }
}
