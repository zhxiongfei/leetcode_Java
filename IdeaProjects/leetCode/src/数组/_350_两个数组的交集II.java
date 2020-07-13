package 数组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class _350_两个数组的交集II {

    /**
     *
     * 方法一
     * 使用 哈希表 存储 nums1中存储的每个元素的出现的次数
     *
     * 遍历 nums2， 当哈希表中存储的当前元素的个数 > 0时，放入结果数组
     *
     * 时间复杂度 : O(m + n)
     * 空间复杂度 : O(min(m, n))
     *
     * */
    public static int[] intersect(int[] nums1, int[] nums2) {

        // 保证 nums2中的个数 > nums1中元素的个数
        // 减小空间复杂度
        if (nums1.length > nums2.length){
            return intersect(nums2, nums1);
        }

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }

        int[] res = new int[nums1.length];
        int idx = 0;
        for (int i = 0; i < nums2.length; i++) {
            int num = nums2[i];
            int cnt = map.getOrDefault(num, 0);
            if (cnt == 0) continue;

            map.put(num, map.getOrDefault(num, 0) - 1);

            res[idx ++] = num;
        }
        return Arrays.copyOfRange(res, 0, idx);
    }

    /**
     *
     *  先排序
     *  排序后，使用双指针法
     *
     *  时间复杂度 : O(m * logm + n * logn + m + n) = O(m * logm + n * logn)
     *
     *  空间复杂度 : O(min(m + n))
     *
     * */
    public static int[] intersect1(int[] nums1, int[] nums2) {
        // 保证 nums2中的个数 > nums1中元素的个数
        // 减小空间复杂度
        if (nums1.length > nums2.length){
            return intersect(nums2, nums1);
        }
        int[] res = new int[nums1.length];

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, idx = 0;
        while (i < nums1.length && j < nums2.length){
            int num1 = nums1[i], num2 = nums2[j];
            // 当两个数字一样，说明是公共元素
            // 加入 结果数组中
            if (num1 == num2){
                res[idx ++] = num1;
                i ++;
                j ++;
            }else if (num1 > num2){
                // 当 num1 > num2
                // j 右移动，nums2中 找寻下一个大的元素是否 nums1 相交
                j ++;
            }else {
                // 当 num2 > num1
                // i 右移动，nums1中 找寻下一个大的元素是否和 nums2 相交
                i ++;
            }
        }

        return Arrays.copyOfRange(res, 0, idx);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};

        intersect(nums1, nums2);
    }
}
