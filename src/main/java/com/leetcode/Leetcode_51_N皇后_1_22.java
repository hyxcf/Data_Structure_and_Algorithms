package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_51_N皇后_1_22 {

    /*
        按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
        n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
        给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
        每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
        示例 1：     
            输入：n = 4
            输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        解释：如上图所示，4 皇后问题存在两个不同的解法。
        示例 2：
            输入：n = 1
            输出：[["Q"]]
            
        行、列、对角线（主对角线、副对角线）都不能用多余的皇后
        如何判断 主副对角线 是否有皇后
        [
        [0,0][0,1][0,2][0,3]
        [1,0][1,1][1,2][1,3]
        [2,0][2,1][2,2][2,3]
        [3,0][3,1][3,2][3,3]
        ]
        主对角线：i-j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
        副对角线：i+j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        // 初始化棋盘为 .
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        boolean[] cols = new boolean[n];// 检查列是否重复
        boolean[] dig1 = new boolean[2 * n - 1];// 主对角线
        boolean[] dig2 = new boolean[2 * n - 1];// 副对角线
        backtrack(0, n, cols, dig1, dig2, board, ans);
        return ans;
    }

    private void backtrack(int row, int n, boolean[] cols, boolean[] dig1, boolean[] dig2, char[][] board, List<List<String>> ans) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (char[] r : board) {
                list.add(new String(r));
            }
            ans.add(list);
            return;
        }
        for (int col = 0; col < n; col++) {
            // d1 是主对角线的唯一编号
            int d1 = row - col + n - 1; // 防止负数索引
            // d2 是副对角线的唯一编号
            int d2 = row + col;
            if (cols[col] || dig1[d1] || dig2[d2]) {
                continue; // 冲突，跳过
            }
            // 放置皇后
            board[row][col] = 'Q';
            cols[col] = true;
            dig1[d1] = true;
            dig2[d2] = true;

            backtrack(row + 1, n, cols, dig1, dig2, board, ans);

            // 回溯
            board[row][col] = '.';
            cols[col] = false;
            dig1[d1] = false;
            dig2[d2] = false;
        }

    }

    private static class Preview_2_1 {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            char[][] board = new char[n][n];
            for (char[] chars : board) {
                Arrays.fill(chars, '.');
            }
            boolean[] col = new boolean[n];
            boolean[] dig1 = new boolean[2 * n - 1];// 主对角线
            boolean[] dig2 = new boolean[2 * n - 1];// 副对角线
            backtrack(0, n, col, dig1, dig2, board, res);
            return res;
        }

        private void backtrack(int row, int n, boolean[] cols, boolean[] dig1, boolean[] dig2, char[][] board, List<List<String>> res) {
            if (row == n) {
                List<String> temp = new ArrayList<>();
                for (char[] r : board) {
                    temp.add(String.valueOf(r));
                }
                res.add(temp);
                return;
            }
            for (int col = 0; col < n; col++) {
                // 主对角线
                int d1 = row - col + n - 1;
                // 副对角线
                int d2 = row + col;
                if (cols[col] || dig1[d1] || dig2[d2]) {
                    continue;
                }
                cols[col] = true;
                dig1[d1] = true;
                dig2[d2] = true;
                board[row][col] = 'Q';
                backtrack(row + 1, n, cols, dig1, dig2, board, res);
                board[row][col] = '.';
                cols[col] = false;
                dig1[d1] = false;
                dig2[d2] = false;
            }
        }
    }

    private static class Preview_2_27 {
        /*
            按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
            n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
            给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
            每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
            示例 1：
                输入：n = 4
                输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
            解释：如上图所示，4 皇后问题存在两个不同的解法。
            示例 2：
                输入：n = 1
                输出：[["Q"]]

            行、列、对角线（主对角线、副对角线）都不能用多余的皇后
            如何判断 主副对角线 是否有皇后
            [
            [0,0][0,1][0,2][0,3]
            [1,0][1,1][1,2][1,3]
            [2,0][2,1][2,2][2,3]
            [3,0][3,1][3,2][3,3]
            ]
            主对角线：i-j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
            副对角线：i+j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
         */
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            char[][] board = new char[n][n];
            for (char[] chars : board) {
                Arrays.fill(chars, '.');
            }
            boolean[] cols = new boolean[n];
            boolean[] dig1 = new boolean[2 * n - 1];
            boolean[] dig2 = new boolean[2 * n - 1];
            backtracking(0, n, cols, dig1, dig2, board, res);
            return res;
        }

        private void backtracking(int row, int n, boolean[] cols, boolean[] dig1, boolean[] dig2, char[][] board, List<List<String>> res) {
            if (row == n) {
                List<String> temp = new ArrayList<>();
                for (char[] r : board) {
                    temp.add(String.valueOf(r));
                }
                res.add(temp);
                return;
            }
            for (int col = 0; col < n; col++) {
                // 主对角线
                int d1 = row - col + n - 1;
                // 副对角线
                int d2 = row + col;
                if (cols[col] || dig1[d1] || dig2[d2]) {
                    continue;
                }
                cols[col] = true;
                dig1[d1] = true;
                dig2[d2] = true;
                board[row][col] = 'Q';
                backtracking(row + 1, n, cols, dig1, dig2, board, res);
                board[row][col] = '.';
                cols[col] = false;
                dig1[d1] = false;
                dig2[d2] = false;
            }
        }
    }

    private static class Preview_3_9 {
        /*
            按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
            n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
            给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
            每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
            示例 1：
                输入：n = 4
                输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
            解释：如上图所示，4 皇后问题存在两个不同的解法。
            示例 2：
                输入：n = 1
                输出：[["Q"]]

            行、列、对角线（主对角线、副对角线）都不能用多余的皇后
            如何判断 主副对角线 是否有皇后
            [
            [0,0][0,1][0,2][0,3]
            [1,0][1,1][1,2][1,3]
            [2,0][2,1][2,2][2,3]
            [3,0][3,1][3,2][3,3]
            ]
            主对角线：i-j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
            副对角线：i+j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
         */
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            char[][] board = new char[n][n];
            for (char[] chars : board) {
                Arrays.fill(chars, '.');
            }
            boolean[] cols = new boolean[n];
            boolean[] dig1 = new boolean[2 * n - 1];
            boolean[] dig2 = new boolean[2 * n - 1];
            backtracking(0, n, cols, dig1, dig2, board, res);
            return res;
        }

        private void backtracking(int row, int n, boolean[] cols, boolean[] dig1, boolean[] dig2, char[][] board, List<List<String>> res) {
            if (row == n) {
                List<String> temp = new ArrayList<>();
                for (char[] chars : board) {
                    temp.add(String.valueOf(chars));
                }
                res.add(temp);
                return;
            }
            for (int col = 0; col < n; col++) {
                // 求对角线的值
                // 主对角线
                int d1 = row - col + n - 1;
                // 副对角线
                int d2 = row + col;
                if (cols[col] || dig1[d1] || dig2[d2]) {
                    continue;
                }
                cols[col] = true;
                dig1[d1] = true;
                dig2[d2] = true;
                board[row][col] = 'Q';
                backtracking(row + 1, n, cols, dig1, dig2, board, res);
                board[row][col] = '.';
                cols[col] = false;
                dig1[d1] = false;
                dig2[d2] = false;
            }
        }
    }

    private static class Preview_3_17 {
        /*
            按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
            n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
            给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
            每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
            示例 1：
            输入：n = 4
            输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
            解释：如上图所示，4 皇后问题存在两个不同的解法。
            示例 2：
            输入：n = 1
            输出：[["Q"]]

            行、列、对角线（主对角线、副对角线）都不能用多余的皇后
            如何判断 主副对角线 是否有皇后
            [
            [0,0][0,1][0,2][0,3]
            [1,0][1,1][1,2][1,3]
            [2,0][2,1][2,2][2,3]
            [3,0][3,1][3,2][3,3]
            ]
            主对角线：i-j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
            副对角线：i+j 都是相等的 最大值 3 最小值 -3 所需空间2n-1
        */
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            char[][] board = new char[n][n];
            for (char[] chars : board) {
                Arrays.fill(chars, '.');
            }
            boolean[] cols = new boolean[n];
            boolean[] dig1 = new boolean[2 * n - 1];
            boolean[] dig2 = new boolean[2 * n - 1];
            backtracking(0, n, cols, dig1, dig2, board, res);
            return res;
        }

        private void backtracking(int row, int n, boolean[] cols, boolean[] dig1, boolean[] dig2, char[][] board, List<List<String>> res) {
            if (n == row) {
                List<String> temp = new ArrayList<>();
                for (char[] chars : board) {
                    temp.add(String.valueOf(chars));
                }
                res.add(temp);
                return;
            }
            for (int col = 0; col < n; col++) {
                // 求对角线
                // 主对角线
                int d1 = row - col + n - 1;
                // 副对角线
                int d2 = row + col;
                if (cols[col] || dig1[d1] || dig2[d2]) {
                    continue;
                }
                cols[col] = true;
                dig1[d1] = true;
                dig2[d2] = true;
                board[row][col] = 'Q';
                backtracking(row + 1, n, cols, dig1, dig2, board, res);
                board[row][col] = '.';
                cols[col] = false;
                dig1[d1] = false;
                dig2[d2] = false;
            }
        }
    }

}
