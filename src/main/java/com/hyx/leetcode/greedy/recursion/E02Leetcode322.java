package com.hyx.leetcode.greedy.recursion;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 0.1
 * @Author hyx
 * @className E02Leetcode322
 * @Date 2024/10/19  10:37
 * @description
 * @since jdk 11
 */
/*
    零钱兑换
    凑成总金额的凑法中，需要硬币最少个数是几？
 */
public class E02Leetcode322 {

    static int min = -1; // 需要的最少硬币数

    public int change(int[] coins, int amount) {
        rec(0, coins, amount, new AtomicInteger(-1));
        return min;
    }

    /**
     * 求凑成剩余金额的解的个数
     *
     * @param index     当前硬币索引
     * @param coins     硬币面值数组
     * @param remainder 剩余金额
     * @param count     代表某一组合钱币的总数
     */
    private void rec(int index, int[] coins, int remainder, AtomicInteger count) {
        // 情况1：剩余金额 > 0 - 继续递归
        // 情况2：剩余金额 = 0 - 有解
        count.incrementAndGet();// count++; 为了正确的计数
        if (remainder == 0) {
            if (min == -1) {
                min = count.get();
            } else {
                min = Integer.min(min, count.get());
            }
        } else if (remainder > 0){
            for (int i = index; i < coins.length; i++) {
                rec(i, coins, remainder - coins[i], count);
            }
        }
        count.decrementAndGet(); // count--; 为了正确的计数
    }

    public static void main(String[] args) {
        E02Leetcode322 leetcode322 = new E02Leetcode322();
        int count = leetcode322.change(new int[]{5, 2, 1}, 5);
        System.out.println(count);
    }

}
