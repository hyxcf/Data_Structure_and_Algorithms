package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className MergeSortBottomUp
 * @Date 2024/10/12  14:22
 * @description 归并排序 - 自下向上(非递归)
 * @since jdk 11
 */
public class MergeSortBottomUp {


    public static void sort(int[] a1) {
        int n = a1.length;
        int[] a2 = new int[n];
        // width 代表有序区间的宽度，取值一次为 1、2、4 ...
        for (int width = 1; width < n; width *= 2) { // width : 1  2  4
            // [left,right] 分别代表待合并区域的左右边界
            for (int left = 0; left < n; left += 2 * width) {
                // 为了防止 right 超出数组的有效范围
                int right = Math.min(left + 2 * width - 1, n - 1);
                int m = Math.min(left + width - 1, n - 1);
                    merge(a1, left, m, m + 1, right, a2);
            }
            System.arraycopy(a2, 0, a1, 0, n);
        }
    }

    // 合并
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i; // 新数组下标
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

    public static void main(String[] args) {
//        int[] a = {6, 5, 4, 3, 2, 1, 9, 8, 0};
        int[] a = {6, 5, 4, 3, 2};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
/*
               初始数组：      9   3   7   2   8   5   1   4
    第一次排序(width为1)：     (3   9)   (2   7)   (5   8)   (1   4)       left:0  2   4   6     right:1   3   5   7
    第二次排序(width为2)：     (2   3   7   9)   (1   4   5   8)           left:0  4             right:3   7
    第三次排序(width为4)：     (1   2   3   4   5   7   8   9)             left:0                right:7

 */