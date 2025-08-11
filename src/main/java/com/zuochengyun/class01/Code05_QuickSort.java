package com.zuochengyun.class01;

/**
 * 快排 最差情况 O(n²)  要随机确定一个划分值，使时间复杂度为O(n * log(n))
 * 规则:
 * 1) [i] < num, [i] 和 < 区域的下一个交换， < 区右扩 i++
 * 2) [i] == num, i++
 * 3) [i] > num, [i] 和 > 区域的前一个交换， > 区左扩，i原地不动！！！
 */
public class Code05_QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r); // fixme：因为有这个时间复杂度才为O(n * log(n))
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        return new int[]{l, r};
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

}
