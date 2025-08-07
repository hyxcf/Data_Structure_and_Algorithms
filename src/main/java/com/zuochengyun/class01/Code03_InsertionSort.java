package com.zuochengyun.class01;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Code03_InsertionSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 7, 7, 3, 1};
        int[] arr2 = {1, 3, 4, 1, 5, 71, 3, 1};
        insertionSort(arr);
        insertionSort2(arr2);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }

    // 方法1
    public static void insertionSort(int[] arr) {
        for (int low = 1; low < arr.length; low++) {
            int t = arr[low];
            int i = low - 1;
            while (i >= 0 && t < arr[i]) {
                arr[i + 1] = arr[i];
                i--;
            }
            if (i != low - 1) {
                // 找到插入位置
                arr[i] = t;
            }
        }
    }

    // 方法2
    public static void insertionSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
                swap(arr, j + 1, j);
            }
        }
    }

    public static void swap(int[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

}
