package com.hyx.sort;

import com.hyx.DynamicArray;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className BucketSort
 * @Date 2024/10/13  14:57
 * @description 桶排序
 * @since jdk 11
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] ages = {20, 18, 28, 66, 25, 31, 67, 30};// 假设人类年龄 99
        System.out.println(Arrays.toString(ages));
        sort(ages);
        System.out.println(Arrays.toString(ages));

    }

    /*
      桶编号
        0
        1       18
        2       20  25  28
        3       30  31
        4
        5
        6       66  67
        7
        8
        9
     */


    private static void sort(int[] ages) {
        // 1.准备桶
        DynamicArray[] buckets = new DynamicArray[ages.length];
        // 1.1 初始化桶
        for (int i = 0; i < ages.length; i++) {
            buckets[i] = new DynamicArray();
        }
        // 2.放入年龄数据
        for (int age : ages) {
            buckets[age / 10].addLast(age);
        }
        int k = 0;
        for (DynamicArray bucket : buckets) {
            // 3.排序桶内元素
            int[] array = bucket.stream().toArray();
            InsertionSort.insertion(array);
            System.out.println(Arrays.toString(array));
            for (int v : array) {
                ages[k++] = v;
            }
        }

    }

}
