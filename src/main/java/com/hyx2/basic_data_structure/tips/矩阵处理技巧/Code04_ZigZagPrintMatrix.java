package com.hyx2.basic_data_structure.tips.矩阵处理技巧;

/**
 * @version 0.1
 * @Author hyx
 * @className Code04_ZigZagPrintMatrix
 * @Date 2024/11/6  23:52
 * @description zigzag 打印矩阵
 * @since jdk 11
 */
public class Code04_ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix) {
        int Ar = 0; // A 的行
        int Ac = 0; // A 的列
        int Br = 0; // B 的行
        int Bc = 0; // B 的列
        int Endr = matrix.length - 1;
        int Endc = matrix[0].length - 1;
        boolean fromUp = false; // 是不是从右上往左下打印
        while (Ar != Endr + 1) {
            printLevel(matrix, Ar, Ac, Br, Bc, fromUp);
            Ar = Ac == Endc ? Ar + 1 : Ar;  // A的列数如果到了最后一列，那么A的行号+1否则行号不变
            Ac = Ac == Endc ? Ac : Ac + 1;  // A的列数如果到了最后一列，那么列数不变，否则+1
            Bc = Br == Endr ? Bc + 1 : Bc;  // B的行数如果到了最后一行，那么B的列数+1，否则不变
            Br = Br == Endr ? Br : Br + 1;  // B的列数如果到了最后一行，那么B的行数不变，否则+1
            fromUp = !fromUp;
        }
        System.out.println();
    }

    private static void printLevel(int[][] matrix, int ar, int ac, int br, int bc, boolean fromUp) {
        if (fromUp) {
            while (ar != br + 1) {
                System.out.print(matrix[ar++][ac--] + " ");
            }
        } else {
            while (br != ar - 1) {
                System.out.print(matrix[br--][bc++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);
    }

}
