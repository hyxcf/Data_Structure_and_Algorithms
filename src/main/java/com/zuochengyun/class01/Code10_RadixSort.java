package com.zuochengyun.class01;

import java.util.Arrays;

/**
 * @author hyx
 * @version 0.1
 * @className Code10_RadixSort
 * @date 2025/8/19 21:31
 * @description 桶排序之基数排序
 * @since jdk 11
 */
public class Code10_RadixSort {

    /*
    步骤详解
    arr = [ 013, 021, 011, 052, 062 ]

    第一轮循环 d = 1
        count = [0   2   2   1                        ]
                 0   1   2   3   4   5   6   7   8   9
        求前缀和
        count = [0   2   4   5                        ]
                 0   1   2   3   4   5   6   7   8   9
         将arr中的数字通过count[]放入bucket中,注意：这里是从右往左遍历
         bucket = [ 021, 011, 052, 062, 013 ]
         将bucket放入arr中
         arr = bucket = [ 021, 011, 052, 062, 013 ]
     第二轮循环 d = 2
        count = [    2   1           1   1            ]
                 0   1   2   3   4   5   6   7   8   9
        求前缀和
        count = [0   2   3           4   5            ]
                 0   1   2   3   4   5   6   7   8   9
        将arr中的数字通过count[]放入bucket中, fixme : 注意：这里是从右往左遍历
        bucket = [ 011, 013, 021, 052, 062 ]

    排序完成!

     */

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 6, 2, 7, 2, 6};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    private static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数字就准备多少个辅助空间 这个是辅助数组
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { // 有多少位就进出几次
            // 10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是1的数字有多少个
            // count[2] 当前位(d位)是2的数字有多少个
            // count[i] 当前位(d位)是i的数字有多少个
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 这一步是进行前缀和的计算
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i]; // 这一步是将数组arr中的数字放入bucket中
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    /**
     * 获取 i 的第d位数字
     */
    private static int getDigit(int i, int d) {
        return (i / (int) Math.pow(10, d - 1)) % 10;
    }

    /**
     * 得到最大的位数
     *
     * @param arr
     * @return
     */
    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

}
