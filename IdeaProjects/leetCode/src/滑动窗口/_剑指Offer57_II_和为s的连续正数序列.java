package 滑动窗口;

/*
输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

        序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

         

        示例 1：

        输入：target = 9
        输出：[[2,3,4],[4,5]]
        示例 2：

        输入：target = 15
        输出：[[1,2,3,4,5],[4,5,6],[7,8]]
         

        限制：

        1 <= target <= 10^5

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

public class _剑指Offer57_II_和为s的连续正数序列 {

    /**
     *
     * 滑动窗口
     *
     * */
    public static int[][] findContinuousSequence(int target) {

        List<int []> res = new ArrayList<>();

        int left = 1, right = 2, sum = 1;
        while (right < target && (left <= target >> 1)){
            if ( sum == target){
                // 添加滑动窗口中的元素
                // 窗口左边 右移
                int [] list = new int[right - left];
                for (int i = left; i < right; i++) {
                    list[i] = i;
                }
                sum -= left ++;
                res.add(list);
            }else if (sum < target){
                // 窗口右边 右移
                sum += right ++;
            }else {
                sum -= left ++;
            }
        }

        int[][] ans = res.toArray(new int[res.size()][]);
        return ans;
    }

    public static void main(String[] args) {
        findContinuousSequence(3);
    }

}
