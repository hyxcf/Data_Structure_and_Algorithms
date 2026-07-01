package com.leetcode;

import java.util.Stack;

/**
 * @author hyx
 * @version 0.1
 * @className LeetCode_20_有效的括号_1_25
 * @date 2026/1/25 15:15
 * @description
 * @since jdk 11
 */
public class LeetCode_20_有效的括号_1_25 {

    /*
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        每个右括号都有一个对应的相同类型的左括号。
        示例 1：
            输入：s = "()"
            输出：true
        示例 2：
            输入：s = "()[]{}"
            输出：true
        示例 3：
            输入：s = "(]"
            输出：false
        示例 4：
            输入：s = "([])"
            输出：true
        示例 5：
            输入：s = "([)]"
            输出：false
     */
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    static class Preview_1_29 {
        public boolean isValid(String s) {
            if (s.length() % 2 == 1)
                return false;
            Stack<Character> st = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    st.push(')');
                } else if (c == '{') {
                    st.push('}');
                } else if (c == '[') {
                    st.push(']');
                } else if (st.isEmpty() || st.pop() != c) {
                    return false;
                }
            }
            return st.isEmpty(); // fixme:这里应该判断栈是否为空
        }
    }

    private static class Preview_3_2 {
        public boolean isValid(String s) {
            if (s.length() % 2 == 1) {
                return false;
            }
            Stack<Character> st = new Stack();
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    st.push(')');
                } else if (c == '{') {
                    st.push('}');
                } else if (c == '[') {
                    st.push(']');
                } else if (st.isEmpty() || st.pop() != c) {
                    return false;
                }
            }
            return st.isEmpty();
        }
    }

    private static class Preview_3_16 {
        /*
            给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
            有效字符串需满足：
            左括号必须用相同类型的右括号闭合。
            左括号必须以正确的顺序闭合。
            每个右括号都有一个对应的相同类型的左括号。
            示例 1：
                输入：s = "()"
                输出：true
            示例 2：
                输入：s = "()[]{}"
                输出：true
            示例 3：
                输入：s = "(]"
                输出：false
            示例 4：
                输入：s = "([])"
                输出：true
            示例 5：
                输入：s = "([)]"
                输出：false
                典型的栈解法
        */
        public boolean isValid(String s) {
            if (s.length() % 2 == 1) {
                return false;
            }
            Stack<Character> stack = new Stack<>();
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                if (c == '(') {
                    stack.push(')');
                } else if (c == '[') {
                    stack.push(']');
                } else if (c == '{') {
                    stack.push('}');
                } else if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
            return stack.isEmpty();
        }

    }

    private static class Preview_7_1 {
        /*
            给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
            有效字符串需满足：
            左括号必须用相同类型的右括号闭合。
            左括号必须以正确的顺序闭合。
            每个右括号都有一个对应的相同类型的左括号。
            示例 1：
                输入：s = "()"
                输出：true
            示例 2：
                输入：s = "()[]{}"
                输出：true
            示例 3：
                输入：s = "(]"
                输出：false
            示例 4：
                输入：s = "([])"
                输出：true
            示例 5：
                输入：s = "([)]"
                输出：false
                典型的栈解法
        */
        public boolean isValid(String s) {
            if (s.length() % 2 == 1) {
                return false;
            }
            Stack<Character> stack = new Stack<>();
            char[] sCharArray = s.toCharArray();
            for (char c : sCharArray) {
                if (c == '(') {
                    stack.push(')');
                } else if (c == '[') {
                    stack.push(']');
                } else if (c == '{') {
                    stack.push('}');
                } else if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
            return stack.isEmpty(); // fixme:这里一定要判定栈是否为空
        }
    }

}
