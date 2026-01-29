package com.leetcode;

import java.util.Stack;

/**
 * @author hyx
 * @version 0.1
 * @className LeetCode_394_字符串解码_1_25
 * @date 2026/1/25 21:39
 * @description
 * @since jdk 11
 */
public class LeetCode_394_字符串解码_1_25 {

    /*
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
    测试用例保证输出的长度不会超过 105。
    示例 1：
        输入：s = "3[a]2[bc]"
        输出："aaabcbc"
    示例 2：
        输入：s = "3[a2[c]]"
        输出："accaccacc"
    示例 3：
        输入：s = "2[abc]3[cd]ef"
        输出："abcabccdcdcdef"
    示例 4：
        输入：s = "abc3[cd]xyz"
        输出："abccdcdcdxyz"
     */
    public String decodeString(String s) {
        // 使用两个栈进行存储
        StringBuilder res = new StringBuilder();
        Stack<Integer> mulStack = new Stack<>();
        Stack<String> pathStack = new Stack<>();
        int mul = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                mulStack.push(mul);
                pathStack.push(res.toString());
                mul = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                Integer curMul = mulStack.pop();
                for (int i = 0; i < curMul; i++) {
                    temp.append(res);
                }
                res = new StringBuilder(pathStack.pop() + temp);
            } else if (c >= '0' && c <= '9') {
                mul = mul * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    static class Preview_1_29 {
        public String decodeString(String s) {
            // 使用两个栈进行存储
            Stack<Integer> mulStack = new Stack<>();
            Stack<String> pathStack = new Stack<>();
            int mul = 0;
            StringBuilder res = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c == '[') {
                    mulStack.push(mul);
                    pathStack.push(res.toString());
                    mul = 0;
                    res = new StringBuilder();
                } else if (c == ']') {
                    StringBuilder temp = new StringBuilder();
                    Integer curMul = mulStack.pop();
                    for (int i = 0; i < curMul; i++) {
                        temp.append(res);
                    }
                    res = new StringBuilder(pathStack.pop() + temp);
                } else if (c >= '0' && c <= '9') {
                    mul = mul * 10 + Integer.parseInt(c + "");
                } else {
                    res.append(c);
                }
            }
            return res.toString();
        }

    }

}

