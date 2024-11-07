package com.hyx2.basic_data_structure.tips.矩阵处理技巧;

/**
 * @version 0.1
 * @Author hyx
 * @className Code05_PrintMatrixSpiralOrder
 * @Date 2024/11/7  9:49
 * @description 转圈打印矩阵
 * @since jdk 11
 */
public class Code05_PrintMatrixSpiralOrder {


    public static void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] m, int a, int b, int c, int d) {
        // 左上角点 和 右下角点 在同一行
        if (a == c) {
            for (int i = b; i <= d; i++) {
                System.out.print(m[a][i] + " ");
            }
            // 左上角点 和 左下角点 在同一列
        } else if (b == d) {
            for (int i = a; i <= c; i++) {
                System.out.print(m[i][b] + " ");
            }
        } else {
            int curC = b;
            int curR = a;
            while (curC != d) {
                System.out.print(m[a][curC++] + " ");
            }
            while (curR != c) {
                System.out.print(m[curR++][d] + " ");
            }
            while (curC != b) {
                System.out.print(m[c][curC--] + " ");
            }
            while (curR != a) {
                System.out.print(m[curR--][b] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        spiralOrderPrint(matrix);
    }
}
