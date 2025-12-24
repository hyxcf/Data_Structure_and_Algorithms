package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;
/*
这道题的本质就是 bfs 求最短路径
 */
public class Leetcode_994_腐烂的橘子_12_24 {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0; // 新鲜橘子的个数
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { // 好橘子
                    count++;
                } else if (grid[i][j] == 2) {// 坏橘子
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int round = 0;
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] bad = queue.poll();
                assert bad != null;
                int r = bad[0];
                int c = bad[1];
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    count--;
                    queue.add(new int[]{r - 1, c});
                }
                if (r + 1 < m && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    count--;
                    queue.add(new int[]{r + 1, c});
                }
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    count--;
                    queue.add(new int[]{r, c - 1});
                }
                if (c + 1 < n && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    count--;
                    queue.add(new int[]{r, c + 1});
                }
            }
        }
        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }
}

/*
本题题解
    有了计算最短路径的层序 BFS 代码框架，写这道题就很简单了。
    这道题的主要思路是：
        一开始，我们找出所有腐烂的橘子，将它们放入队列，作为第 0 层的结点。
        然后进行 BFS 遍历，每个结点的相邻结点可能是上、下、左、右四个方向的结点，注意判断结点位于网格边界的特殊情况。
        由于可能存在无法被污染的橘子，我们需要记录新鲜橘子的数量。在 BFS 中，每遍历到一个橘子（污染了一个橘子），就将新鲜橘子的数量减一。如果 BFS 结束后这个数量仍未减为零，说明存在无法被污染的橘子。
*/
