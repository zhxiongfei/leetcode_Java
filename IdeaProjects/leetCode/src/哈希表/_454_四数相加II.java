package 哈希表;

import java.util.HashMap;

/**
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

        为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

        例如:

        输入:
        A = [ 1, 2]
        B = [-2,-1]
        C = [-1, 2]
        D = [ 0, 2]

        输出:
        2

        解释:
        两个元组如下:
        1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
        2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/4sum-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _454_四数相加II {

    /**
     * 哈希表
     *
     * 将 4个 数组分为两部分 A+B  C+D
     * 使用二重循环遍历得到所有 A[i]+B[j] 的值存入哈希表。
     * C, D 同理.
     *
     * 遍历两个哈希表
     * 当两个哈希表的 key 相加为 0时，res += val1 * val2;
     *
     * 时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(N ^ 2)
     *
     * */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer,Integer> map1 = new HashMap<>();
        for (int i : A) {
            for (int j : B) {
                map1.put(i + j, map1.getOrDefault(i + j, 0) + 1);
            }
        }
        HashMap<Integer,Integer> map2 = new HashMap<>();
        for (int i : C) {
            for (int j : D) {
                map2.put(i + j, map2.getOrDefault(i + j, 0) + 1);
            }
        }

        int res = 0;
        for(Integer key : map1.keySet()){
            int cnt = map1.get(key);
            if (map2.containsKey(-key)) {
                cnt *= map2.get(-key);
                res += cnt;
            }
        }
        return res;
    }

    /**
     * 优化
     * 在遍历 C + D 组合时,计算出结果值
     * */
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i : A) {
            for (int j : B) {
                map.put(i + j, map.getOrDefault(i + j, 0) + 1);
            }
        }
        int res = 0;
        for (int i : C) {
            for (int j : D) {
                if (map.containsKey(-i - j)) res += map.get(-i - j);
            }
        }

        return res;
    }

}
