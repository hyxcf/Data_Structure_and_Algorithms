package com.zuochengyun.class01;

import java.util.Arrays;

/**
 * 数组小和问题 : 求出左侧的数字比他小的 和  1 2 5 4 1      1+1+2+2+1 = 7
 * 求左数字比他小的，也就是从左往右看 有几个比他大的，比如说1右侧有三个比他大的，所以有3个1的小和
 */
public class Code08_ArrSmallSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 4, 1};
        int sum = mergeSort(arr);
        System.out.println("数组小和为：" + sum);
        System.out.println(Arrays.toString(arr));
    }

    public static int mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[l...r]  既要排好序，也要求小和
    public static int process(int[] arr, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) +
                process(arr, mid + 1, r) +
                merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0; // 当前右侧有几个数比左侧指向的那个数大
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++]; // 只有左组小于右组时，才移动左组。
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int k = 0; k < help.length; k++) {
            arr[l + k] = help[k];
        }
        return res;
    }

}
