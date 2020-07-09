package 贪心;

/**
给定一个非负整数数组，你最初位于数组的第一个位置。

        数组中的每个元素代表你在该位置可以跳跃的最大长度。

        判断你是否能够到达最后一个位置。

        示例 1:

        输入: [2,3,1,1,4]
        输出: true
        解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
        示例 2:

        输入: [3,2,1,0,4]
        输出: false
        解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/jump-game
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _55_跳跃游戏 {

    /**
     输入: [2,3,1,0,4]
     输出: true
     解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
     */

    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        // targetIdx 代表目标索引
        // 跳到 最后一个位置 所以下标为 nums.length - 1
        int targetIdx = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int num = nums[i];

            // 如果 num == 0 或者 num + i < targetIdx
            // 代表 当前位置不可能跳到 targetIdx
            // 向前寻找，是否有更大数，可以跳到 targetIdx
            if (num == 0 || num + i < targetIdx) continue;

            // 开始位置也能跳到 targetIdx
            // 说明，可以从最初位置 跳到 最后一个位置了
            // return false
            if (i == 0) return true;

            // 如果 i 能跳到 targetIdx 了
            // 那么, i 前边的元素，只要跳到 i 就可以了
            // 所以 targetIdx = i;
            targetIdx = i;
        }
        return false;
    }

    /**
     *
     * 从前往后
     * 每一步都取, 能走步数最远的
     *
     * */
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff = Math.max(diff - 1, nums[i]);
            if (diff == 0 && i != nums.length - 1) return false;
        }
        return true;
    }

    /**
     *
     * 优化
     * 从前往后
     * 每一步都取, 能走步数最远的
     *
     * 优化 如果 diff >= nums.size() - 1就没必要往后比了
     *
     * */
    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff = Math.max(diff - 1, nums[i]);
            if (diff == 0 && i != nums.length - 1) return false;

            if (diff >= nums.length - 1 - i) return true;
        }
        return true;
    }
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        canJump(nums);
    }
}
