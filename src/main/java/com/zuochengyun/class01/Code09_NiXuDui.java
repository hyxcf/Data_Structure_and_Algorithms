package com.zuochengyun.class01;

import java.util.Arrays;

/**
 * 逆序对问题 在一个数组中，左边的数如果比右边的数大，则两个数构成一个逆序对，请打印所有逆序对。
 */
public class Code09_NiXuDui {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 4, 1};
        int sum = mergeSort(arr);
        System.out.println("逆序对的数量为：" + sum);
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
            if (arr[p1] > arr[p2]) {
                // 左组当前元素大于右组当前元素
                // 那么左组从p1到mid的元素都大于arr[p2]
                for (int k = p1; k <= mid; k++) {
                    System.out.println(arr[k] + "  " + arr[p2]);
                }
                res += mid - p1 + 1;
                help[i++] = arr[p2++];
            } else {
                help[i++] = arr[p1++];
            }
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
