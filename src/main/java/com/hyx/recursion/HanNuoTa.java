package com.hyx.recursion;

/**
 * @Author：hyx
 * @Date：2024/9/27 9:46
 * 汉诺塔问题
 */
public class HanNuoTa {
    public static void main(String[] args) {
        tower(3,'a','b','c');
    }

    public static void tower(int n, char a, char b, char c) {

        if (n == 1) {
            System.out.println(a + "->" + c);
        } else {
            tower(n - 1, a, c, b);
            System.out.println(a + "->" + c);
            tower(n - 1, b, a, c);
        }


    }


}
