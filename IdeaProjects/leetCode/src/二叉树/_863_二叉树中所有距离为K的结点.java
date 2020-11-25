package 二叉树;

import java.util.*;

/**
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。

        返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。

        示例 1：

        输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
        输出：[7,4,1]
        解释：
        所求结点为与目标结点（值为 5）距离为 2 的结点，
        值分别为 7，4，以及 1



        注意，输入的 "root" 和 "target" 实际上是树上的结点。
        上面的输入仅仅是对这些对象进行了序列化描述。
         

        提示：

        给定的树是非空的。
        树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
        目标结点 target 是树上的结点。
        0 <= K <= 1000.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _863_二叉树中所有距离为K的结点 {

    /**
     * DFS
     * */
    Map<TreeNode,TreeNode>parentDic = new HashMap<>();
    public void buildMap(TreeNode node, TreeNode parent){
        if (node == null) return;
        parentDic.put(node, parent);
        buildMap(node.left, node);
        buildMap(node.right, node);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if (root == null || target == null) return res;
        buildMap(root, null);

        boolean[] visited = new boolean[501];
        // 递归获取距离指定节点指定距离的所有结点值
        search(target, K, res, visited);

        return res;
    }

    private void search(TreeNode target, int K , List<Integer>res, boolean[]visited){
        if (target == null || K < 0 || visited[target.val]) return;
        if (K == 0){
            res.add(target.val);
            visited[target.val] = true;
            return;
        }
        visited[target.val] = true;
        search(target.left, K - 1, res, visited);
        search(target.right, K - 1, res, visited);
        search(parentDic.get(target), K - 1, res, visited);
    }

    public static void main(String[] args) {
        _863_二叉树中所有距离为K的结点 cls = new _863_二叉树中所有距离为K的结点();
        // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        cls.distanceK(root,root.left,2);
    }

}
