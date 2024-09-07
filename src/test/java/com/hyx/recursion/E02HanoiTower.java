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

    public static void main(String[] args) {

    }

}
