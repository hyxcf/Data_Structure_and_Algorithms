package com.leetcode;

public class Leetcode_200_岛屿数量_12_24 {
    //利用深度递归解决，可以看图，并加记住这个模板，他可以解决岛屿中的问题，还有一题岛屿面积问题也是这个模板。
    public int numIslands(char[][] grid) {
        //定义一个表示岛屿数量的变量
        int count = 0;
        //这个两层for循环是用来遍历整张二维表格中所有的陆地
        //其中 i 表示行，j 表示列
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //取出所有的陆地
                if (grid[i][j] == '1') {
                    //深度递归，遍历所有的陆地
                    dfs(grid, i, j);
                    //用来统计有多少岛屿，岛屿是由多个陆地组成的，概念不一样
                    count++;
                }
            }
        }
        //返回岛屿的数量
        return count;
    }

    public void dfs(char[][] grid, int i, int j) {
        //防止 i 和 j 越界，也就是防止超出岛屿（上下左右）的范围。特别注意当遍历到海洋的时候也退出循环
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        //将遍历过的陆地改为海洋，防止重复遍历
        grid[i][j] = '0';
        //下
        dfs(grid, i + 1, j);
        //上
        dfs(grid, i - 1, j);
        //右
        dfs(grid, i, j + 1);
        //左
        dfs(grid, i, j - 1);
    }

    static class Preview_2_2 {
        /*
            给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
            岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
            此外，你可以假设该网格的四条边均被水包围。
            示例 1：
                输入：grid = [
                  ['1','1','1','1','0'],
                  ['1','1','0','1','0'],
                  ['1','1','0','0','0'],
                  ['0','0','0','0','0']
                ]
                输出：1
            示例 2：
                输入：grid = [
                  ['1','1','0','0','0'],
                  ['1','1','0','0','0'],
                  ['0','0','1','0','0'],
                  ['0','0','0','1','1']
                ]
                输出：3
         */
        public int numIslands(char[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) return 0;
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        dfs(i, j, grid);
                        count++;
                    }
                }
            }
            return count;
        }

        private void dfs(int i, int j, char[][] grid) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            dfs(i - 1, j, grid);
            dfs(i + 1, j, grid);
            dfs(i, j - 1, grid);
            dfs(i, j + 1, grid);
        }

    }

}