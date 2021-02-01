package DFS;

import java.util.Stack;

/**
你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

        一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

        请你返回从左上角走到右下角的最小 体力消耗值 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/path-with-minimum-effort
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/// TODO:未完成
public class _1631_最小体力消耗路径 {

    int res = Integer.MIN_VALUE;
    public int minimumEffortPath(int[][] heights) {

        boolean[][] visited = new boolean[heights.length][heights[0].length];
        Stack<Integer>path = new Stack<>();
        dfs(0,0,visited,heights, path, 0, Integer.MIN_VALUE);

        return res;
    }

    private void dfs(int i,int j, boolean[][] visited, int[][]heights, Stack<Integer>path, int prevHeight, int cutMax){
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length || visited[i][j]) return;

        visited[i][j] = true;

        int curHeight = heights[i][j];
        int tmp = Math.abs(curHeight - prevHeight);
        if (tmp > cutMax) cutMax = tmp;

        path.add(curHeight);
        if (i == heights.length - 1 && j == heights[0].length - 1) {
            res = Math.max(res, cutMax);
            return;
        }

        dfs(i - 1, j, visited, heights, path, curHeight, cutMax);
        dfs(i + 1, j, visited, heights, path, curHeight, cutMax);
        dfs(i, j - 1, visited, heights, path, curHeight, cutMax);
        dfs(i, j + 1, visited, heights, path, curHeight, cutMax);

        path.remove(curHeight);
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        _1631_最小体力消耗路径 cls = new _1631_最小体力消耗路径();
        int[][]heights = {{1,2,2},{3,8,2},{5,3,5}};
        int res = cls.minimumEffortPath(heights);
        System.out.println(res);
    }
}
