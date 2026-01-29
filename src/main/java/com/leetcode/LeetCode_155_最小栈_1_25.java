package com.leetcode;

import java.util.Stack;

/**
 * @author hyx
 * @version 0.1
 * @className LeetCode_155_最小栈_1_25
 * @date 2026/1/25 17:20
 * @description
 * @since jdk 11
 */
public class LeetCode_155_最小栈_1_25 {

    /*
        设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
        实现 MinStack 类:
        MinStack() 初始化堆栈对象。
        void push(int val) 将元素val推入堆栈。
        void pop() 删除堆栈顶部的元素。
        int top() 获取堆栈顶部的元素。
        int getMin() 获取堆栈中的最小元素。

        示例 1:
        输入：
            ["MinStack","push","push","push","getMin","pop","top","getMin"]
            [[],[-2],[0],[-3],[],[],[],[]]
        输出：
            [null,null,null,null,-3,null,0,-2]
        解释：
            MinStack minStack = new MinStack();
            minStack.push(-2);
            minStack.push(0);
            minStack.push(-3);
            minStack.getMin();   --> 返回 -3.
            minStack.pop();
            minStack.top();      --> 返回 0.
            minStack.getMin();   --> 返回 -2.
     */

    private Stack<Integer> stack;
    private Stack<Integer> stackMin;

    public LeetCode_155_最小栈_1_25() {
        stack = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (stackMin.isEmpty() || val <= stackMin.peek()) {
            stackMin.push(val);
        }
    }

    public void pop() {
        if (stack.pop().equals(stackMin.peek())) {
            stackMin.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    // 复习
    static class Preview_1_29 {
        private Stack<Integer> minStack;
        private Stack<Integer> stack;

        public Preview_1_29() {
            minStack = new Stack<>();
            stack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

}
