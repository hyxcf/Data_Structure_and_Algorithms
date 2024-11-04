package com.hyx2.basic_data_structure.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className HeLanGuoQi
 * @Date 2024/10/25  19:29
 * @description 荷兰国旗问题
 * @since jdk 11
 */
public class HeLanGuoQi {
    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 7, 1, 6, 4, 7, 4};
        int[] res = netherlandsFlag(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(res));
    }

    private static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, r, more);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
