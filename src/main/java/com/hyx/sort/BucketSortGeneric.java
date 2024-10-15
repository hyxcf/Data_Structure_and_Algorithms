package com.hyx.sort;

import java.util.ArrayList;
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
        int[] ages = {9, 0, -1, 1, 3, -2, 4, 6, 8, 7};// 假设人类年龄 99
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
        ArrayList<Integer>[] buckets2 = new ArrayList[(max - min) / range + 1];
        // 1.1 初始化桶
        for (int i = 0; i < buckets2.length; i++) {
            buckets2[i] = new ArrayList<>();
        }
        // 2.放入数据
        for (int age : ages) {
            buckets2[(age - min) / range].add(age);
        }
        // 3.排序桶内元素
        int k2 = 0;
        for (ArrayList<Integer> bucket2 : buckets2) {
            Integer[] arr = new Integer[bucket2.size()];
            arr = bucket2.toArray(arr);
            int[] array = Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
            // 使用选择排序
            InsertionSort.insertion(array);
            for (int v : array) {
                ages[k2++] = v;
            }
            System.out.println(bucket2);
            System.out.println(Arrays.toString(ages));
        }
    }
}
