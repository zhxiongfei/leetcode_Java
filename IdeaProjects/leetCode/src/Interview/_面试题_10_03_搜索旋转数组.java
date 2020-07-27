package Interview;

import java.util.Arrays;

/**
        搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
        请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。

        示例1:

        输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
        输出: 8（元素5在该数组中的索引）
        示例2:

        输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
        输出：-1 （没有找到）
        提示:

        arr 长度范围在[1, 1000000]之间

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/search-rotate-array-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _面试题_10_03_搜索旋转数组 {

    public int search(int[] arr, int target) {
        int n = arr.length;
        while (n > 1 && arr[n - 1] == arr[0]) n--;

        // 求最小值的索引
        int l = 0, r = n - 1;
        while (l < r){
            int mid = (l + r) >> 1;
            if (arr[mid] <= arr[r]) r = mid;
            else l = mid + 1;
        }

        // 求target的索引
        r = l + n - 1;
        if (target < arr[l]) return -1;
        while (l < r){
            int mid = (l + r) >> 1;
            if (arr[mid % n] >= target) r = mid;
            else l = mid + 1;
        }
        if (arr[l%n] == target) return l%n;
        return -1;
    }

    public static void main(String[] args) {
        _面试题_10_03_搜索旋转数组 cls = new _面试题_10_03_搜索旋转数组();

        int[] nums = {21, 21, -21, -20, -17, -8, -6, -2, -2, -1, 0, 2, 3, 4, 4, 6, 11, 13, 14, 16, 17, 18, 20};
        int res = cls.search(nums,14);
        System.out.println(res);
    }

}
