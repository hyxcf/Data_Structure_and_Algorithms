package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className ShellSort
 * @Date 2024/10/11  20:14
 * @description 希尔排序
 * @since jdk 11
 */
public class ShellSort {

    /*
        - 简单的说，就是分组实现插入，每组元素间隙称为 gap
        - 每轮排序后 gap 逐渐变小，直至 gap 为 1 完成排序
        - 对插入排序的优化，让元素更快速地交换到最终位置
     */

    public static void sort(int[] a) {
        for (int gap = a.length >> 1; gap >= 1; gap = gap >> 1) {
            // gap = 4  2  1
            for (int low = gap; low < a.length; low++) {
                // 待插入元素
                int t = a[low];
                int i = low - gap;
                // 自右向左找插入位置，如果比待插入元素大，则不断右移，空出插入位置
                while (i >= 0 && t < a[i]) {
                    a[i + gap] = a[i];// 往后移位置
                    i -= gap;
                }
                // 找到插入位置
                if (i != low - gap) {
                    a[i + gap] = t;
                }

            }
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 1, 9, 8, 0};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}


