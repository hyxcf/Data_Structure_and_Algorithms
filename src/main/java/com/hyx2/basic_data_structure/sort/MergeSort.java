package com.hyx2.basic_data_structure.sort;

import java.util.Arrays;

// 归并排序（递归版）
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = { 1, 4, 8, 324, 87, 2, 832, 0 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		spilt(arr, 0, arr.length - 1);
	}

	private static void spilt(int[] arr, int left, int right) {
		if (left == right)
			return;
		int m = left + ((right - left) >> 1);
		spilt(arr, left, m);
		spilt(arr, m + 1, right);
		merge(arr, left, m, right);
	}

	private static void merge(int[] arr, int left, int m, int right) {
		int[] help = new int[right - left + 1];
		int k = 0; // 定义新数组指针
		int p1 = left;
		int p2 = m + 1;
		while (p1 <= m && p2 <= right) {
			help[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[k++] = arr[p1++];
		}
		while (p2 <= right) {
			help[k++] = arr[p2++];
		}
		// 合并数组
		for (int i = 0; i < help.length; i++) {
			arr[i + left] = help[i];
		}
	}

}
