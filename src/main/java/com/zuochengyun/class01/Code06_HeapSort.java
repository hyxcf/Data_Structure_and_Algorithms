package com.zuochengyun.class01;

import java.util.Arrays;

/**
 * 堆排序
 * 左孩子：2*i+1
 * 右孩子：2*i+2
 */
public class Code06_HeapSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 6, 2, 7, 2, 6};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("堆排序,时间复杂度为O(N*logN),额外空间复杂度为O(1),无稳定性");
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 从最后一个非叶子节点开始，自底向上进行堆化
        for (int i = arr.length / 2 - 1; i >= 0; i--) { // 最后一个非叶子节点
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
