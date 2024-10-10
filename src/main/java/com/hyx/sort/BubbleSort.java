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
        3 2 4 1 6
              i j
            i j
          i j
        i j
        j
     */

    private static void bubble(int[] a) {
        int j = a.length - 1;
        while (true){
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]){
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    x = i;
                }
            }
            j = x;
            if (j == 0){
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
