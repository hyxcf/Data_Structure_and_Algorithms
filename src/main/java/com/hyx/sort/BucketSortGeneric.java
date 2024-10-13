package com.hyx.sort;

import com.hyx.DynamicArray;

import java.util.Arrays;

/**
 * @author hyx
 * @version 0.1
 * @className BucketSortGeneric
 * @date 2024/10/13 16:04
 * @description 桶排序（改良版）
 * @since jdk 11
 */
public class BucketSortGeneric {

    public static void main(String[] args) {
        int[] ages = {9, 0, 5, 1, 3, 2, 4, 6, 8, 7};// 假设人类年龄 99
        System.out.println(Arrays.toString(ages));
        sort(ages, 3);
        System.out.println(Arrays.toString(ages));

    }

    /*
        BucketSort 桶排序 的缺点：
            1、数据比较密集时，数据都集中在个别桶中，导致性能较差
            2、桶的长度没有限制
     */
    /*
        一个桶里放 range 个元素（range = 3）
        0   0，1，2
        1   3，4，5
        2   6，7，8
        3   9
     */
    private static void sort(int[] ages, int range) {
        // 求得最大值和最小值
        int min = ages[0];
        int max = ages[0];
        for (int i = 1; i < ages.length; i++) {
            if (min > ages[i]) {
                min = ages[i];
            }
            if (max < ages[i]) {
                max = ages[i];
            }
        }
        // 1.创建桶
        DynamicArray[] buckets = new DynamicArray[(max - min) / range + 1];
        // 1.1 初始化桶
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }
        // 2.放入数据
        for (int age : ages) {
            buckets[(age - min) / range].addLast(age);
        }
        // 3.排序桶内元素
        int k = 0;
        for (DynamicArray bucket : buckets) {
            // 排序
            int[] array = bucket.stream().toArray();
            InsertionSort.insertion(array);
            System.out.println(Arrays.toString(array));
            for (int v : array) {
                ages[k++] = v;
            }
        }
    }


}
