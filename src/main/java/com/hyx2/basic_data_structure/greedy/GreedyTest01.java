package com.hyx2.basic_data_structure.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author hyx
 * @version 0.1
 * @className GreedyTest01
 * @date 2024/12/27 20:37
 * @description
 * @since jdk 11
 */
/*
一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲
给你每一个项目开始的时间和结束的时间
你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
返回最多的宣讲场次。
 */
public class GreedyTest01 {


    /**
     * 暴力解法：方法1
     * @param programs 会议的数组
     * @return 安排会议的数量
     */
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     * @param programs 会议的数组
     * @param done     安排了几个会议
     * @param timeLine 安排会议的最后结束时间
     * @return 安排会议的数量
     */
    private static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    /**
     * 将数组中第i个元素删除
     *
     * @param programs 会议的数组
     * @param i        第i个元素
     * @return 新的会议数组
     */
    private static Program[] copyButExcept(Program[] programs, int i) {
        int index = 0;
        Program[] newArr = new Program[programs.length - 1];
        for (int j = 0; j < programs.length; j++) {
            if (j == i) {
                continue;
            }
            newArr[index++] = programs[j];
        }
        return newArr;
    }


    /**
     * 贪心算法（）
     * @param programs 会议的数组
     * @return 安排会议的数量
     */
    public static int bestArrange2(Program[] programs){
        // 将每个结束时间从小到大进行排列
        Arrays.sort(programs,new programComparator());
        int result = 0;
        int timeLine = 0;
        for (int i = 0; i < programs.length;i++){
            if (programs[i].start >= timeLine){
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }

    public static class programComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }


    /**
     * 新建会议对象
     */
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    // 测试
    public static void main(String[] args) {
        Program[] programs = new Program[5];
        programs[0] = new Program(8,9);
        programs[1] = new Program(8,10);
        programs[2] = new Program(10,18);
        programs[3] = new Program(11,17);
        programs[4] = new Program(18,19);
        int result = bestArrange1(programs);
        System.out.println(result);
    }

}
