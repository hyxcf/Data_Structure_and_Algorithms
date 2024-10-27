package com.hyx2.basic_data_structure.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className RadixSort
 * @Date 2024/10/27  18:32
 * @description 基数排序 时间复杂度 O(N*log(N))
 * @since jdk 11
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {213, 4, 2, 54, 2, 1, 5, 1, 9, 7};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    /**
     * 求最高位
     *
     * @param arr
     * @return
     */
    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }


    /**
     * @param arr   arr
     * @param L     最左侧的索引位置
     * @param R     最右侧的索引位置
     * @param digit 最高位数
     */
    private static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10; // 十进制的数以十为基底
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        // 有多少位就进出几次
        for (int d = 1; d <= digit; d++) {
            // 10 个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int[] count = new int[radix];
            // 这个循环统计 arr 在 d 位中出现的次数，用 count 数组收集
            for (i = L; i <= R; i++) {
                // 103 1  3
                // 209 1  9
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 求前缀和
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 创建一个 help 数组，对 arr 从右向左读个位，根据 count` 的次数判断位置，并向 help 中写入数据
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }

    }

    // 用于提取一个整数 x 中第 d 位的数字。
    private static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

}

/*
radixSort() 方法的原理：
arr = [101, 200, 012, 011, 202, 403]
求个位时
    使用普通桶排序时的顺序：(这样就创建了十个桶)
        [200, 101, 011, 012, 202, 403]

优化：1、新建一个长度为 10 的数组，统计个位出现次数
    count[1 2 2 1 0 0 0 0 0 0]      个位 == i 有几个
          0 1 2 3 4 5 6 7 8 9
    2、创建一个 count` 统计数组出现次数的前缀和
    count`[1 3 5 6 6 6 6 6 6 6]     个位 <= i 有几个
           0 1 2 3 4 5 6 7 8 9
    3、创建一个 help 数组，对 arr 从右向左读个位，根据 count` 的次数判断位置，并向 help 中写入数据
    count`[0 1 3 5 6 6 6 6 6 6]
           0 1 2 3 4 5 6 7 8 9

    help [200 101 011 012 202 403]
           0   1   2   3   4   5

问题：
    步骤 2 中为什么要从右向左读取？
    因为前缀和求得是一个大致范围 开头不确定，这样也可以保证稳定性

求其他位置，以此类推

 */






















