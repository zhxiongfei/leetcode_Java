package BFS;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。

        二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：

        路径途经的所有单元格都的值都是 0 。
        路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
        畅通路径的长度 是该路径途经的单元格总数。

         

        示例 1：


        输入：grid = [[0,1],[1,0]]
        输出：2
        示例 2：


        输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
        输出：4
        示例 3：

        输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
        输出：-1
         

        提示：

        n == grid.length
        n == grid[i].length
        1 <= n <= 100
        grid[i][j] 为 0 或 1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1091_二进制矩阵中的最短路径 {

    /**
    创建queue存放每一次走到的点坐标位置
    创建辅助数组dir存放每一个点往下走一步，会走到哪里，一共有8种可能。
    层序遍历queue中的坐标，将下一步满足条件的坐标存入queue,进行下一步遍历 到达终点记录走的步数
    */

    /**
        {0,0,0},
        {1,1,0},
        {1,1,0}}
    */
    public int shortestPathBinaryMatrix1(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        if (grid[0][0] == 1 || grid[rows-1][cols-1] == 1) return -1;

        // { 上 下 左 右 左上 左下 右上 右下 }
        int[][]directions = {{0,-1},{0,1},{-1,0},{1,0},{-1,-1},{-1,1},{1,-1},{1,1},};

        int step = 1;
        Queue<int[]>queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        grid[0][0] = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                if (point[0] == rows-1 && point[1] == cols-1) return step;

                for (int j = 0; j < directions.length; j++) {
                    int[] dir = directions[j];
                    int newX = point[0] + dir[0];
                    int newY = point[1] + dir[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 0){
                        queue.add(new int[]{newX,newY});
                        grid[newX][newY] = 1;
                    }
                }
            }
            step ++;
        }
        return -1;
    }

    int n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        if (n == 1) return 1;
        int[][] dir = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, 1}, {-1, -1}, {1, -1}, {1, 1}
        };
        Node start = new Node(0, 0, grid[0][0] = 1);
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int step = grid[node.x][node.y];
            for (int[] d : dir) {
                int x = node.x + d[0];
                int y = node.y + d[1];
                if (x == n - 1 && y == n - 1) return step + 1;
                if (x < 0 || x >= n || y < 0 || y >= n) continue;
                if (grid[x][y] != 0 && grid[x][y] <= step + 1) continue;
                Node next = new Node(x, y, grid[x][y] = step + 1);
                queue.offer(next);
            }
        }
        return -1;
    }

    class Node implements Comparable<Node> {
        int x;
        int y;
        int f;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            int distance = Math.max(n - 1 - x, n - 1 - y);
            this.f = distance + step;
        }

        @Override
        public int compareTo(Node o) {
            return this.f - o.f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(x * n + y);
        }
    }

    public static void main(String[] args) {
        _1091_二进制矩阵中的最短路径 cls = new _1091_二进制矩阵中的最短路径();
        int[][]matrix = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(cls.shortestPathBinaryMatrix(matrix));
    }
}
