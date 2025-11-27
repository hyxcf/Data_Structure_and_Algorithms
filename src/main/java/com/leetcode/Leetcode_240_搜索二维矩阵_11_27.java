package com.leetcode;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class Leetcode_240_搜索二维矩阵_11_27 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while(i >= 0 && j < matrix[0].length){
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target){
                j++;
            }else{
                return true;
            }
        }
        return false;
    }
}
