package 回溯算法;

import java.util.*;

/**
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

        如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

        必须原地修改，只允许使用额外常数空间。

        以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
        1,2,3 → 1,3,2
        3,2,1 → 1,2,3
        1,1,5 → 1,5,1



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/next-permutation
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class _31_下一个排列 {

    /**
     * 暴力回溯法
     * 暴力循环每一种可能
     * 时间复杂度过高， 且明显不符合题目对空间复杂度的要求 原地修改
     * 所以 未通过要求.
     * */
    public void nextPermutation1(int[] nums) {

        int[] sortedNums = new int[nums.length];
        System.arraycopy(nums,0,sortedNums,0, nums.length);
        Arrays.sort(sortedNums);

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[nums.length];
        int length = nums.length;

        dfs(sortedNums, length, 0, path, used, res);

        int prev = -1;
        out:for (int i = res.size() - 1; i >= 0; i--) {
            List<Integer>integers = res.get(i);
            for (int j = 0; j < integers.size(); j++) {
                if (integers.get(j) != nums[j]) continue out;
            }
            prev = i;
            break out;
        }
        int cur = prev == res.size() - 1 ? 0 : prev + 1;
        for (int i = 0; i < res.get(cur).size(); i++) {
            nums[i] = res.get(cur).get(i);
        }
    }

    public void dfs(int[] nums, int length,int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res){
        if (depth == length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, length, depth + 1, path, used, res);

            path.removeLast();
            used[i] = false;
        }
    }

    /**
     * 1，从右向左查找 第一个下降的数字下标，记为 i
     * 2，从 i 开始往右找，找到比 nums[i] 大的最小元素下标,记为j
     * 3, 交换 i 和 j 位置的元素
     * 4, i 往后位置的元素排序
     * */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) return;

        int i = nums.length - 2;
        while (i >= 0){
            if (nums[i] < nums[i + 1]){
                break;
            }
            i --;
        }
        if (i == -1) {
            Arrays.sort(nums);
            return;
        }

        int j = i + 1;
        while (j < nums.length){
            if (nums[i] > nums[j]){
                break;
            }
            j ++;
        }
        j --;

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

        Arrays.sort(nums, i + 1, nums.length);
    }

    public static void main(String[] args) {

        _31_下一个排列 cls = new _31_下一个排列();
        int[] nums = {1,3,2};
        cls.nextPermutation(nums);
    }

}
