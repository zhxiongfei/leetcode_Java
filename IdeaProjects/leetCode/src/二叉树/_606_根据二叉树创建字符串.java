package 二叉树;

/*
你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。

        空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。

        示例 1:

        输入: 二叉树: [1,2,3,4]
        1
        /   \
        2     3
        /
        4

        输出: "1(2(4))(3)"

        解释: 原本将是“1(2(4)())(3())”，
        在你省略所有不必要的空括号对之后，
        它将是“1(2(4))(3)”。
        示例 2:

        输入: 二叉树: [1,2,3,null,4]
        1
        /   \
        2     3
        \
        4

        输出: "1(2()(4))(3)"

        解释: 和第一个示例相似，
        除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/construct-string-from-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class _606_根据二叉树创建字符串 {

    /**
     *
     * 递归
     *
     * */
    public static String tree2str(TreeNode t) {
        if (t == null) return "";
        if (t.left == null && t.right == null) return t.val + "";
        if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";
        return t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";
    }


    /**
     *
     * 迭代
     *
     * */
    public static String tree2str1(TreeNode t) {
        if (t == null) return "";

        StringBuilder sb = new StringBuilder();
        Stack<TreeNode>stack = new Stack<>();
        Set<TreeNode>set = new HashSet<>();
        stack.push(t);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();

            if (set.contains(node)){
                sb.append(")");
                stack.pop();
            }else {
                set.add(node);
                sb.append("(" + node.val);

                if (node.left == null && node.right != null) sb.append("()");

                if (node.right != null){
                    stack.push(node.right);
                }
                if (node.left != null){
                    stack.push(node.left);
                }
            }
        }
        return sb.toString().substring(1,sb.length() - 1);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.right = new TreeNode(4);

        String s = tree2str1(node);
        System.out.println(s);
    }
}
