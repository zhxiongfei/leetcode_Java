package 排序;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

        （这里，平面上两点之间的距离是欧几里德距离。）

        你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

         

        示例 1：

        输入：points = [[1,3],[-2,2]], K = 1
        输出：[[-2,2]]
        解释：
        (1, 3) 和原点之间的距离为 sqrt(10)，
        (-2, 2) 和原点之间的距离为 sqrt(8)，
        由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
        我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
        示例 2：

        输入：points = [[3,3],[5,-1],[-2,4]], K = 2
        输出：[[3,3],[-2,4]]
        （答案 [[-2,4],[3,3]] 也会被接受。）
         

        提示：

        1 <= K <= points.length <= 10000
        -10000 < points[i][0] < 10000
        -10000 < points[i][1] < 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _973_最接近原点的K个点 {

    /**
     * 全排序
     * 时间复杂度 : O(N * logN)
     * 空间复杂度 : O(1)
     * */
    public int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (int)(Math.pow(o1[0], 2) + Math.pow(o1[1], 2) - Math.pow(o2[0], 2) - Math.pow(o2[1], 2));
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }

    /**
     * 堆
     * 优先级队列
     * 时间复杂度 : O(N * logK)
     * 空间复杂度 : O(K)
     * */
    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]>heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (int)(Math.pow(o1[0], 2) + Math.pow(o1[1], 2) - Math.pow(o2[0], 2) - Math.pow(o2[1], 2));
            }
        });

        for (int[] point : points) {
            heap.add(point);
            if (heap.size() > K) heap.poll();
        }
        return heap.toArray(new int[0][0]);
    }

    /**
     * 快排减治思想
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * */
    public int[][] kClosest(int[][] points, int K) {
        if (K == points.length) return points;
        return kClosestHelper(points, 0, points.length - 1, K);
    }

    private int[][] kClosestHelper(int[][] points, int begin, int end, int targetIdx){
        // 轴点元素下标
        int pivotIdx = pivotIndex(points, begin, end);
        // 当轴点元素下标 正好是 targetIdx
        if (pivotIdx == targetIdx) return Arrays.copyOfRange(points, 0, targetIdx);
        // 当轴点元素下标 > targetIdx
        if (pivotIdx > targetIdx){
            return kClosestHelper(points, begin, pivotIdx - 1, targetIdx);
        }else {
            return kClosestHelper(points, pivotIdx + 1, end, targetIdx);
        }
    }

    // 找轴点元素
    private int pivotIndex(int[][]points, int begin, int end){
        int[] tmpPit = points[begin];
        int tmp = square(points[begin]);
        boolean isRight = true;

        while (begin < end){
            if (isRight){
                if (square(points[end]) > tmp){
                    end --;
                }else {
                    points[begin ++] = points[end];
                    isRight = false;
                }
            }else {
                if (square(points[begin]) < tmp){
                    begin ++;
                }else {
                    points[end --] = points[begin];
                    isRight = true;
                }
            }
        }
        points[begin] = tmpPit;
        return begin;
    }

    private int square(int[] point){
        return (int)(Math.pow(point[0], 2) + Math.pow(point[1], 2));
    }

    public static void main(String[] args) {
        _973_最接近原点的K个点 cls = new _973_最接近原点的K个点();
        int[][] points = {{0,1},{2,0},{1,0},{0,3}};
        int[][] res = cls.kClosest(points, 4);
        System.out.println(res);
    }
}
