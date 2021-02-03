package 滑动窗口;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
 * */

public class _1423_可获得的最大点数 {

    // [1,2,3,4,5,6,1], k = 3

    /**
     * 滑动窗口
     * 时间复杂度 : O(k)
     * 空间复杂度 : O(1)
     * */
    public int maxScore(int[] cardPoints, int k) {
        if (k >= cardPoints.length) return Arrays.stream(cardPoints).sum();

        int left = 0, right = k - 1;
        int res = 0;
        for (int i = 0; i <= right; i++) {
            res += cardPoints[i];
        }

        int tmp = res;
        while (right >= 0){

            tmp -= cardPoints[right --];
            tmp += cardPoints[(--left + cardPoints.length) % cardPoints.length];
            res = Math.max(res, tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        _1423_可获得的最大点数 cls = new _1423_可获得的最大点数();
        int[] cardPoints = {1,2,3,4,5,6,1};
        cls.maxScore(cardPoints, 3);
    }
}
