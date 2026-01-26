package com.leetcode;

import java.util.Stack;

public class LeetCode_42_接雨水_1_26 {

    /*
        给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
        示例 1：
            输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
            输出：6
            解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
        示例 2：
            输入：height = [4,2,0,3,2,5]
            输出：9
     */

    // 单调栈法
    public int trap(int[] height) {
        int length = height.length;
        if (length <= 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int sum = 0;
        for (int i = 1; i < height.length; i++) {
            // 两个墙相等，存不下雨水，把老的弹出栈，新的再加入栈
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int mid = stack.pop();
                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    int h = Math.min(height[i], height[left]) - height[mid];
                    int w = i - left - 1;
                    sum += h * w;
                }
            }
            stack.push(i);
        }
        return sum;
    }
    
    // 双指针法，这个更加高效
    static class Solution2{
        public int trap(int[] height) {
            if (height == null || height.length <= 2) {
                return 0;
            }

            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;
            int sum = 0;

            while (left < right) {
                // 更新左右最大值（注意：是“到当前位置为止”的最大值）
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);

                if (leftMax < rightMax) {
                    // 左边瓶颈更小 → left 位置的积水由 leftMax 决定
                    sum += leftMax - height[left];
                    left++;  // 处理下一个左边位置
                } else {
                    // 右边瓶颈更小（或相等）→ right 位置的积水由 rightMax 决定
                    sum += rightMax - height[right];
                    right--; // 处理下一个右边位置
                }
            }
            return sum;
        }
    }
    
}
