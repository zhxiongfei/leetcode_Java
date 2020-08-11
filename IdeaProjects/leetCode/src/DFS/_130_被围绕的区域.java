package DFS;

/**
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

        找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

        示例:

        X X X X
        X O O X
        X X O X
        X O X X
        运行你的函数后，矩阵变为：

        X X X X
        X X X X
        X X X X
        X O X X
        解释:

        被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/surrounded-regions
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _130_被围绕的区域 {

    /**
     *
     * 深度优先搜索
     *
     * 本题跟定的矩阵中有三种元素 :
     * 字母 X
     * 被字母 X 包围的字母 O
     * 没有被字母 X 包围的字母 O
     *
     * 本题要求将搜有的字母 X 包围的 O 变为 X， 但很难判断哪些O被包围，哪些O不被包围
     *
     * 注意到题木中提到 : 任何边界上的 O 都不会填充为 X。 可以想到, 所有的不被包围的 O 都直接或间接与边界上的 O 相连
     * 我们可以利用这个性质判断 O 是否在边界上。
     *
     * 对于边界上的 O , 我们以它为起点, 标记所有与它直接或间接相连的字母 O;
     * 如果该字母没有被标记, 则该字母为被 字母 X 包围的字母 O. 将其修改为字母 X;
     *
     * */
    int m, n;
    public void dfs(char[][] board, int i, int j){
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') return;

        board[i][j] = 'A';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int i = 0; i < n; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') board[i][j] = 'O';
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public static void main(String[] args) {
        char[][] board =
                {{'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}};

        _130_被围绕的区域 cls = new _130_被围绕的区域();
        cls.solve(board);
    }

}
