package com.hyx2.basic_data_structure.class04_recur;

/**
 * @author hyx
 * @version 0.1
 * @className Code09_NQueens
 * @date 2025/2/7 14:47
 * @description 位运算
 * @since jdk 11
 */
public class Code09_NQueens {

    /**
     * 用数组来表示n皇后的行和列
     * 下标是 表格的行， 数组中的值是表格中的列
     *
     * @param n n 个皇后
     * @return 多少种解法
     */
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // record[0] ? record[1] ? record[2]
        int[] record = new int[n];
        return process(0, record, n);
    }

    private static int process(int i, int[] record, int n) {
        if (i == n) { // 终止行
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) { // 当前行在i行，尝试i行所有的列 -> j
            // 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行共列或者共斜线，
            // 如果是，认为有效
            // 如果不是，认为无效
            if (isValid(record, i, j)) { // i是行，j是列
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    // record[0...i-1] 你需要看

    /**
     * 只需要判断列和斜线是否相等
     *
     * @param record n皇后的数组
     * @param i      行
     * @param j      列
     * @return 是否有效
     */
    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) { // 当前 k 行
            // 判断列是否相等          判断斜线是否相等 |行-行| = |列-列|
            if (record[k] == j || Math.abs(j - record[k]) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(num1(8));
    }

}
