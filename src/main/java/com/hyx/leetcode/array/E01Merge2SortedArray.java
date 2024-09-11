package com.hyx.leetcode.array;

import java.util.Arrays;

/**
 * 合并两个有序数组（Leetcode 88 题改）
 *
 * @author hyx
 */
public class E01Merge2SortedArray {


    // 方法1 ：双指针

    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = 0;// 定义新定义数组的下标
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }


    /* *//**
     * 自己想的
     * @param a1    第一个数组
     * @param a1End 第一个数组的长度
     * @param a2    第二个数组
     * @param a2End 第二个数组的长度
     * @return
     *//*
    public static int[] merge(int[] a1, int a1End, int[] a2, int a2End) {
        int i = 0;// 定义nums1数组的下标
        int j = 0;// 定义nums2数组的下标
        int k = 0;// 定义新定义数组的下标
        int[] a = new int[a1End + a2End]; // 6
        while (i <= a1End && j <= a2End) {
            if (i >= a1End) {
                System.arraycopy(a2, j, a, k, a2End - j);
                return a;
            }
            if (j >= a2End) {
                System.arraycopy(a1, i, a, k, a1End - i);
                return a;
            }
            if (a1[i] < a2[j]) {
                a[k] = a1[i];
                i++;
            } else {
                a[k] = a2[j];
                j++;

            }
            k++;

        }
        return null;
    }*/

    // 方法 2：递归
    /*
        递归思路：
        merge([1,5,6] [2,4,10,11], a2=[]){
            merge([5,6] [2,4,10,11], a2=[1]){
                merge([5,6] [4,10,11], a2=[1,2]){
                    merge([5,6] [10,11], a2=[1,2,4]){
                        merge([6] [10,11], a2=[1,2,4,5]){
                            merge([] [10,11], a2=[1,2,4,5,6]){
                                merge([] [], a2=[1,2,4,5,6,10,11]){
                            }
                        }
                    }
                }
            }
        }
     */

    /**
     * 数组的左半部分
     * 将一个数组进行拆分，分为两部分
     *
     * @param a1   第一个数组
     * @param i    第一个数组左半部分起始下标
     * @param iEnd 第一个数组左半部分终点下标
     * @param j    第二个数组右半部分起始下标
     * @param jEnd 第二个数组右半部分终点下标
     * @param a2   新数组
     * @param k    新数组下标
     */
    public static void merge2(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2, int k) {

        // 数组的左半部分 已经为空，将数组的右半部分复制到新数组
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
            return;
        }

        // 反之，亦然
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
            return;
        }


        // 比较数组的左半部分和右半部分的起始下标
        // 左半部分 比 右半部分小，左半部分起始位置的则入新数组
        if (a1[i] < a1[j]) {
            a2[k] = a1[i];
            merge2(a1, i + 1, iEnd, j, jEnd, a2, k + 1);
        }
        // 左半部分 比 右半部分大，右半部分起始位置的则入新数组
        if (a1[i] > a1[j]) {
            a2[k] = a1[j];
            merge2(a1, i, iEnd, j + 1, jEnd, a2, k + 1);
        }
    }

    public static void main(String[] args) {
//        int[] a1 = {1, 5, 6, 2, 4, 10, 11};
//        int[] a2 = new int[a1.length];
        int[] a1 = {1, 2, 3, 8, 2, 5, 6};
        int[] a2 = new int[a1.length];
        merge(a1, 0, 3, 4, 6, a2);
        System.out.println(Arrays.toString(a2));
    }
}
