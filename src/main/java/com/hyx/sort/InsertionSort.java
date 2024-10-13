package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className InsertionSort
 * @Date 2024/10/11  19:31
 * @description 插入排序
 * @since jdk 11
 */
@SuppressWarnings("all")
public class InsertionSort {

    /*
    要点
        - 将数组分为两部分 [0 .. low-1]  [low .. a.length-1]
          - 左边 [0 .. low-1] 是已排序部分
          - 右边 [low .. a.length-1] 是未排序部分
        - 每次从未排序区域取出 low 位置的元素, 插入到已排序区域
     */

    // 递归版
    private static void insertion(int[] a, int low) {
        // 将数组分为两部分，一部分是已经排序的，另一部分是还未排序的
        if (low == a.length) {
            return;
        }
        int t = a[low]; // 待排序插入的元素
        int i = low - 1; // 上一个元素

        while (i >= 0 && t < a[i]) {
            a[i + 1] = a[i];// 将元素往后移
            i--;
        }
        if (i != low - 1) { // 找到了
            // 那么 就找到了插入位置
            a[i + 1] = t;
        }
        insertion(a, low + 1);
    }

    // 非递归版
    public static void insertion(int[] a) {
        for (int low = 1; low < a.length; low++) {
            // 待插入元素
            int t = a[low];
            int i = low - 1;
            // 自右向左找插入位置，如果比待插入元素大，则不断右移，空出插入位置
            while (i >= 0 && t < a[i]) {
                a[i + 1] = a[i];// 往后移位置
                i--;
            }
            // 找到插入位置
            if (i != low - 1) {
                a[i + 1] = t;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1, 9, 8, 0};
        System.out.println(Arrays.toString(a));
        insertion(a);
        System.out.println(Arrays.toString(a));
    }

}
