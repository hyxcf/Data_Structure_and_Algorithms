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
            for (int i = top; i <= bottom ; i++){
                res.add(matrix[i][right]);
            }
            if (--right < left) break;
            // 从右到左
            for (int i = right; i >= left; i --){
                res.add(matrix[bottom][i]);
            }
            if (top > --bottom) break;
            // 从下到上
            for (int i = bottom; i>=top;i--){
                res.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return res;
    }

}