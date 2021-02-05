package 滑动窗口;

import javax.management.StandardEmitterMBean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。

        返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。

        示例 1:

        输入:
        big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
        small = [1,5,9]
        输出: [7,10]
        示例 2:

        输入:
        big = [1,2,3]
        small = [4]
        输出: []
        提示：

        big.length <= 100000
        1 <= small.length <= 100000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shortest-supersequence-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _面试题_17_18_最短超串 {

    // [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7,16]
    // [1,5,9]

    /**
     * 滑动窗口
     * 先向右滑动，直到窗口中包含所有 small 中的元素
     * 再向左滑动，条件是窗口中仍然包含 所有 small 的元素。 尽量向左移动窗口。
     *
     * minLeft 为 最短的窗口左边界
     * minRight 为最短的窗口右边界
     * minLen 记录滑动窗口的最小值
     * valid  记录窗口中有效的数字个数
     *
     * */
    public int[] shortestSeq(int[] big, int[] small) {
        HashMap<Integer,Integer>need = new HashMap<>();
        HashMap<Integer,Integer>window = new HashMap<>();
        for (int i : small) need.put(i, 1);

        int left = 0, right = 0, minLeft = 0, minRight = 0, minLen = Integer.MAX_VALUE, valid = 0;
        int length = big.length, smallSize = small.length;
        while (right < length){
            int num = big[right ++];

            if (need.containsKey(num) && need.get(num) != 0){
                window.put(num, window.getOrDefault(num, 0) + 1);
                if (window.get(num) == need.get(num)) valid ++;
            }

            while (valid == smallSize){
                if (right - left < minLen){
                    minLeft = left;
                    minRight = right - 1;
                    minLen = right - left;
                }

                int tmp = big[left++];
                if (need.containsKey(tmp) && need.get(tmp) != 0){
                    if (need.get(tmp) == window.get(tmp)) valid --;
                    window.put(tmp, window.getOrDefault(tmp,0) - 1);
                }
            }
        }
        if (minLen == Integer.MAX_VALUE) return new int[0];
        return new int[]{minLeft, minRight};
    }

    public static void main(String[] args) {
        _面试题_17_18_最短超串 cls = new _面试题_17_18_最短超串();
        int[] big = {7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7};
        int[] small = {1,5,9};
        cls.shortestSeq(big, small);

    }
}
