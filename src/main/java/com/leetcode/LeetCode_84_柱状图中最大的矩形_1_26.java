package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
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
    // fixme:首尾都加元素0，尾部加0是因为如果数组是单调递减的则无法出栈；头部加0是因为数组中第一个元素无法判断
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        int[] tmp = new int[heights.length + 2];
        tmp[0] = 0;
        tmp[tmp.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) tmp[i + 1] = heights[i];
        heights = tmp;
        for (int i = 0; i < heights.length; i++) {
            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {
                int tmpInt = st.pop();
                res = Math.max(res, heights[tmpInt] * (i - st.peek() - 1));
            }
            st.push(i);
        }
        return res;
    }

}
