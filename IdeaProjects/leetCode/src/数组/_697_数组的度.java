package 数组;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
        给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
        你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
         
        示例 1：
        输入：[1, 2, 2, 3, 1]
        输出：2
        解释：
        输入数组的度是2，因为元素1和2的出现频数最大，均为2.
        连续子数组里面拥有相同度的有如下所示:
        [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
        最短连续子数组[2, 2]的长度为2，所以返回2.

        示例 2：
        输入：[1,2,2,3,1,4,2]
        输出：6
         
        提示：
        nums.length 在1到 50,000 区间范围内。
        nums[i] 是一个在 0 到 49,999 范围内的整数。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/degree-of-an-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _697_数组的度 {

    // 题目描述的比较晦涩难懂
    // 总结下就是找数组的众数，并且找出众数在数组中第一次出现和最后一次出现的下标
    // 两个位置组成的区间长度就是答案
    // 如果众数不止一个， 找出区间长度的最小值

    // map 中存储 <元素 : [元素出现次数， 元素第一次出现次数， 元素最后一次出现次数]>
    // 遍历 nums， 构建 map
    // 第一次遍历 map，找到众数的个数
    // 第二次遍历 map，找到众数的元素放入数组
    // 第三次遍历 map，找到众数的度最小值
    /**
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * 提交后，通过执行耗时 169ms -> 击败 5%
     * 猜想是因为 map 遍历3次太多了 -> 导致耗时长
     * */
    public int findShortestSubArray1(int[] nums) {
        HashMap<Integer,int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)){
                map.get(num)[0]++;
                map.get(num)[2] = i;
            }else {
                map.put(num,new int[]{1,i,i});
            }
        }
        int maxValue = 0, res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            maxValue = Math.max(entry.getValue()[0], maxValue);
        }

        List<Integer>list = new ArrayList<>();
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (entry.getValue()[0] == maxValue) list.add(entry.getKey());
        }
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (list.contains(entry.getKey())){
                res = Math.min(res, entry.getValue()[2] - entry.getValue()[1] + 1);
            }
        }
        return res;
    }
    
    /**
     * 优化为 遍历一遍 map
     * 耗时 11ms
     * */
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,int[]> map = new HashMap<>();
        int length = nums.length, res = length, maxCnt = 0;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num)){
                int[] tmp = map.get(num);
                tmp[0]++;
                tmp[2] = i;
            }else {
                map.put(num, new int[]{1, i, i});
            }
        }
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] counts = entry.getValue();
            if (counts[0] > maxCnt){
                maxCnt = counts[0];
                res = counts[2] - counts[1] + 1;
            }else if (counts[0] == maxCnt){
                res = Math.min(res, counts[2] - counts[1] + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _697_数组的度 cls = new _697_数组的度();
        int[] nums = {1, 2, 2, 3, 1};
        System.out.println(cls.findShortestSubArray(nums));
    }

}
