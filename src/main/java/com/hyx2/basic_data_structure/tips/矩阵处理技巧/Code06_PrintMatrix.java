package com.hyx2.basic_data_structure.tips.矩阵处理技巧;

/**
 * @version 0.1
 * @Author hyx
 * @className Code04_PrintMatrix
 * @Date 2024/11/7  10:17
 * @description
 * @since jdk 11
 */
public class Code06_PrintMatrix {

    public static void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    private static void rotateEdge(int[][] m, int a, int b, int c, int d) {
        int temp = 0;
        for (int i = 0; i < d - b; i++) {
            temp = m[a][b + i];
            m[a][b + i] = m[c - i][b];
            m[c - i][b] = m[c][d - i];
            m[c][d - i] = m[a + i][d];
            m[a + i][d] = temp;
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; i != matrix[0].length; j++) {
                 System.out.print(matrix[i][j] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        rotate(matrix);
        printMatrix(matrix);
    }
}
