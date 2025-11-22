package com.leetcode;

/**
 * 11_21 十一月二十一号
 * 73：力扣第73题
 */
public class 矩阵置零11_21_73 {

    /**
     * 原地算法
     * @param matrix
     */
    public void setZeros(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean flagCol = false, flagRow = false;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                flagCol = true;
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
            for (int i = 0; i < row; i++) {
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

}
