package com.hyx.recursion;

import java.util.LinkedList;

/**
 * hyx
 * 汉诺塔问题
 */
public class E02HanoiTower {

    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    // 3 2 1
    static void init(int n) {
        for (int i = n; i >= 1; i--) {
            a.addLast(i);
        }
    }

    /**
     * @param n 圆盘个数
     * @param a 源
     * @param b 借
     * @param c 至
     */
    static void move(int n,
                     LinkedList<Integer> a,
                     LinkedList<Integer> b,
                     LinkedList<Integer> c) {
        if (n == 0) {
            return;
        }
        move(n - 1, a, c, b);// 把 n-1 个盘子由a,到c,移至b
        c.addLast(a.removeLast());
        printInfo();
        move(n - 1, b, a, c);// 把 n-1 个盘子由b,到a,移至c
    }


    public static void main(String[] args) {
        init(3);
        printInfo();
        move(3, a, b, c);

    }

    private static void printInfo() {
        System.out.println("-----------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
