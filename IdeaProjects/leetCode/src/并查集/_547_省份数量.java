package 并查集;

import java.util.LinkedList;
import java.util.Queue;

/**
有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

        省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

        给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

        返回矩阵中 省份 的数量。

         

        示例 1：


        输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
        输出：2
        示例 2：


        输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
        输出：3
         

        提示：

        1 <= n <= 200
        n == isConnected.length
        n == isConnected[i].length
        isConnected[i][j] 为 1 或 0
        isConnected[i][i] == 1
        isConnected[i][j] == isConnected[j][i]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/number-of-provinces
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _547_省份数量 {

    /**
     * 深度优先搜索
     * */
    public int findCircleNumDfs(int[][] isConnected) {
        // int[] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
        int n = isConnected.length;
        // 是否访问过
        boolean[] visited = new boolean[n];
        // 定义cnt来累计遍历过的联通域的数量
        int cnt = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (visited[i]) continue;
            dfs(isConnected,visited,i);
        }
        return cnt;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i){
        visited[i] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]){
                dfs(isConnected, visited, j);
            }
        }
    }

    /**
     * 广度优先搜索
     */
    public int findCircleNumBfs(int[][] isConnected) {
        // int[] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
        int n = isConnected.length;
        // 是否访问过
        boolean[] visited = new boolean[n];
        // 定义cnt来累计遍历过的联通域的数量
        int cnt = 0;
        Queue<Integer>queue = new LinkedList<>();
        for (int i = 0; i < isConnected.length; i++) {
            if (visited[i]) continue;
            cnt ++;
            queue.offer(i);
            visited[i] = true;
            while (!queue.isEmpty()){
                int v = queue.poll();
                for (int w = 0; w < n; w++) {
                    if (isConnected[v][w] == 1 && !visited[w]) {
                        visited[w] = true;
                        queue.offer(w);
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * 并查集
     * */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n;j++){
                if (isConnected[i][j] == 1){
                    uf.union(i,j);
                }
            }
        }
        return uf.size;
    }

        // 并查集
    class UnionFind {
        int[] roots;
        int size; // 集合数量
        public UnionFind(int n) {
            roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
            }
            size = n;
        }

        public int find(int i) {
            if (i == roots[i]) {
                return i;
            }
            return roots[i] = find(roots[i]);
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                roots[pRoot] = qRoot;
                size--;
            }
        }
    }

    public static void main(String[] args) {
        _547_省份数量 cls = new _547_省份数量();
        int[][]isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        cls.findCircleNum(isConnected);
    }
}
