package Interview;

/*
给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。

        示例:
        给定有序数组: [-10,-3,0,5,9],

        一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

        0
        / \
        -3   9
        /   /
        -10  5

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class _面试题_04_02_最小高度树 {

    // 思路：
    // 因为是升序数组，且生成二叉排序树
    // 所以树的根节点，是数组的中间元素(总个数为双数的数组，中间左右均可)
    // 先取出树的根节点
    // 再分别递归计算其 左，右子树
    public TreeNode sourtedArrayToBST(int[] nums, int left, int right){
        if (right <= left) return null;

        int mid = (left + right) >> 1;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = sourtedArrayToBST(nums,left,mid);
        node.right = sourtedArrayToBST(nums,mid,right);

        return node;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sourtedArrayToBST(nums,0,nums.length);
    }
}
