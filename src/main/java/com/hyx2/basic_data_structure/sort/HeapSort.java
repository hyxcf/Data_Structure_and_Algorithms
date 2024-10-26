package com.hyx2.basic_data_structure.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className HeapSort
 * @Date 2024/10/26  14:37
 * @description
 * @since jdk 11
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 7, 1, 6, 4, 7, 4};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 下潜操作
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 建立大顶堆
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        // 进行交换 - 排序
        int heapSize  = arr.length;
        while (heapSize > 0){
            swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 判断两个孩子谁大谁小
            // 判断右孩子是否存在，且大于左孩子
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 把父亲和较大的孩子进行比较
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            // 执行到这，说明孩子比父亲大，交换孩子
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
