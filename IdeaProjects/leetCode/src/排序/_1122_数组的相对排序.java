package 排序;

import java.lang.reflect.Array;
import java.util.*;

/**
给你两个数组，arr1 和 arr2，
        arr2 中的元素各不相同
        arr2 中的每个元素都出现在 arr1 中
        对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

        示例：

        输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
        输出：[2,2,2,1,4,3,3,9,6,7,19]

        提示：

        1 <= arr1.length, arr2.length <= 1000
        0 <= arr1[i], arr2[i] <= 1000
        arr2 中的元素 arr2[i] 各不相同
        arr2 中的每个元素 arr2[i] 都出现在 arr1 中

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/relative-sort-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1122_数组的相对排序 {

    /**
     * 计数排序
     */
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        int[] counts = new int[1001];
        int[] res = new int[arr1.length];
        for (int i : arr1) {
            counts[i] ++;
        }

        int index = 0;
        for (int i : arr2) {
            for (int j = 0; j < counts[i]; j++) {
                res[index ++] = i;
            }
        }
        out : for (int i = 0; i < counts.length; i++) {
            for (int n : res) {
                if (n == i) continue out;
            }
            for (int j = 0; j < counts[i]; j++) {
                res[index ++] = i;
            }
        }
        return res;
    }

    /**
     * 哈希表 + 自定义排序
     * */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int num : arr1) list.add(num);
        for(int i = 0; i < arr2.length; i++) map.put(arr2[i], i);
        Collections.sort(list, (x, y) -> {
            if(map.containsKey(x) || map.containsKey(y)) return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            return x - y;
        });
        for(int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }

    public static void main(String[] args) {
        _1122_数组的相对排序 cls = new _1122_数组的相对排序();

        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        cls.relativeSortArray(arr1, arr2);
    }
}
