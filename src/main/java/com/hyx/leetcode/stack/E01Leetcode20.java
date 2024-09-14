package com.hyx.leetcode.stack;

/**
 * @author hyx
 */
public class E01Leetcode20 {

    /*
                (   [    {

                底 - 栈 - 顶

           栈： )    ]    }
        遇到左括号，把要配对的元素放入栈顶
        遇到右括号，把它于栈顶元素对比
            若相等，栈顶元素弹出，继续对比下一组
            若不等，无效括号直接返回false
     */

    public static boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '[' -> stack.push(']');
                case '{' -> stack.push('}');
                case '(' -> stack.push(')');
                default -> {
                    // 如果是右括号，则判断右括号和栈顶元素是否相等
                    if (!stack.isEmpty() && c == stack.peek()) { // fixme: 这里需要判断stack是否为空的情况
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        // 只需要判断栈内是否还有元素
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("["));
    }

}
