package com.hyx.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className QuickSortLomuto
 * @Date 2024/10/12  17:26
 * @description
 * @since jdk 11
 * <p>
 * 单边循环快排（lomuto 洛穆托分区方案）
 * 核心思想： 每轮找到一个基准点元素，把比它小的放到它左边，比它大的放到它右边，这称为分区
 * <p>
 * 单边循环（lomuto分区）要点
 * 1、选择最右侧元素作为基准点
 * 2、j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
 * 3、交换时机：j 找到小的，且与 i 不相等，i 找到 >= 基准点元素后，不应自增
 * 4、最后基准点与 i 交换，i 即为基准点最终索引
 */
public class QuickSortLomuto {

    public static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(a, left, right); // p代表基准点元素索引
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /*
    单边循环（lomuto分区）要点
        1、选择最右侧元素作为基准点
        2、j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
        3、交换时机：j 找到小的，且与 i 不相等，i 找到 >= 基准点元素后，不应自增
        4、最后基准点与 i 交换，i 即为基准点最终索引
     */
    private static int partition(int[] a, int left, int right) {
        // 1、选择最右侧元素作为基准点
        int pv = a[right];
        int i = left;
        int j = left;
        while (j < right) {
            if (a[j] < pv) { // j 找基准点小的，i找基准点大的
                if (i != j) {
                    swap(a, i, j);
                }
                i++;
            }
            j++;
        }
        swap(a, i, right);
        return i; // 此时的i为最终基准点
    }


    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
//        int[] a = {5, 3, 7, 2, 9, 8, 1, 4};
        int[] a = {4, 3, 7, 2, 9, 8, 1, 5};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}

/*
单边循环（lomuto分区）要点
    1、选择最右侧元素作为基准点
    2、j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
    3、交换时机：j 找到小的，且与 i 不相等，i 找到 >= 基准点元素后，不应自增
    4、最后基准点与 i 交换，i 即为基准点最终索引


    4   3   2   1   5   8   7   9
                    i
                                j
    l                           r


    4   3   2   1   9   8   7   5
                    i
                                j
    l                           r

此时。j = right,j退出，i的位置就是基准点，最后基准点 与 i 交换，i即为基准点最终索引。
    4   3   2   1   5   8   7   9
                    i
                                j
    l                           r

执行顺序
quick(int[] a,int 0,int 7)
partition(a,0,7) return p = 4; l=0 r=7
    quick(a, 0, 3);
    partition(a,0,3) return p=0 l=0 r=3
        quick(a,0,0-1) return;
        quick(a,1,3)
            partition(a,1,3) return p=3 l=1 r=3
                quick(a,1,2)
                    partition(a,1,2) return p=1 l=1 r=2
                       quick(a,1,0) return;
                       quick(a,2,2) return;
                quick(a,4,3) return;
    quick(a,5,7)
    partition(a,5,7) return p=7 l=5 r=7
        quick(a,5,6)
            partition(a,5,6) return p=5 l=5 r=6
                quick(a,5,4) return;
                quick(a,6,6) return;
        quick(a,8,7) return;
结束，排序完成

 */
































