package com.hyx2.basic_data_structure.class04_recur;

/**
 * @author hyx
 * @version 0.1
 * @className Code07_Knapsack
 * @date 2025/2/5 20:39
 * @description 从左往右的尝试模型2 - 背包问题
 * - 给定两个长度都为N的数组weights和values,
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * @since jdk 11
 */
public class Code07_Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);
    }

    /**
     * @param w        重量
     * @param v        价值
     * @param index    第 index 个物品
     * @param alreadyW 已经存在的重量
     * @param bag      袋子
     * @return 最多的价值
     * 不变：w v bag
     * index... 最大价值
     * 0...index-1上做了货物的选择，使得你已经达到的重量是 alreadyW
     * 如果返回-1，认为没有方案
     * 如果不返回-1，认为返回的值是真实价值
     */
    private static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        // 重量没超
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, alreadyW, bag); // 没有要当前index的货物，产生的最大价值
        int p2Next = process(w, v, index + 1, alreadyW + w[index], bag); // 要当前index的货物，后面的货物会产生多少最大价值
        int p2 = -1;
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }


    private static int maxValue(int[] w, int[] v, int bag) {
        return process2(w, v, 0, bag);
    }

    private static int process2(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        // rest >= 0
        if (index == w.length) {
            return 0;
        }
        int p1 = process2(w, v, index + 1, rest); // 没有要当前index货物，后续产生的最大价值
        int p2next = process2(w, v, index + 1, rest - w[index]); // 要当前的货物，后续产生的最大价值
        int p2 = -1;
        if (p2next != -1) {
            p2 = v[index] + p2next;
        }
        return Math.max(p1, p2);
    }

}
