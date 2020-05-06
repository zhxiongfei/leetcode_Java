package 堆;

/*
给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。

        定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。

        找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。

        示例 1:

        输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
        输出: [1,2],[1,4],[1,6]
        解释: 返回序列中的前 3 对数：
        [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
        示例 2:

        输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
        输出: [1,1],[1,1]
        解释: 返回序列中的前 2 对数：
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
        示例 3:

        输入: nums1 = [1,2], nums2 = [3], k = 3
        输出: [1,3],[2,3]
        解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class _373_查找和最小的K对数字 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        // 最大堆
        PriorityQueue<List> queue = new PriorityQueue<>(
                (List e1, List e2) -> {
                    int res1 = (int)e1.get(0) + (int)e1.get(1);
                    int res2 = (int)e2.get(0) + (int)e2.get(1);
                    return res2 - res1;
                });

        for (int i = 0; i < nums1.length; i++) {
            int n1 = nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                int n2 = nums2[j];

                List l = new ArrayList();
                l.add(n1);
                l.add(n2);
                if (queue.size() < k){
                    queue.add(l);
                }else{

                    ArrayList list = (ArrayList) queue.peek();
                    int sum = (int)list.get(0) + (int)list.get(1);
                    if ((n1 + n2) < sum){
                        queue.poll();
                        queue.add(l);
                    }
                }
            }
        }

        List res = new LinkedList();
        while (!queue.isEmpty()){
            res.add(queue.poll());
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        int[] nums1 = {1,1,2};
        int[] nums2 = {1,2,3};
        int k = 2;

        _373_查找和最小的K对数字 test = new _373_查找和最小的K对数字();

        List list = test.kSmallestPairs(nums1,nums2,k);
        System.out.println(list);
    }
}
