package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
在给定的网格中，每个单元格可以有以下三个值之一：

        值 0 代表空单元格；
        值 1 代表新鲜橘子；
        值 2 代表腐烂的橘子。
        每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。

        返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
        示例 1：
        输入：[[2,1,1],[1,1,0],[0,1,1]]
        输出：4
        示例 2：

        输入：[[2,1,1],[0,1,1],[1,0,1]]
        输出：-1
        解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
        示例 3：

        输入：[[0,2]]
        输出：0
        解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
         

        提示：

        1 <= grid.length <= 10
        1 <= grid[0].length <= 10
        grid[i][j] 仅为 0、1 或 2

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/rotting-oranges
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _994_腐烂的橘子 {

    /**
     *
     * BFS
     * 使用一个队列
     * 先把腐烂的橘子（的坐标）依次入队
     * 然后从队头开始，弹出一个腐烂的橘子，同时将其四周的好橘子腐蚀并入队，时间time+1
     * 依次进行直到队空，最后如果grid中还有好橘子则返回-1，否则返回time
     *
     * */
    public int orangesRotting(int[][] grid) {
        int[][] directions = {{0,-1},{0,1},{-1,0},{1,0}};

        int time = 0, freshCnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2){
                    queue.add(new int[]{i, j});
                }else if (grid[i][j] == 1){
                    freshCnt ++;
                }
            }
        }

        // 坏橘子队列不为空
        while (freshCnt > 0 && !queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 腐烂的橘子
                int[]point = queue.poll();
                int x = point[0], y = point[1];

                for (int j = 0; j < directions.length; j++) {
                    int newX = x + directions[j][0];
                    int newY = y + directions[j][1];

                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1){
                        grid[newX][newY] = 2;
                        queue.add(new int[]{newX, newY});
                        freshCnt --;
                    }
                }
            }
            time ++;
        }

        return freshCnt > 0 ? -1 : time;
    }

    public static void main(String[] args) {
        _994_腐烂的橘子 cls = new _994_腐烂的橘子();
        int[][]grid = {{2,1,1},{1,1,0},{0,1,1}};
        cls.orangesRotting(grid);
    }
}
