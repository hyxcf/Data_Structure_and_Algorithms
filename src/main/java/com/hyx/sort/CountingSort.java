package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className CountingSort
 * @Date 2024/10/13  10:52
 * @description 计数排序 (可以处理负数的情况)
 * @since jdk 11
 */
public class CountingSort {

    /*
        要点:
            1.让原始数组的最小值映射到 count[0] 最大值映射到 count 最右侧
            2.原始数组元素 - 最小值 = count 索引
            3.count 索引 + 最小值 = 原始数组元素

        初始数组:[5  2   1   2   0  -1]
        count   [1  1   1   2   0   0   1]
      新数组索引  0  1   2   3   4   5   6
        对应关系 -1  0   1   2   3   4   5
     */

    private static void sort(int[] a) {
        int max = a[0];
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        int[] count = new int[max - min + 1];
        // 新数组记录初始数组中元素出现的次数
        for (int e : a) {
            count[e - min]++;
        }
        int k = 0; // 定义数组 a 的下标
        for (int i = 0; i < count.length; i++) {
            // i + min 代表原始数组元素 a[i] 元素出现次数
            while (count[i] > 0) {
                a[k++] = i + min;
                count[i]--;
            }
        }
    }


    public static void main(String[] args) {
        int[] a = {-3, -4, 5, 2, 1, 2, 0, -1, -2};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}



