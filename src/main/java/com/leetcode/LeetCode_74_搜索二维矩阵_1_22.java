package com.leetcode;

public class LeetCode_74_搜索二维矩阵_1_22 {
    
    /*
        给你一个满足下述两条属性的 m x n 整数矩阵：
        每行中的整数从左到右按非严格递增顺序排列。
        每行的第一个整数大于前一行的最后一个整数。
        给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
        示例 1：
            输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
            输出：true
        示例 2：
            输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
            输出：false
     */

    // 两种方法：1、将整个二维数组看成一个数组去进行二分  2、排除法：由于题目设置，可以直接与数组中的最后一个元素进行比较，从而定位位置
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int pre = 0;
        int last = m * n - 1;
        while (pre <= last) {
            int mid = (last + pre) >>> 1;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                pre = mid + 1;
            } else if (x > target) {
                last = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int i = 0;
            int j = matrix[0].length - 1;
            while (i < matrix.length && j >= 0) { // 还有剩余元素
                if (matrix[i][j] == target) {
                    return true; // 找到 target
                }
                if (matrix[i][j] < target) {
                    i++; // 这一行剩余元素全部小于 target，排除
                } else {
                    j--; // 这一列剩余元素全部大于 target，排除
                }
            }
            return false;
        }
    }

    static class Preview_1_30 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix.length == 0) {
                return false;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            int pre = 0;
            int last = m * n - 1;
            while (pre <= last) {
                int mid = (pre + last) >>> 1;
                int x = matrix[mid / n][mid % n];
                if (x < target) {
                    pre = mid + 1;
                } else if (x > target) {
                    last = mid - 1;
                } else {
                    return true;
                }
            }
            return false;
        }

        public boolean searchMatrix2(int[][] matrix, int target) {
            if (matrix.length == 0) {
                return false;
            }
            int i = 0;
            int j = matrix[0].length - 1;
            while (i < matrix.length && j >= 0) { // 还有剩余元素
                if (matrix[i][j] < target) {
                    i++;
                } else if (matrix[i][j] > target) {
                    j--;
                } else {
                    return true;
                }
            }
            return false;
        }

    }

}

