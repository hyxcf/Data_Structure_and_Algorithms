package com.hyx.recursion;

/**
 * hyx
 * 杨辉三角
 *
 * @author 32596
 */
public class E01PascalTriangle {

    private static int element(int i, int j) {
        if (j == 0 || i == j) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);
    }



    private static void printSpace(int n, int i) {
        int num = (n - 1 - i) * 2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    /**
     * @param n 杨辉三角的高度
     */
    public static void printInfo(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.print("   " + element(i, j));
            }
            System.out.println();
        }
    }

    // 记忆法
    private static int element2(int[][] triangle, int i, int j) {
        if (triangle[i][j] > 0){
            return triangle[i][j];
        }
        if (j == 0 || i == j) {
            triangle[i][j] = 1;
            return 1;
        }
        triangle[i][j] = element2(triangle, i - 1, j - 1) + element2(triangle, i - 1, j);
        return triangle[i][j];
    }

    // 记忆法
    private static void printSpace2(int n, int i) {
        int num = (n - 1 - i) * 2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    /**
     * @param n 杨辉三角的高度
     *          记忆法
     */
    public static void printInfo2(int n) {
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
            printSpace2(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.print("   " + element2(triangle, i, j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        System.out.println(element(4, 2));
        printInfo2(10);
    }

}
