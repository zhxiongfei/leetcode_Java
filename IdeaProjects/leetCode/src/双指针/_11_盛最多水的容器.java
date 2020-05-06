package 双指针;

/*
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

        说明：你不能倾斜容器，且 n 的值至少为 2。



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/container-with-most-water
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;
import java.util.Set;

public class _11_盛最多水的容器 {

    private int area(int m, int n, int i, int j) {
        return Math.abs(j - i) * Math.min(m, n);
    }

    // 双指针 一个指针指向开头，一个指向末尾。 算出area。把高度的一端向中心移动。 重新计算area。 知道i < j
    public int maxArea(int[] height) {
        int len = height.length;
        int ans = 0, i = 0, j = len - 1;
        while (i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            ans = Math.max(ans, area);

            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return ans;
    }

    // 暴力法
    public int maxArea1(int[] height) {
        int len = height.length;
        int ans = 0;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {

                int area = Math.min(height[i], height[j]) * (j - i);

                ans = Math.max(ans, area);
            }
        }

        return ans;
    }

    public int maxArea2(int[] nums) {
        int len = nums.length;
        int max = 0, i = 0, j = len - 1;
        while (i < j) {
            int area = Math.min(nums[i], nums[j]) * (j - 1);
            max = Math.max(area, max);

            if (nums[i] > nums[j]) {
                j--;
            } else {
                i++;
            }
        }

        return max;
    }

    public int removeDuplicates(int[] nums){

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]){
                i ++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
