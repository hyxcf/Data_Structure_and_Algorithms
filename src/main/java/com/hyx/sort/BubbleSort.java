package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className BubbleSort
 * @Date 2024/10/10  14:29
 * @description 冒泡排序
 * @since jdk 11
 */
public class BubbleSort {

    /*
        优化:每次循环时，若能确定 更合适 的右边界，则可以减少冒泡次数
        x 记录的是最后无序的右边界

        3   2   1   4   5
        i  i+1
        x
        2   3   1   4   5
        i  i+1
        x
        2   1   3   4   5
            i  i+1
            x
        2   1   3   4   5
                    i  i+1
            x


       8    9   2   3   1
       i   i+1
       x

       8    2   9   3   1
            i  i+1
            x
       8    2   3   9   1
                i  i+1
                x
       8    2   3   1   9
                    i  i+1
                    x
       2    8   3   1   9
       i   i+1
       x
       2    3   8   1   9
            i  i+1
            x
       2    3   1   8   9
                i  i+1
                x
       以此类推
     */

    // 递归版
    private static void bubble2(int[] a, int j) {
        if (j == 0) {
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int t = a[i];
                a[i] = a[i + 1];
                a[i + 1] = t;
                x = i;
            }
        }
        bubble2(a, x);
    }

    // 非递归版
    private static void bubble(int[] a) {
        int j = a.length - 1;
        while (true) {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    x = i;
                }
            }
            j = x;
            if (j == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        bubble(a);
        System.out.println(Arrays.toString(a));
    }
}
