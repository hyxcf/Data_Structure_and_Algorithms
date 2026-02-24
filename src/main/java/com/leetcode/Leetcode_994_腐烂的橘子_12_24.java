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

    /*
        在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
        值 0 代表空单元格；
        值 1 代表新鲜橘子；
        值 2 代表腐烂的橘子。
        每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
        返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
        示例 1：
            输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
            输出：4
        示例 2：
            输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
            输出：-1
            解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
     */
    static class Preview_2_2 {
        public int orangesRotting(int[][] grid) {
            // 求最短路径
            int m = grid.length;
            int n = grid[0].length;
            int count = 0;
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        count++;
                    } else if (grid[i][j] == 2) {
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
                    int row = bad[0];
                    int col = bad[1];
                    if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                        grid[row - 1][col] = 2;
                        count--;
                        queue.offer(new int[]{row - 1, col});
                    }
                    if (row + 1 < m && grid[row + 1][col] == 1) {
                        grid[row + 1][col] = 2;
                        count--;
                        queue.offer(new int[]{row + 1, col});
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                        grid[row][col - 1] = 2;
                        count--;
                        queue.offer(new int[]{row, col - 1});
                    }
                    if (col + 1 < n && grid[row][col + 1] == 1) {
                        grid[row][col + 1] = 2;
                        count--;
                        queue.offer(new int[]{row, col + 1});
                    }
                }
            }
            // fixme:有新鲜的橘子没有被污染的时候，则返回-1
            if (count > 0) {
                return -1;
            } else {
                return round;
            }
        }
    }

    /*
        在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
        值 0 代表空单元格；
        值 1 代表新鲜橘子；
        值 2 代表腐烂的橘子。
        每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
        返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
        示例 1：
            输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
            输出：4
        示例 2：
            输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
            输出：-1
            解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
     */
    private static class Preview_2_24 {
        public int orangesRotting(int[][] grid) {
            // 这道题目要返回 直到单元格中没有新鲜橘子为止必须经过的最小分钟数。如果不可能，返回 -1
            int m = grid.length;
            int n = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            // 记录新鲜橘子的个数
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        count++;
                    } else if (grid[i][j] == 2) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }
            int result = 0;
            while (count > 0 && !queue.isEmpty()) {
                result++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] bad = queue.poll();
                    int row = bad[0];
                    int col = bad[1];
                    if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                        grid[row - 1][col] = 2;
                        count--;
                        queue.offer(new int[]{row - 1, col});
                    }
                    if (row + 1 < m && grid[row + 1][col] == 1) {
                        grid[row + 1][col] = 2;
                        count--;
                        queue.offer(new int[]{row + 1, col});
                    }
                    if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                        grid[row][col - 1] = 2;
                        count--;
                        queue.offer(new int[]{row, col - 1});
                    }
                    if (col + 1 < n && grid[row][col + 1] == 1) {
                        grid[row][col + 1] = 2;
                        count--;
                        queue.offer(new int[]{row, col + 1});
                    }
                }
            }
            if (count > 0) {
                return -1;
            } else {
                return result;
            }
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
