package com.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode_503_下一个更大元素II_1_26 {

    /*
        给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
        数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
        示例 1:
            输入: nums = [1,2,1]
            输出: [2,-1,2]
            解释: 第一个 1 的下一个更大的数是 2；
            数字 2 找不到下一个更大的数； 
            第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
        示例 2:
            输入: nums = [1,2,3,4,3]
            输出: [2,3,4,-1,4]
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        int size = nums.length;
        Stack<Integer> st = new Stack<>();
        // fixme：遇到成环的情况，可以使用取模 % 处理成环的过程
        for (int i = 0; i < 2 * size; i++) {
            while (!st.empty() && nums[i % size] > nums[st.peek()]) {
                res[st.peek()] = nums[i % size];//更新result
                st.pop();//弹出栈顶
            }
            st.push(i % size);
        }
        return res;
    }
}
