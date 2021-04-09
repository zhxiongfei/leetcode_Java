package 回溯算法;

import 二叉搜索树.面试题17_12_BiNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
在二维网格 grid 上，有 4 种类型的方格：

        1 表示起始方格。且只有一个起始方格。
        2 表示结束方格，且只有一个结束方格。
        0 表示我们可以走过的空方格。
        -1 表示我们无法跨越的障碍。
        返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。

        每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。

         

        示例 1：

        输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
        输出：2
        解释：我们有以下两条路径：
        1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
        2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
        示例 2：

        输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
        输出：4
        解释：我们有以下四条路径：
        1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
        2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
        3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
        4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
        示例 3：

        输入：[[0,1],[2,0]]
        输出：0
        解释：
        没有一条路能完全穿过每一个空的方格一次。
        请注意，起始和结束方格可以位于网格中的任意位置。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/unique-paths-iii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _980_不同路径_III {

    int res = 0;
    public int uniquePathsIII(int[][] grid) {
        // 找到起点，和需要经过的路径总和
        int startX = 0, startY = 0, totalPath = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0){
                    totalPath ++;
                    continue;
                }
                if (grid[i][j] == 1){
                    startX = i;
                    startY = j;
                }
            }
        }
        // 上下左右 四个方向
        int[][]directions = {{0,-1},{0,1},{-1,0},{1,0}};
        // 是否访问过数组
//        boolean[][]used = new boolean[grid.length][grid[0].length];
        // 走过的路径
        Stack<Integer[]>path = new Stack<>();
        // 存放路径结果的数组
        List<List<Integer[]>> resLists = new ArrayList<>();
        dfs(grid, directions, path, resLists, startX, startY, totalPath + 1, 0);
        return res;
    }

    private void dfs(int[][]grid, int[][]directions, Stack<Integer[]>path, List<List<Integer[]>> resLists, int i, int j, int totalPath, int depth){
        // 越界，直接返回
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;

        if (grid[i][j] == 2 && depth == totalPath){
            // 到终点, 且已路过所有 节点
            res ++;
            resLists.add(new ArrayList<>(path));
            return;
        }

        for (int k = 0; k < directions.length; k++) {
            // 已经路过，剪枝
//            if (used[i][j]) continue;
            // -1时阻挡， 剪枝
            if (grid[i][j] == -1) continue;
            // 已到达目的地， 但是没走完所有节点，剪枝
            if (grid[i][j] == 2 && depth != totalPath) continue;

            int[] dir = directions[k];

            // 修改状态
            int oriNum = grid[i][j];
            grid[i][j] = -1;
//            used[i][j] = true;
            depth ++;
            Integer[] tmp = new Integer[]{i, j};
            path.push(tmp);

            // 选择下一节点
            dfs(grid,directions, path, resLists, i+dir[0], j+dir[1], totalPath, depth);

            // 状态重置
//            used[i][j] = false;
            grid[i][j] = oriNum;
            path.pop();
            depth --;
        }
    }

    public static void main(String[] args) {
        _980_不同路径_III cls = new _980_不同路径_III();
        int[][]grid = {{1, 0, 0, 0},{0, 0, 0, 0},{0, 0, 2, -1}};
        cls.uniquePathsIII(grid);
        System.out.println(cls.res);
    }
}
