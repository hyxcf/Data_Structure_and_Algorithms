package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className Test
 * @Date 2024/10/21  20:01
 * @description
 * @since jdk 11
 */
public class RadixSort2 {

    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // 取余数按位来排序
        /*
        这个循环用于统计数组 arr 中每个数字在当前位（由 exp 决定）上的数字出现的次数。
        (arr[i] / exp) % 10 取出当前数字 arr[i] 在特定位上的数字（比如个位、十位等，当 exp 为 1 时取个位，为 10 时取十位）。
        然后将对应的计数数组 count 中该数字的计数加一。
         */
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        /*
        这个循环用于对计数数组 count 进行累加操作。
        经过第一个循环后，count[i] 表示当前位上数字 i 出现的次数。这个循环使得 count[i] 变为当前位上数字小于等于 i 的数字出现的累计次数。
        这样做的目的是为了确定每个数字在输出数组中的最终位置。
        实现稳定排序
         */
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        /*
        这个循环用于将数组 arr 中的数字按照当前位的数字顺序填充到输出数组 output 中。
        output[count[(arr[i] / exp) % 10] - 1] = arr[i] 根据计数数组确定当前数字在输出数组中的位置。
        count[(arr[i] / exp) % 10]-- 是为了确保相同数字在输出数组中保持相对顺序，因为计数数组中的计数是针对小于等于当前数字的累计次数。
         */
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        // 这个循环将排序好的输出数组 output 的内容复制回原始数组 arr，完成当前位的排序。
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {180, 172, 193, 141};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
/*
以下是对这行代码的详细解释：
1. output[count[(arr[i] / exp) % 10] - 1] = arr[i];：
    - (arr[i] / exp) % 10：这部分先通过将当前数组元素 arr[i] 除以 exp（这个 exp 的值在每一轮循环中不同，用于确定是针对个位、十位、百位等特定位置进行排序），
       然后取模 10，这样就得到了当前元素 arr[i] 在当前考虑的位置上的数字（例如，当 exp 为 1 时得到个位数字，exp 为 10 时得到十位数字）。
    - count[(arr[i] / exp) % 10]：这个值是通过前面的步骤统计得到的，表示在当前考虑的位置上，小于或等于当前元素对应位置数字的元素个数。
    - count[(arr[i] / exp) % 10] - 1：减去 1 是为了确定当前元素在输出数组 output 中的正确位置索引。因为计数是从 1 开始的，而数组索引从 0 开始。
    - output[count[(arr[i] / exp) % 10] - 1] = arr[i]：将当前元素 arr[i] 放置到输出数组 output 中的正确位置。
count[(arr[i] / exp) % 10]--;：
这一步是在放置完当前元素后，将对应位置的计数减一。这是因为计数数组 count 中的值表示小于或等于某个数字的元素个数，当放置了一个特定数字的元素后，该数字的计数需要减一，以确保下一个相同数字的元素能够放置在正确的位置，保证排序的稳定性。
总的来说，这两行代码的作用是根据当前元素在特定位置上的数字，确定其在输出数组中的位置，并将其放置在正确的位置，同时更新计数数组以保证后续元素的正确放置。
 */