package 回溯算法;

import java.util.ArrayDeque;
import java.util.Deque;

/**
给定一个二维网格和一个单词，找出该单词是否存在于网格中。
        单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

        示例:
        board =
        [
        ['A','B','C','E'],
        ['S','F','C','S'],
        ['A','D','E','E']
        ]

        给定 word = "ABCCED", 返回 true
        给定 word = "SEE", 返回 true
        给定 word = "ABCB", 返回 false
         

        提示：
        board 和 word 中只包含大写和小写英文字母。
        1 <= board.length <= 200
        1 <= board[i].length <= 200
        1 <= word.length <= 10^3

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/word-search
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _79_单词搜索 {

    /**
     * 回溯算法
     * */
    public boolean exist(char[][] board, String word) {
        boolean[][]used = new boolean[board.length][board[0].length];
        char[]words = word.toCharArray();
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, words, used, 0, word.length(), i, j,rows, cols)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][]board, char[]words, boolean[][]used, int depth, int length, int i, int j, int rows, int cols){
        if (i < 0 || j < 0 || i >= rows || j >= cols || board[i][j] != words[depth] || used[i][j]) return false;
        if (depth == length - 1) return true;

        used[i][j] = true;
        boolean res = dfs(board, words, used,depth + 1, length, i - 1, j, rows, cols) ||
                dfs(board, words, used,depth + 1, length, i + 1, j, rows, cols) ||
                dfs(board, words, used,depth + 1, length, i, j - 1, rows, cols) ||
                dfs(board, words, used,depth + 1, length, i, j + 1, rows, cols);
        used[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        _79_单词搜索 cls = new _79_单词搜索();
        char[][]board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(cls.exist(board, "BFDEESCCE"));
    }
}
