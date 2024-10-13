package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className CountingSort
 * @Date 2024/10/13  10:52
 * @description 计数排序 (只处理 >= 0的元素)
 * @since jdk 11
 */
public class CountingSortPositive {

    /*
        思路:
            1.先找到数组中的最大值,然后创建一个 数组长度为 最大值+1 的数组
            2.新数组的索引 对应原始数组的元素,新数组记录初始数组中元素出现的次数;
            3.最后根据 新数组 中统计的元素个数,写出排序好的数组
        前提:
            待排序元素 >= 0 且最大值不能太大 (数组里面有负数的情况,就不行了)

        eg:
            初始数组:[5  2   1   2   0]
             新数组 :[1  1   2   0   0  1]
           新数组索引:0  1   2   3   4   5
            排序后的数组: 0 1 2 2 5
     */

    private static void sort(int[] a) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        int[] count = new int[max + 1];
        // 新数组记录初始数组中元素出现的次数
        for (int e : a) {
            count[e]++;
        }
        int k = 0; // 定义数组 a 的下标
        for (int i = 0; i < count.length; i++) {
            // i 代表原始数组元素 a[i] 元素出现次数
            while (count[i] > 0) {
                a[k++] = i;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 1, 2, 0};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}



















