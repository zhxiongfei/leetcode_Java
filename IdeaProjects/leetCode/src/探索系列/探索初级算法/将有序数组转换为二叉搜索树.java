package 探索系列.探索初级算法;

public class 将有序数组转换为二叉搜索树 {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        return sortedArrayToBST(nums, 0, nums.length);
    }

    // 左闭右开
    public TreeNode sortedArrayToBST(int[] nums, int left, int right){
        if (right - left < 1) return null;

        int mid = (left + right) >> 1;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid);
        root.right = sortedArrayToBST(nums, mid + 1, right);

        return root;
    }



}
