package com.hyx.leetcode.greedy;

/**
 * @version 0.1
 * @Author hyx
 * @className E02Leetcode322
 * @Date 2024/10/19  19:34
 * @description
 * @since jdk 11
 */

/**
 * 零钱兑换
 * 贪心解法：可能得到错误的解
 */
/*
    总金额 18
    5   2   1

    3 * 5  3
    1 * 2  1
    1 * 1  0

    贪心的原则：
        1、每一次都选取当前最优解
        2、《向前看，别回头》

    几个有问题的情况
    总金额 21
    15  10  1
    7个硬币
    1 * 15
    6 * 1

    3个硬币
    2 * 10
    1 * 1

    总金额  20
    15    10
    1*15  5     无解
 */
public class E02Leetcode322 {
    public int coinChange(int[] coins, int amount) {
        // 每次循环找到当前最优解：面值最大的硬币，他凑出的硬币数最小
        int remainder = amount; // 18
        int count = 0;
        for (int coin : coins) { // 5
            while (remainder > coin) {
                remainder -= coin;
                count++;
            }
            if (remainder == coin){
                remainder = 0;
                count++;
                break;
            }
        }
        if (remainder > 0){
            return -1;
        }else {
            return count;
        }
    }

    public static void main(String[] args) {
        E02Leetcode322 leetcode322 = new E02Leetcode322();
//        int count = leetcode322.coinChange(new int[]{5, 2, 1}, 18);
        // 问题1 没有回头，导致找到更差的解
//        int count = leetcode322.coinChange(new int[]{15, 10, 1}, 21);
        // 问题2 没有回头，导致无解
        int count = leetcode322.coinChange(new int[]{15, 10}, 20);
        System.out.println(count);
    }

}
