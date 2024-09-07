package com.hyx.recursion;

/**
 * hyx
 * .
 *
 * @author 32596
 */
public class E01PascalTriangle2 {
    /**
     * <h3>优化2 - 使用一维数组记忆法</h3>
     */
    private static void createRow(int[] row, int i) {
        if (i == 0) {
            row[0] = 1;
            return;
        }
        for (int j = i; j > 0; j--) {
            row[j] = row[j] + row[j - 1];
        }
    }

    /**
     * @param n 杨辉三角的高度
     *          记忆法
     */
    public static void printInfo2(int n) {
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {
            createRow(row, i);
//            printSpace2(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.print("   " + row[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printInfo2(4);
    }
}
