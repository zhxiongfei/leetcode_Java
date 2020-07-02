package 堆;

/*
给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
        请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。

         

        示例:

        matrix = [
        [ 1,  5,  9],
        [10, 11, 13],
        [12, 13, 15]
        ],
        k = 8,

        返回 13。
         

        提示：
        你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class _378_有序矩阵中第K小的元素 {

    /*
    *
    * 使用大顶堆(较大元素放在堆顶)
    * 依次把 矩阵中元素加入 最大堆， 当堆的size > k 时, 移除栈顶元素
    * 最终栈顶元素即为最终结果
    * 时间复杂度 : O(N^2 * LogK)
    * */
    public int kthSmallest1(int[][] matrix, int k) {

        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                queue.add(matrix[i][j]);

                if (queue.size() > k){
                    queue.poll();
                }
            }
        }

        return queue.peek();
    }

    /*
    *
    * 上述方法中，完全没有用到矩阵横向和纵向都是有序的 这一特性
    * 时间复杂度比较高
    * 下面尝试做优化
    * */
    public int kthSmallest(int[][] matrix, int k) {

        // 矩阵最小值min 为 左上元素
        int min = matrix[0][0];

        // 矩阵最大值max 为 右下元素
        int max = matrix[matrix.length - 1][matrix.length - 1];

        // 第 k 个小元素一定在 [min, max] 范围

        // 求出中间元素 。 检查矩阵中有多少个元素 < mid,假设小于mid 的元素个数为 num.
        // 当 num 比 k小时。 说明 最终结果在 [mid + 1, max]之间, 则 min = mid + 1;
        // 当 num 比 k大是。 说明 最终结果在 [min, mid]之间, 则 max = mid

        while (min < max){
            int mid = (min + max) >> 1;
            int smallCnt = getLowCount(matrix, mid);
            if (smallCnt < k){
                min = mid + 1;
            }else{
                max = mid;
            }
        }
        return min;
    }

    // 获取比 mid 小的元素
    // 从矩阵的最后一排，第一列开始比较
    // 从矩阵，左下角开始对比
    // 如果左下角元素 比 mid 大，则，对应的一整列都比 mid 大，所以 cnt += (i + 1)
    // 如果比 mid 大. 则一整行都比 mid 大， 开始比较 i-1 行
    int getLowCount(int[][] matrix, int mid){

        int i = matrix.length - 1, j = 0, cnt = 0;
        while (i >= 0 && j <= matrix.length - 1) {
            if (matrix[i][j] <= mid) {
                cnt += (i + 1);
                j++;
            } else {
                i--;
            }
        }
        return cnt;
    }

}
