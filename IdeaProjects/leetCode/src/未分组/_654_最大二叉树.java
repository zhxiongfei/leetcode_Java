package 未分组;

/*
给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：

        二叉树的根是数组中的最大元素。
        左子树是通过数组中最大值左边部分构造出的最大二叉树。
        右子树是通过数组中最大值右边部分构造出的最大二叉树。
        通过给定的数组构建最大二叉树，并且输出这个树的根节点。

         

        示例 ：

        输入：[3,2,1,6,0,5]
        输出：返回下面这棵树的根节点：

        6
        /   \
        3     5
        \    /
        2  0
        \
        1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import com.sun.source.tree.Tree;

import java.util.Stack;

public class _654_最大二叉树 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return findRoot(nums,0,nums.length);
    }

    // [l,r)
    public TreeNode findRoot(int[]nums,int l, int r){

        if (l == r) return null;

        int maxIdx = l;
        for (int i = l; i < r; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = findRoot(nums,l,maxIdx);
        root.right = findRoot(nums,maxIdx + 1,r);

        return root;
    }

    public int[] getArray(int[] nums){
        int[] leftIdx = new int[nums.length];
        int[] rightIdx = new int[nums.length];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            leftIdx[i] = -1;
            rightIdx[i] = -1;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                // 出栈  且 top 右边第一个大的数值是 nums[i]
                rightIdx[stack.pop()] = i;
            }

            // 入栈  且 i 左边第一个大的数值是 top
            leftIdx[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int[] resIdx = new int[nums.length];
        for (int i = 0; i < leftIdx.length; i++) {

            if (leftIdx[i] == -1 && rightIdx[i] == -1){
                resIdx[i] = -1;
                continue;
            }

            if (leftIdx[i] == -1){
                resIdx[i] = rightIdx[i];
                continue;
            }
            if (rightIdx[i] == -1) {
                resIdx[i] = leftIdx[i];
                continue;
            }

            if (nums[leftIdx[i]] > nums[rightIdx[i]]){
                resIdx[i] = rightIdx[i];
            }else {
                resIdx[i] = leftIdx[i];
            }
        }

        return resIdx;
    }

    public static void main(String[] args) {
        _654_最大二叉树 test = new _654_最大二叉树();

        int[] nums = {3,2,1,6,0,5};
        int[] res = test.getArray(nums);
        System.out.println(res.toString());
    }
}
