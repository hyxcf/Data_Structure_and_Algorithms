package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className QuickSortHoare
 * @Date 2024/10/12  22:03
 * @description 双边循环
 * @since jdk 11
 */
public class QuickSortHoare {

    public static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(a, left, right); // p代表基准点元素索引
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /*
    双边循环要点
         1、选择最左侧元素作为基准点
         2、j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
          2.1 i 从左向右
          2.2 j 从右向左
         3、最后基准点与 i 交换，i 即为基准点最终索引
     */
    private static int partition(int[] a, int left, int right) {
        // 1、选择最左侧元素作为基准点
        int pv = a[left];
        int i = left; // i 从左往右 找大的
        int j = right;// j 从右往左 找小的
        while (i < j) {
            // 1. j 从右往左 找小的
            while (i < j && a[j] > pv) {
                j--;
            }
            // 2. i 从左往右 找大的
            while (i < j && a[i] <= pv) {
                i++;
            }
            swap(a, i, j);
            // 3.交换位置

        }
        swap(a, left, i);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
//        int[] a = {5, 3, 7, 2, 9, 8, 1, 4};
        int[] a = {5, 1, 1, 2, 0, 0};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
