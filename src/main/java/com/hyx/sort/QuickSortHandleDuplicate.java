package com.hyx.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortHandleDuplicate {

    public static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(a, left, right);
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /*
        注意事项：
            1.为啥要加内层循环的 i < j 条件
            2.为啥要先处理 j，再处理 i
            3.随机元素作为基准点
            4.数组中如果有大量重复元素
     */
    /*
        4.的解决办法要点
            循环内
                i 从 left + 1 开始，从左向右找大的或相等的
                j 从 right 开始，从右向左找小的或相等的
                交换，i++ j--
            循环外 j 和 基准点交换，j 即为分区位置
     */
    private static int partition(int[] a, int left, int right) {
        // 算随机值的时间复杂度是 O(n * log(n))
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, left, idx);
        int pv = a[left];
        int i = left + 1;// 第一个 i 肯定等于它自身，所以可以直接跳过，写成 left + 1
        int j = right;
        while (i <= j) {
            // j 从右向左找小的
            while (i <= j && a[j] > pv) {
                j--;
            }
            // i 从左向右找大的
            while (i <= j && a[i] < pv) {
                i++;
            }
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        swap(a, j, left); // j 是找小的，所以让 j 与基准点交换
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 1, 3, 2, 44, 4, 4, 4}; // 最外层循环 = 要加
//        int[] a = {2, 1, 3, 2}; // 内层循环 = 要加
//        int[] a = {2, 1, 3, 2}; // 内层if要加
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}