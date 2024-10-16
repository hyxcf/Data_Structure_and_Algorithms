package com.hyx.leetcode.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className E01Leetcode1122
 * @Date 2024/10/16  11:33
 * @description
 * @since jdk 11
 */
public class E01Leetcode1122 {

    /*
        根据另一个数组次序排序     前提
        1、元素值均 >= 0
        2、两个数组长度 <= 1000
        输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
        输出：[2,2,2,1,4,3,3,9,6,7,19]
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 使用计数排序
        int[] count = new int[1001];
        for (int i : arr1) {
            count[i]++;
        }
        int k = 0;
        int[] result = new int[arr1.length];
        for (int i : arr2) {
            while (count[i] > 0) {
                result[k++] = i;
                count[i]--;
            }
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                result[k++] = i;
                count[i]--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};

        int[] result = relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(result));

    }
}
