package 堆;

/*
给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
        请注意，它是排序后的第k小元素，而不是第k个元素。

        示例:

        matrix = [
        [ 1,  5,  9],
        [10, 11, 13],
        [12, 13, 15]
        ],
        k = 8,

        返回 13。
        说明:
        你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class _378_有序矩阵中第K小的元素 {

    public int kthSmallest(int[][] matrix, int k) {

        // 最大堆
        PriorityQueue<Integer> queue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            for (int j = 0; j < row.length; j++) {

                int num = row[j];
                if (queue.size() < k) {
                    queue.offer(num);
                } else if (num < queue.peek()) {
                    queue.poll();
                    queue.offer(num);
                }
            }
        }

        return queue.peek();
    }

    public static void main(String[] args) {

        _378_有序矩阵中第K小的元素 test = new _378_有序矩阵中第K小的元素();

        int[] [] matrix = {{1,2},{1,3}};
        int res = test.kthSmallest(matrix,2);
        System.out.println(res);
    }
}
