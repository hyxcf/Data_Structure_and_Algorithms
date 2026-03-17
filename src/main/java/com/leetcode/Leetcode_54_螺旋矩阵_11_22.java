package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 11_22 十一月二十二号
 * 54：力扣第54题
 */
public class Leetcode_54_螺旋矩阵_11_22 {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        List<Integer> res = new ArrayList<>();

        while (true) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            if (++top > bottom) break;
            // 从上到下
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if (--right < left) break;
            // 从右到左
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            if (top > --bottom) break;
            // 从下到上
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return res;
    }

    private static class Preview_3_17 {
        /*
            给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
            示例 1：
                输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
                输出：[1,2,3,6,9,8,7,4,5]
            示例 2：
                输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
                输出：[1,2,3,4,8,12,11,10,9,5,6,7]
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return new ArrayList<>();
            }
            int left = 0;
            int right = matrix[0].length - 1;
            int top = 0;
            int bottom = matrix.length - 1;
            List<Integer> res = new ArrayList<>();
            while (true) {
                // 从左到右
                for (int i = left; i <= right; i++) {
                    res.add(matrix[top][i]);
                }
                if (++top > bottom) break;
                // 从上到下
                for (int i = top; i <= bottom; i++) {
                    res.add(matrix[i][right]);
                }
                if (--right < left) break;
                // 从右到左
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                if (--bottom < top) {
                    break;
                }
                // 从下到上
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                if (++left > right) {
                    break;
                }
            }
            return res;
        }
    }

}