package com.hyx.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 *
 * @author hyx
 */
public class E02Leetcode150 {

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        String[] tokens = {"4", "13", "5", "/", "+"};
        int result = evalRPN(tokens);
        System.out.println(result);
    }

    // 优秀解法
    public int evalRPN2(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                // 确实这里可以这样简化
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    /*
        将数字压栈，遇到运算符压栈，然后将结果再压入栈，遇到运算符再弹出栈
     */
    public static int evalRPN(String[] tokens) {
        ArrayStack<Integer> stack = new ArrayStack<>(tokens.length);
        // 遍历 传来的数组
        for (int i = 0; i < tokens.length; i++) {
//            Character character = tokens[i].charAt(0);
            String s = tokens[i];
            // 判断是否为运算符，不是则将数字压栈，是则弹出栈订前两个进行运算
            switch (s) {
                case "+":
                    Integer first1 = stack.pop();
                    Integer second1 = stack.pop();
                    stack.push(second1 + first1);
                    break;
                case "-":
                    Integer first2 = stack.pop();
                    Integer second2 = stack.pop();
                    stack.push(second2 - first2);
                    break;
                case "*":
                    Integer first3 = stack.pop();
                    Integer second3 = stack.pop();
                    stack.push(second3 * first3);
                    break;
                case "/":
                    Integer first4 = stack.pop();
                    Integer second4 = stack.pop();
                    stack.push(second4 / first4);
                    break;
                default:
                    // 说明是数字，进行压栈
                    stack.push(Integer.valueOf(s));
            }
        }
        return stack.peek();
    }

    public static class ArrayStack<E> {
        private final E[] array;
        private int top = 0;// 栈顶指针

        public ArrayStack(int capacity) {
            this.array = (E[]) new Object[capacity];
        }

        // 判断栈内元素是否已经满了
        public boolean isFull() {
            return top == array.length;// 当 top 指向数组长度时，说明栈已经满了
        }

        // 判断栈内元素是否为空
        public boolean isEmpty() {
            return top == 0;
        }

        // 压栈
        public boolean push(E value) {
            if (isFull()) {
                return false;
            }
            array[top++] = value;
            return true;
        }

        // 出栈
        public E pop() {
            if (isEmpty()) {
                return null;
            }
            return array[--top];
        }

        // 查看栈顶元素
        public E peek() {
            if (isEmpty()) {
                return null;
            }
            return array[top - 1];
        }
    }


}
