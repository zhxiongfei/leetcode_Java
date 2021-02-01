package 哈希表;

import java.util.Arrays;
import java.util.HashMap;

/**
https://leetcode-cn.com/problems/fair-candy-swap/
*/

public class _888_公平的糖果棒交换 {

    // [1,1] -> 2
    // [2,3] -> 4

    // diff -> 2

    // A + 1
    // B - 1

    /**
     * 哈希
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * */
    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();

        boolean flag = sumB > sumA ? true : false;
        int diff = flag ? sumB - sumA : sumA - sumB;
        if ((diff & 1) != 0) return null;

        int minus = diff >> 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : A) {
            map.put(flag ? num + minus : num - minus, num);
        }

        for (int num : B) {
            if (map.containsKey(num)){
                int[] res = new int[]{map.get(num), num};
                return res;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        _888_公平的糖果棒交换 cls = new _888_公平的糖果棒交换();
        int[] A = {2};
        int[] B = {1,3};
        int[] res = cls.fairCandySwap(A, B);

        for (int re : res) {
            System.out.println(re);
        }
    }
}
