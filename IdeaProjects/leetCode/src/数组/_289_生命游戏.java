package 数组;

import java.util.Arrays;

/**
根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。

        给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

        如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
        如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
        如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
        如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
        下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。

         

        示例 1：


        输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
        输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
        示例 2：


        输入：board = [[1,1],[1,0]]
        输出：[[1,1],[1,1]]
         

        提示：

        m == board.length
        n == board[i].length
        1 <= m, n <= 25
        board[i][j] 为 0 或 1
         

        进阶：

        你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
        本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/game-of-life
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _289_生命游戏 {

    /**
     * 暴力解法
     * 复制一个矩阵的深拷贝
     *
     * 遍历拷贝矩阵
     * 遍历过程中， 计算相邻 8 个位置的值
     * 记录生，死细胞的数量
     *
     * 根据规则修改 原数组
     *
     * 时间复杂度 : O(m * n)
     * 空间复杂度 : O(m * n)
     *
     * */
    public void gameOfLife(int[][] board) {
        int rows = board.length, cols = board[0].length;
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                int live = 0, dead = 0;
                int topL = -1, top = -1, topR = -1,left = -1, right = -1, botL = -1, bot = -1, botR = -1;

                // 8个都有
                if (i>0 && j>0) topL = copy[i - 1][j - 1];
                if (i > 0) top = copy[i - 1][j];
                if (i > 0 && j + 1 < cols) topR = copy[i - 1][j + 1];
                if (j > 0) left = copy[i][j - 1];
                if (j + 1 < cols) right = copy[i][j + 1];
                if (i + 1 < rows && j > 0) botL = copy[i + 1][j - 1];
                if (i + 1 < rows) bot = copy[i + 1][j];
                if (i + 1 < rows && j + 1 < cols) botR = copy[i + 1][j + 1];

                int[] nums = {topL, top, topR, left, right, botL, bot, botR};

                for (int num : nums) {
                    if (num == -1) continue;
                    if (num == 1) live ++;
                    else dead++;
                }

                // 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
                // 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
                // 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
                // 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
                if (live < 2) board[i][j] = 0;
                else if (live == 3 && board[i][j] == 0) board[i][j] = 1;
                else if ((live == 2 || live == 3) && board[i][j] == 1) board[i][j] = 1;
                else if (live > 3) board[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        _289_生命游戏 cls = new _289_生命游戏();
        int[][]board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        cls.gameOfLife(board);
    }
}
