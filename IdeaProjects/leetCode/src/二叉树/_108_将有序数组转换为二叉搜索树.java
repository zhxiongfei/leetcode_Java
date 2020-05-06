package 二叉树;

/*
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

        本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

        示例:

        给定有序数组: [-10,-3,0,5,9],

        一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

        0
        / \
        -3   9
        /   /
        -10  5

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import javax.annotation.processing.SupportedSourceVersion;

public class _108_将有序数组转换为二叉搜索树 {

    // 递归
    static TreeNode sortedArrayToBST(int[] nums) {

        if (nums == null || nums.length == 0) return null;
        return arrToBst(0,nums.length,nums);
    }

    // 左闭右开
    static TreeNode arrToBst(int begain,int end,int[] nums){

        if (end - begain < 1) return null;

        int mid = (begain + end) >> 1;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = arrToBst(begain,mid,nums);
        root.right = arrToBst(mid+1,end,nums);

        return root;
    }

    public static void main(String[] args){
        int[] nums = {-10,-3,0,5,9};
        TreeNode node = sortedArrayToBST(nums);

        System.out.println(node.val);
    }
}
