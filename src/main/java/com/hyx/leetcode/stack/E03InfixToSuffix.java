/*
package com.hyx.leetcode.stack;

import java.util.LinkedList;

*/
/**
 * 中缀表达式转后缀表达式
 *
 * @author hyx
 *//*


public class E03InfixToSuffix {

    */
/*
        a+b           ab+
        a+b-c         ab+c-
        a*b+c         ab*c+
        a+b*c-d       abc*+d-
        (a+b)*c       ab+c*
        (a+b*c-d)*e   abc*+d-e*
        a*(b+c)       abc+*

        1. 遇到非运算符 直接拼串
        2. 遇到 + - * /
            - 它的优先级比栈顶运算符高，入栈
            - 否则把栈里优先级 >= 它 的都出栈，它再入栈
        3. 遍历完成，栈里剩余运算符依次出栈
        4. 带()
            - 左括号直接入栈，左括号的优先级设置为0
            - 右括号就把栈顶到左括号为止的所有运算符出栈
     *//*


    public static void main(String[] args) {
//        System.out.println(infixToSuffix("a+b"));
//        System.out.println(infixToSuffix("a+b-c"));
//        System.out.println(infixToSuffix("a*b+c"));
//        System.out.println(infixToSuffix("a+b*c-d"));
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("(a+b*c-d)*e"));
        System.out.println(infixToSuffix("a*(b+c)"));

    }

    // 中缀转后缀
    public static String infixToSuffix(String exp) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case '+', '-', '*', '/' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        // 判断栈顶元素的优先级 和 要添加的优先级哪个更高
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            // 栈内元素优先级更高...
                            // 取出栈内元素，直到 要添加的元素优先级比 栈内的优先级更高时停止
                            // 判断栈内是否为空，为空则停止循环
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            // 等循环结束后，当前的运算符再入栈
                            stack.push(c);
                        }
                    }
                }
                case '(' -> {
                    stack.push(c);
                }
                case ')' -> {
                    while (!stack.isEmpty() && '(' != stack.peek()) {
                        sb.append(stack.pop());
                    }
                    stack.pop(); // 弹出左括号
                }

                default -> {
                    // 非运算符
                    sb.append(c);
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }


    // 设置 运算符号的优先级
    public static int priority(char c) {
        return switch (c) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case '(' -> 0;
            default -> throw new IllegalArgumentException("不合法的运算符");
        };
    }
}
*/
