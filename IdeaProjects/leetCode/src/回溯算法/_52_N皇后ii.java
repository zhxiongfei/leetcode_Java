package 回溯算法;

/*
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



        上图为 8 皇后问题的一种解法。

        给定一个整数 n，返回 n 皇后不同的解决方案的数量。

        示例:

        输入: 4
        输出: 2
        解释: 4 皇后问题存在如下两个不同的解法。
        [
         [".Q..",  // 解法 1
          "...Q",
          "Q...",
          "..Q."],

         ["..Q.",  // 解法 2
          "Q...",
          "...Q",
          ".Q.."]
        ]
         

        提示：

        皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步，可进可退。（引用自 百度百科 - 皇后 ）

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/n-queens-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _52_N皇后ii {

    // 存放每一个皇后的列号
    int[] cols;

    // 一共有多少合理的解法
    private int ways;

    // 皇后的个数
    private int queenCnt;

    public int totalNQueens(int n) {
        if (n < 1) return 0;
        cols = new int[n];
        queenCnt = n;

        place(0);
        return ways;
    }

    // 从第row行，开始摆放皇后
    void place(int row){
        if (row == queenCnt){
            ways ++;
            return;
        }

        for (int col = 0; col < queenCnt; col++) {
            if (isValid(row,col)){
                cols[row] = col;
                // 继续下一行
                // 如果一直没有可以放置的位置， 则回溯
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col){
        for (int i = 0; i < row; i++) {
            // 当前列 已经有 相同的了
            if (cols[i] == col) {
                return false;
            }

            // 对角线 已经有 相同的了
            if (row - i == Math.abs(col - cols[i])) {
                return false;
            }
        }
        return true;
    }
}
