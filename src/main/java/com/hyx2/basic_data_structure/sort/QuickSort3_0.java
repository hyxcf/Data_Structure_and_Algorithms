package com.hyx2.basic_data_structure.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className QuickSort3_0
 * @Date 2024/10/25  20:39
 * @description 快速排序 3.0
 * @since jdk 11
 */
public class QuickSort3_0 {
    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 7, 1, 6, 4, 7, 4};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        partition(arr, 0, arr.length - 1);
    }

    private static void partition(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 这一步使得快排的时间复杂度从 O(n^2) -> O(log(n) * n)
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        partition(arr, L, equalArea[0] - 1);
        partition(arr, equalArea[1] + 1, R);
    }

    // 求大于基准点的左边界和小于基准点的右边界
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
