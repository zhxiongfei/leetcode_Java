package 回溯算法;

/*
设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。

        注意：本题相对原题做了扩展

        示例:

        输入：4
        输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
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

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/eight-queens-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

public class _面试题_08_12_八皇后 {

    // 存储最终结果数组
    List<List<String>> lists;

    // 存放每一行皇后的列号
    int[] cols;

    // 一共有多少合理的解法
    private int ways;

    // 皇后的个数
    private int queenCnt;

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) return null;
        cols = new int[n];
        queenCnt = n;
        lists = new ArrayList<>();

        // 从di 0 行开始
        place(0);
        return lists;
    }

    // 从第row行，开始摆放皇后
    void place(int row){
        // 当 行数 == 皇后数时，说明已经走到最后 ways++
        // 并且 组装结果数组
        if (row == queenCnt){
            ways ++;
            show();
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

    // 组装最终结果数组
    void show(){
        List<String> list = new ArrayList<>();
        for (int row = 0; row < queenCnt; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < queenCnt; col++) {
                if (cols[row] == col){
                    // 皇后位置
                    sb.append("Q");
                }else {
                    // 非皇后位置
                    sb.append(".");
                }
            }
            list.add(sb.toString());
        }
        lists.add(list);
    }

    public static void main(String[] args) {

        _面试题_08_12_八皇后 cls = new _面试题_08_12_八皇后();
        cls.solveNQueens(4);
    }
}
