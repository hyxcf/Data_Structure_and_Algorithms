package com.leetcode;

import com.hyx.graph.Dfs;

import java.util.List;

public class Leetcode_79_单词搜索_1_21 {
    
    /*
        给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
        单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
        示例 1：
            输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
            输出：true
        示例 2：
            输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
            输出：true
     */

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int start, boolean[][] visited) {
        // 首先确立剪枝条件
        if (start == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || word.charAt(start) != board[i][j] || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (dfs(board, word, i, j + 1, start + 1, visited) || dfs(board, word, i, j - 1, start + 1, visited) || dfs(board, word, i - 1, j, start + 1, visited) || dfs(board, word, i + 1, j, start + 1, visited)) {
            return true;
        } else {
            visited[i][j] = false;
            return false;
        }
    }

    static class Preview_2_1 {
        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0 || board[0].length == 0) {
                return false;
            }
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (backtrack(0, i, j, board, word, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean backtrack(int index, int i, int j, char[][] board, String word, boolean[][] visited) {
            if (index == word.length()) {
                return true;
            }
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || word.charAt(index) != board[i][j]) {
                return false;
            }
            visited[i][j] = true;
            if (backtrack(index + 1, i - 1, j, board, word, visited)
                    || backtrack(index + 1, i + 1, j, board, word, visited)
                    || backtrack(index + 1, i, j + 1, board, word, visited)
                    || backtrack(index + 1, i, j - 1, board, word, visited)) {
                return true;
            } else {
                visited[i][j] = false;
                return false;
            }
        }
    }

    private static class Preview_2_27 {
        /*
            给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
            单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
            示例 1：
                输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
                输出：true
            示例 2：
                输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
                输出：true
         */
        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0 || board[0].length == 0) {
                return false;
            }
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (backtracking(0, i, j, board, word, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean backtracking(int index, int i, int j, char[][] board, String word, boolean[][] visited) {
            if (index == word.length()) {
                return true;
            }
            if (i < 0 || j < 0 || j >= board[0].length || visited[i][j] || word.charAt(index) != board[i][j]) {
                return false;
            }
            visited[i][j] = true;
            if (backtracking(index + 1, i - 1, j, board, word, visited)
                    || backtracking(index + 1, i + 1, j, board, word, visited)
                    || backtracking(index + 1, i, j + 1, board, word, visited)
                    || backtracking(index + 1, i, j - 1, board, word, visited)) {
                return true;
            } else {
                visited[i][j] = false;
                return false;
            }
        }
    }

}
