package com.leetcode;

/**
 * 11_21 十一月二十一号
 * 73：力扣第73题
 */
public class Leetcode_73_矩阵置零_11_21 {

    /**
     * 原地算法
     *
     * @param matrix
     */
    public void setZeros(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean flagCol = false, flagRow = false;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                flagCol = true; // 这里和下面要注意到底是行为0还是列为0
            }
        }
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                flagRow = true;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol) {
            for (int i = 0; i < row; i++) {  // 在这里和下面的要注意 flagCol、flagRwo、row、col 的用法
                matrix[i][0] = 0;
            }
        }
        if (flagRow) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
    }


    /**
     * 简易算法
     */
    public void setZeros2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[] r = new boolean[row];
        boolean[] l = new boolean[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 标记数组记录出现0的行和列
                if (matrix[i][j] == 0) {
                    r[i] = true;
                    l[j] = true;
                }
            }
        }
        // 更新矩阵
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (r[i] || l[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static class Preview_3_17 {
        /*
        给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
        示例 1：
            输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
            输出：[[1,0,1],[0,0,0],[1,0,1]]
        示例 2：
            输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
            输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
         */
        public void setZeros(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            boolean[] r = new boolean[row];
            boolean[] l = new boolean[col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // 标记数组记录出现0的行和列
                    if (matrix[i][j] == 0) {
                        r[i] = true;
                        l[j] = true;
                    }
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (r[i] || l[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        public void setZeros2(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            boolean flagCol = false, flagRow = false;
            for (int i = 0; i < row; i++) {
                if (matrix[i][0] == 0){
                    flagCol = true;
                }
            }
            for (int i = 0; i < col; i++) {
                if (matrix[0][i] == 0){
                    flagRow = true;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == 0){
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0){
                        matrix[i][j] = 0;
                    }
                }
            }
            if (flagCol){
                for (int i = 0; i < row; i++) {
                    matrix[i][0] = 0;
                }
            }
            if (flagRow){
                for (int i = 0; i < col; i++) {
                    matrix[0][i] = 0;
                }
            }
        }

    }

}
