package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className MergeInsertionSort
 * @Date 2024/10/12  16:04
 * @description 归并排序 + 插入排序
 * @since jdk 11
 */
public class MergeInsertionSort {

    // 归并排序+插入排序 16ms
    public static void sort(int[] a1) {
        int[] a2 = new int[a1.length];
        spilt(a1, 0, a1.length - 1, a2);
    }

    public static void spilt(int[] a1, int left, int right, int[] a2) {
        // 2.治
        if (right - left <= 32) {
            // 插入排序
            insertion(a1, left, right);
            return;
        }
        // 1.分
        int m = (left + right) >>> 1;

        spilt(a1, left, m, a2);
        spilt(a1, m + 1, right, a2);
        // 3.合
        merge(a1, left, m, m + 1, right, a2);
        System.arraycopy(a2, left, a1, left, right - left + 1);
    }

    /**
     * 合并
     *
     * @param a1   原始数组
     * @param i    第一个有序范围的起始索引
     * @param iEnd 第一个有序范围的结束索引
     * @param j    第二个有序范围的起始索引
     * @param jEnd 第二个有序范围的结束索引
     * @param a2   临时数组
     */
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;// 定义新定义数组的下标
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }

    // 插入排序
    public static void insertion(int[] a, int left, int right) {
        for (int low = left + 1; low <= right; low++) {
            int t = a[low];
            int i = low - 1;
            while (i >= left && a[i] > t) {
                a[i + 1] = a[i];
                i--;
            }
            // 找到插入位置
            if (i != low - 1) {
                a[i + 1] = t;
            }
        }
    }

    public static void main(String[] args) {
//        int[] a = {6, 5, 4, 3, 2, 1, 9, 8, 0};
        int[] a = {6, 5, 4, 3, 2};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
