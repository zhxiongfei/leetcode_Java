package 数组;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

        换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

        以数组形式返回答案。

         

        示例 1：

        输入：nums = [8,1,2,2,3]
        输出：[4,0,1,1,3]
        解释：
        对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
        对于 nums[1]=1 不存在比它小的数字。
        对于 nums[2]=2 存在一个比它小的数字：（1）。
        对于 nums[3]=2 存在一个比它小的数字：（1）。
        对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
        示例 2：

        输入：nums = [6,5,4,8]
        输出：[2,1,0,3]
        示例 3：

        输入：nums = [7,7,7,7]
        输出：[0,0,0,0]
         

        提示：

        2 <= nums.length <= 500
        0 <= nums[i] <= 100

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1365_有多少小于当前数字的数字 {

    /**
     * 使用 HashMap
     * 1，遍历数组，遍历过程中 map 赋值为每个数字出现的次数
     * 2，map的key排序后遍历 map, 将map转换为 {数字 : 小于它的值的个数}
     * 3，再次遍历 nums，遍历过程中，取出map对应的值，并赋值结果数组
     * 时间复杂度 : O(N)
     * 空间复杂度 : 常数级别(数字取值范围 [0,100])所以最大101长度的字典
     * */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Integer[]keys = map.keySet().toArray(new Integer[] {});
        Arrays.sort(keys);
        int cnt = 0;
        for (int i = 0; i < keys.length; i++) {
            Integer key = (Integer) keys[i];
            int curCnt = map.get(key);
            map.put(key, cnt);
            cnt += curCnt;
        }
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ret[i] = map.get(nums[i]);
        }
        return nums;
    }

    /**
     * 因为题目中数字的范围是 [0, 100]
     * 所以可以使用数组代替 map
     * 基本思想跟上解法一致
     * 时间复杂度 : O(N)
     * 空间复杂度 : 常数级别(长度为101的int数组)
     *
     * 使用数字代替字典，没有了字典底层复杂的hash运算等操作
     * 执行耗时有了明显的提高
     * */
    public static int[] smallerNumbersThanCurrent1(int[] nums) {
        int[]cnts = new int[101];
        for (int num : nums) {
            cnts[num]++;
        }

        int cnt = 0;
        for (int i = 0; i < cnts.length; i++) {
            int curCnt = cnts[i];
            cnts[i] = cnt;
            cnt += curCnt;
        }
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ret[i] = cnts[nums[i]];
        }
        return ret;
    }

    /**
     * 排序
     * */
    public static int[] smallerNumbersThanCurrent2(int[] nums) {
        int length = nums.length;
        int[][]sorted = new int[nums.length][2];

        for (int i = 0; i < length; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }

        Arrays.sort(sorted, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        int i = 0, j = 0;
        while (i < length){
            j = i + 1;
            if (j == length) {
                nums[sorted[i][1]] = 0;
                break;
            }
            int cur = sorted[i][0];
            int nex = sorted[j][0];
            while (cur == nex){
                j ++;
                if (j >= length) break;
                nex = sorted[j][0];
            }

            nums[sorted[i][1]] = length - j;
            i ++;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {60,14,3,39,49,43};
        smallerNumbersThanCurrent2(nums);
    }

}
