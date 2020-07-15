package DFS;

/**
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

        [["a","b","c","e"],
        ["s","f","c","s"],
        ["a","d","e","e"]]

        但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

         

        示例 1：

        输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        输出：true
        示例 2：

        输入：board = [["a","b"],["c","d"]], word = "abcd"
        输出：false
        提示：

        1 <= board.length <= 200
        1 <= board[i].length <= 200

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _剑指_Offer12矩阵中的路径 {

    /**
     *
     * dfs 深度优先搜索
     *
     * 因为每一个路径都可以是起点，所以最外层需要两层循环。 以每个点开始为起点 dfs 查找
     * 因为 可以👆，👇，👈，👉四个放下走， 所以用 i， j 模拟四个方向走路
     * i - 1, j 代表 👈
     * i + 1, j 代表 👉
     * i, j - 1 代表 👆
     * i, j + 1 代表 👇
     *
     *
     * dfs 过程中
     *  如果 i < 0 || j < 0 || i >= m || j >= n 说明走出矩形区域。 return false
     *  如果 borad[i][j] != words[idx] 说明走不通，return false
     *
     *  否则, 进入下一次 dfs.
     *  因为走过的节点，不能重复走, 所以开始我们的想法是记录 visited 数组记录走过的节点
     *  题解中看到一种巧妙的方法, 直接修改原数组board, 访问过 board[i][j] == '0'. 因为 是 字符串中是字母，就不可能与 board[i][j] 相等
     *  相当于模拟了 visited数组，但是要问清楚，是否允许修改原矩形的数据, 如果不允许, 还得老老实实使用 visit 数组
     *
     *  ⚠️⚠️⚠️ 关键点 回溯
     *  当 此条路径不通时, 我们需要把路过此路径的节点值，重新置回原来的值⚠️.
     *  题解中非常巧妙的使用了一种方法, 记录临时变量, 放路径不通时, 将 board[i][j] 回复为 记录的临时变量
     *
     * */
    public boolean dfs(int i, int j, int m, int n,int idx, char[][] board, char[] words){
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != words[idx]) return false;

        char tmp = board[i][j];
        board[i][j] = '0';
        if (idx == words.length - 1) return true;

        boolean res =
                dfs(i + 1, j, m, n, idx + 1, board, words) ||
                dfs(i - 1, j, m, n, idx + 1, board, words) ||
                dfs(i, j + 1, m, n, idx + 1, board, words) ||
                dfs(i, j - 1, m, n, idx + 1, board, words);
        if (res) return true;

        // 这一步很重要 ⚠️⚠️⚠️
        // 没走通，状态重置, 回溯,
        board[i][j] = tmp;
        return false;
    }

    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                boolean exist = dfs(i,j, board.length, board[0].length, 0,board, word.toCharArray());
                if (exist) return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};

        _剑指_Offer12矩阵中的路径 cls = new _剑指_Offer12矩阵中的路径();
        boolean exist = cls.exist(board, "ABCESEEEFS");
        System.out.println(exist);
    }

}
