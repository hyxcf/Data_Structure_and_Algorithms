package com.hyx2.basic_data_structure.class05_dynamic_plan;

/**
 * @author hyx
 * @version 0.1
 * @className Code02_Knapsack_improve
 * @date 2025/2/11 0:04
 * @description 背包问题的动态规划版本
 * @since jdk 11
 */
public class Code02_Knapsack_improve {

    private static int maxValue(int[] w, int[] v, int bag) {
        return dpway(w, v, bag);
    }

    private static int dpway(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - w[index] >= 0) {
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(dpway(weights, values, bag));
    }

}
