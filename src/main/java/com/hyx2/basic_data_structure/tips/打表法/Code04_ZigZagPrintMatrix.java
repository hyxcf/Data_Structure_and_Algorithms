package com.hyx2.basic_data_structure.tips.打表法;

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
            Ar = Ac == Endc ? Ar + 1 : Ar;
            Ac = Ac == Endc ? Ac : Ac + 1;
            Bc = Br == Endr ? Bc + 1 : Bc;
            Br = Br == Endr ? Br : Br + 1;
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
