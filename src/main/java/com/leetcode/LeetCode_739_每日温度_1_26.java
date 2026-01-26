package com.leetcode;

import java.util.Stack;

public class LeetCode_739_每日温度_1_26 {

    /*
        给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。

        示例 1:
            输入: temperatures = [73,74,75,71,69,72,76,73]
            输出: [1,1,4,2,1,1,0,0]
        示例 2:
            输入: temperatures = [30,40,50,60]
            输出: [1,1,1,0]
        示例 3:
            输入: temperatures = [30,60,90]
            输出: [1,1,0]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // 单调栈 从左往右
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && t > temperatures[stack.peek()]) {
                // 找到了第一个右边比他大的数
                int j = stack.pop();
                // 计算距离  
                ans[j] = i - j;
            }
            stack.push(i);
        }
        return ans;
    }
}
