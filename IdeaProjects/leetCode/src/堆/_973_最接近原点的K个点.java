package 堆;

/*
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

import java.util.Comparator;
import java.util.PriorityQueue;

public class _973_最接近原点的K个点 {


    /*
    *
    * 思路一：
    * 全排序， 取开头K个元素
    * 用最优的快排，时间复杂度 O(N * logN)
    * 因为有比此方法更优的题解，此处不写代码.
    * */


    /*
    *
    * 思路一：堆
    * 使用最大堆 (堆的比较方法为, 数组中两个数字的平方和)
    * 将points数组中存放的point 依次 如堆
    * 当堆中元素 > K 时，弹出堆顶元素
    *
    * 遍历完毕后, 堆中存放的 point 就是距离原点最小的 K 个点
    *
    * 时间复杂度 : O(N * log K)
    * 空间复杂度 : O(K)
    *
    * */
    public int[][] kClosest(int[][] points, int K) {

        // 最大堆
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (int) (Math.pow(o2[0], 2) + Math.pow(o2[1], 2) - Math.pow(o1[0], 2) - Math.pow(o1[1], 2));
            }
        }) ;

        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];

            queue.add(point);

            if (queue.size() > K){
                queue.remove();
            }
        }

        int[][] result = new int[K][2];
        int i = 0;
        while (!queue.isEmpty()){{
            result[i++] = queue.poll();
        }}

        return result;
    }

    /*
    *
    * 思路三 : 分治
    * 题解中比较高端的分治解法，暂时还没有理解
    * 晚上回家再补上
    * */
}
