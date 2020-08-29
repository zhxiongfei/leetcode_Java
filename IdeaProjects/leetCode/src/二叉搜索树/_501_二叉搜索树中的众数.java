package 二叉搜索树;

import java.util.*;
import java.util.stream.Collectors;

/**
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

        假定 BST 有如下定义：

        结点左子树中所含结点的值小于等于当前结点的值
        结点右子树中所含结点的值大于等于当前结点的值
        左子树和右子树都是二叉搜索树
        例如：
        给定 BST [1,null,2,2],

        1
        \
        2
        /
        2
        返回[2].

        提示：如果众数超过1个，不需考虑输出顺序

        进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _501_二叉搜索树中的众数 {

    /**
     *
     * 二叉搜索树中序遍历是升序
     * 所以我们先dfs中序遍历二叉树, 将遍历结果存入list中
     * 再遍历list得到众数
     *
     * 时间复杂度 : O(n)  线性的时间复杂度
     * 空间复杂度 : O(n)  使用了额外的list存储空间
     *
     * */
    List<Integer> list = new ArrayList<>();
    public void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }

    public int[] findMode(TreeNode root) {
        dfs(root);
        List<Integer> res = new ArrayList<>();
        if (list.size() <= 1) return list.stream().mapToInt(Integer::valueOf).toArray();
        int maxCnt = 1, curCnt = 1, preNum = list.get(0);
        res.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int curNum = list.get(i);
            if (curNum == preNum) {
                curCnt++;
                if (curCnt > maxCnt) res.clear();
                maxCnt = Math.max(maxCnt, curCnt);
            } else {
                curCnt = 1;
                preNum = curNum;
            }
            if (curCnt == maxCnt) res.add(curNum);
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);

        _501_二叉搜索树中的众数 cls = new _501_二叉搜索树中的众数();
        cls.findMode(node);
    }
}
