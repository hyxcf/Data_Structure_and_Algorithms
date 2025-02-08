package com.hyx2.basic_data_structure.class05_dynamic_plan;

/**
 * @author hyx
 * @version 0.1
 * @className Code01_RobotWalk
 * @date 2025/2/8 13:40
 * @description 假设有排成一行的N个位置，记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置;
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置;
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走，
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 * @since jdk 11
 */
public class Code01_RobotWalk {

    /**
     * 动态规划 - 记忆化搜索
     */
    public static int waysCache(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P < N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= K; col++) {
                dp[row][col] = -1;
            }
        }
        return walkCache(N, M, K, P, dp);
    }

    /**
     * 本质上就是走了缓存，避免了重复计算，用哈希表也行。
     */
    private static int walkCache(int N, int cur, int rest, int P, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) {
            dp[cur][rest] = walk1(N, 2, rest - 1, P);
            return dp[cur][rest];
        }
        if (cur == P) {
            dp[cur][rest] = walk1(N, P - 1, rest - 1, P);
            return dp[cur][rest];
        }
        // 中间的位置
        dp[cur][rest] = walk1(N, cur + 1, rest - 1, P) + walk1(N, cur - 1, rest - 1, P);
        return dp[cur][rest];
    }


    /**
     * 这个方法是暴力递归，这个重复的次数较高
     *
     * @param N N个位置
     * @param M cur 位置
     * @param K 走 K 步
     * @param P final position
     * @return steps
     */
    public static int ways1(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P < N) {
            return 0;
        }
        return walk1(N, M, K, P);
    }

    private static int walk1(int N, int cur, int rest, int P) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        if (cur == 1) {
            return walk1(N, 2, rest - 1, P);
        }
        if (cur == P) {
            return walk1(N, P - 1, rest - 1, P);
        }
        // 中间的位置
        return walk1(N, cur + 1, rest - 1, P) + walk1(N, cur - 1, rest - 1, P);
    }

}
