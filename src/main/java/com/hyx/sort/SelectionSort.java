package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className SelectionSort
 * @Date 2024/10/11  10:52
 * @description 选择排序
 * @since jdk 11
 */
/*
    要点：每一轮选择，找出最大（最小）的元素，并把它交换到合适的位置 */
public class SelectionSort {

    public static void sort(int[] a) {
        // 1.选择轮数 a.length - 1
        // 2.交换的索引位置(right) 初始化 a.length - 1,每次递减
        for (int right = a.length - 1; right > 0; right--) {
            int max = right;
            for (int i = 0; i < right; i++) {
                if (a[i] > a[max]) {
                    max = i;
                }
            }
            if (max != right) {
                swap(a, max, right);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
