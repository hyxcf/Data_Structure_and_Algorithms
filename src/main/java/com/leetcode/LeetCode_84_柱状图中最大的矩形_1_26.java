package com.leetcode;

import java.util.Stack;

public class LeetCode_84_柱状图中最大的矩形_1_26 {

    /*
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
    示例 1:
        输入：heights = [2,1,5,6,2,3]
        输出：10
        解释：最大的矩形为图中红色区域，面积为 10
    示例 2：
        输入： heights = [2,4]
        输出： 4
     */
    public int largestRectangleArea(int[] heights) {
        return 0;
    }

    // 读懂题意：这个是找左右两边第一个小于当前值的数
    static class Solution {
        public int largestRectangleArea(int[] heights) {
            int[] newHeight = new int[heights.length + 2];
            System.arraycopy(heights, 0, newHeight, 1, heights.length);
            newHeight[heights.length + 1] = 0;
            newHeight[0] = 0;

            Stack<Integer> stack = new Stack<>();
            stack.push(0);

            int res = 0;
            for (int i = 1; i < newHeight.length; i++) {
                while (newHeight[i] < newHeight[stack.peek()]) {
                    int mid = stack.pop();
                    int w = i - stack.peek() - 1;
                    int h = newHeight[mid];
                    res = Math.max(res, w * h);
                }
                stack.push(i);

            }
            return res;
        }
    }
}
