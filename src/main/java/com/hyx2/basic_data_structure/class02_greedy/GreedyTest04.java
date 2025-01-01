package com.hyx2.basic_data_structure.class02_greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author hyx
 * @version 0.1
 * @className GreedyTest04
 * @date 2024/12/28 10:32
 * @description
 * @since jdk 11
 */

/*
输入:正数数组costs、正数数组profits、正数K、正数M
costs[i]表示i号项目的花费
profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
K表示你只能串行的最多做k个项目
W表示你初始的资金
说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
输出:你最后获得的最大钱数。
 */

/*
花费  1   1   4   5
利润  3   1   3   2

    小根堆(按花费排序)      大根堆(按利润排序)      初始资金
         1,1                                     1
         1,3                                    K=
         4,3
         5,2
     把所有初始资金符合小根堆的都弹出到大根堆,然后做利润中最大的，再从小根堆中找符合条件的初始资金，周而复始。
    小根堆(按花费排序)      大根堆(按利润排序)      初始资金
                              1,3 （做这个）       1 -> 4
                              1,1                K= 1
         4,3
         5,2

 */

public class GreedyTest04 {

    public static int getMaxCapital(int K, int W, int[] profits, int[] costs) {
        PriorityQueue<Program> minCostPriority = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitsPriority = new PriorityQueue<>(new MinCostComparator());
        // 先将所有的都加入小根堆
        for (int i = 0; i < profits.length; i++) {
            minCostPriority.add(new Program(profits[i], costs[i]));
        }
        // 最多可以做 K 个项目
        for (int i = 0; i < K; i++) {
            while (!minCostPriority.isEmpty() && minCostPriority.peek().c <= W) {
                maxProfitsPriority.add(minCostPriority.poll());
            }
            // 大根堆无任务可做，或者时 W(本金) 不够小根堆的花费时，停止，直接返回
            if (maxProfitsPriority.isEmpty()) {
                return W;
            }
            W += maxProfitsPriority.poll().p;
        }
        return W;
    }


    public static class Program {
        private int p;
        private int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitsComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
}
