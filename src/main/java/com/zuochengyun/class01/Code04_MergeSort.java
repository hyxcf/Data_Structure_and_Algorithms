package com.zuochengyun.class01;

import java.util.Arrays;

/**
 * @author hyx
 * @version 0.1
 * @className Code04_MergeSort
 * @date 2025/8/10 0:21
 * @description 时间复杂度 O(n * log n) 空间复杂度 O(n)
 * @since jdk 11
 */
public class Code04_MergeSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 6, 2, 7, 2, 6};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        // 分治
        // 求中点
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int help[] = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p2] < arr[p1] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int k = 0; k < help.length; k++) {
            arr[l + k] = help[k]; // fixme:这里需要注意合并时的索引
        }
    }

}
