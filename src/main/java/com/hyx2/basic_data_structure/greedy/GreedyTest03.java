package com.hyx2.basic_data_structure.greedy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author hyx
 * @version 0.1
 * @className GreedyTest03
 * @date 2024/12/27 23:35
 * @description
 * @since jdk 11
 */

/*
*** 小根堆、大根堆、排序 天然符合贪心策略的排序需求，本身就包含贪心的意思
哈夫曼树
一块金条切成两半，是需要花费和长度数值一样的铜板的。
比如长度为20的金条，不管怎么切，都要花费20个铜板。一群人想整分整块金条，怎么分最省铜板?

例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
如果先把长度60的金条分成10和50，花费60;再把长度50的金条分成20和30，花费50:一共花费110铜板
但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20，花费30;一共花费90铜板。

输入一个数组，返回分割的最小代价。
 */
public class GreedyTest03 {

    /**
     * 方法1：暴力解法 - 使用小根堆
     *
     * @param arr arr
     * @return
     */
    public static int getMinMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    private static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }


    /**
     * 贪心算法
     * 思路：按小根堆进行排序
     *      10
     *      20
     *      30
     * 出去前两个求出的和再回来，到最后只剩一个元素时结束
     * 30 - 60
     * @param arr arr
     * @return
     */
    public static int getMinMoney2(int[] arr) {
        if (arr == null || arr.length == 0){
            return 0;
        }
        if (arr.length == 1){
            return arr[0];
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pq.size() > 1) {
            cur = pq.poll() + pq.poll();
            sum += cur;
            pq.add(cur);
        }
        return sum;
    }


}

