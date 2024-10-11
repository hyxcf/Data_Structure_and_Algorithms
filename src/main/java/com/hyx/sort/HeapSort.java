package com.hyx.sort;

import java.time.Period;
import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className HeapSort
 * @Date 2024/10/11  13:48
 * @description 堆排序
 * @since jdk 11
 */
public class HeapSort {

    // 1.建立大顶堆 （堆化）
    // 2.每次将堆顶元素（最大值）交换到末尾，调整堆顶元素，让他重新符合大顶堆特性 （下潜）

    public static void sort(int[] a) {
        // 建立大顶堆
        heapify(a, a.length);
        for (int right = a.length - 1; right > 0; right--) {
            swap(a, 0, right); // 每次将堆顶元素最大值交换到末尾
            down(a, 0, right); // 让剩余元素重新交换称为大顶堆
        }
    }

    // 建堆 O(n)
    private static void heapify(int[] array, int size) {
        // 找到最后一个非叶子节点
        for (int i = size / 2 - 1; i >= 0; i--) { // 1 0
            down(array, i, size);
        }
    }

    // 下潜
    private static void down(int[] array, int parent, int size) {
        while (true) {
            int left = parent * 2 + 1;
            int right = left + 1;
            int max = parent;
            // 注意 left 和 right 的索引范围
            if (left < size && array[left] > array[max]) {
                max = left;
            }
            if (right < size && array[right] > array[max]) {
                max = right;
            }
            // 判断是否被替换
            if (max == parent) { // 没找到更大的孩子
                break;
            }
            swap(array, max, parent);
            parent = max;
        }
    }

    // 交换
    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {66, 5, 333, 444, 1111};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
