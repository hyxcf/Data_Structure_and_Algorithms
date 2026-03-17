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

                // right <- top
                matrix[i][last] = top;
            }
        }
    }

    /*
        给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
        你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
        示例 1：
            输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
            输出：[[7,4,1],[8,5,2],[9,6,3]]
        示例 2：
            输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
            输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     */
    private static class Preview_3_17 {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            for (int layer = 0; layer < n / 2; layer++) {
                int first = layer;// first：当前层的起始行号 = 起始行号 = layer
                int last = n - 1 - layer;// 当前层的结束行号 = 结束列号 = n - 1 - layer
                for (int i = first; i < last; i++) {
                    int offset = i - first;
                    // Save top
                    int top = matrix[first][i];

                    // top <- left
                    matrix[first][i] = matrix[last - offset][first];

                    // left <- bottom
                    matrix[last - offset][first] = matrix[last][last - offset];

                    // bottom <- right
                    matrix[last][last - offset] = matrix[i][last];

                    // right <- top
                    matrix[i][last] = top;
                }
            }
        }
    }

}
