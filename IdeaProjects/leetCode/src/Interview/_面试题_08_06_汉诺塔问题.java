package Interview;

/*
在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
        (1) 每次只能移动一个盘子;
        (2) 盘子只能从柱子顶端滑出移到下一根柱子;
        (3) 盘子只能叠在比它大的盘子上。

        请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。

        你需要原地修改栈。

        示例1:

        输入：A = [2, 1, 0], B = [], C = []
        输出：C = [2, 1, 0]
        示例2:

        输入：A = [1, 0], B = [], C = []
        输出：C = [1, 0]
        提示:

        A中盘子的数目不大于14个。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/hanota-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;

/*
解题思路

        建议参考UIUC的Jeff的《Algorithm》第一章，里面对递归思想有很清晰易懂的论述。
        key就是不要去深入想，要会浅尝辄止。
        比如该汉诺塔问题
            只需要思考怎么把A的全部移动到C
                把A的上面n-1个移动到B
                再把A最下面的一个移动到C
                再把B的所有移动到C

        至于怎么把A的上面n-1个移动到B和怎么把B的所有移动到C不要去想，把他交给你的函数去解决。这其实是一种化归的思想。

        本题中，move函数，作用就是把 N 个碟子从A移动到C
        所以解题步骤为：
            1，把 A 除底层以外的 n - 1个盘子， 从 A移动到B
            2，把 A 最底层 的 N 移动到 C
            3，把 B 的N-1个盘子 移动到 C
 */

public class _面试题_08_06_汉诺塔问题 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {

        move(A.size(), A, B, C);
    }

    public void move(int n, List<Integer> A, List<Integer> B, List<Integer> C){

        if (n <= 1) {
            // 只有一个时，把A 移动到 C即可
            C.add(A.remove(A.size() - 1));
            return;
        }

        // 把 A底层以外挪动到n-1 B
        move(n-1, A, C, B);

        // 把 A的最底层n 挪动到 C
        C.add(A.remove(A.size() - 1));

        // 把 B 挪动到 C
        move(n-1, B, A, C);
    }
}
