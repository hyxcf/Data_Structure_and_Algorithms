package com.leetcode;

public class Leetcode_48_旋转图像_11_24 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer; // first：当前层的起始行号 = 起始列号 = layer
            int last = n - 1 - layer; // last：当前层的结束行号 = 结束列号 = n - 1 - layer
            for (int i = first; i < last; i++) {
                int offset = i - first; // offset 表示当前处理的是当前层的第几个位置（从 0 开始）

                // Save top
                int top = matrix[first][i];

                // top <- left
                matrix[first][i] = matrix[last - offset][first];

                // left <- bottom
                matrix[last - offset][first] = matrix[last][last - offset];

                // bottom <- right
                matrix[last][last - offset] = matrix[i][last];

                // right <- right
                matrix[i][last] = top;
            }
        }
    }    
}
