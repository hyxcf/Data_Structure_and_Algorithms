package com.hyx2.basic_data_structure.class04_recur;

/**
 * @author hyx
 * @version 0.1
 * @className Code01_Hanoi
 * @date 2025/1/6 10:58
 * @description 汉诺塔问题
 * @since jdk 11
 */
public class Code01_Hanoi {

    public static void main(String[] args) {
        hanoi(4);
    }

    public static void hanoi(int n) {
        if (n > 0) {
            process(n, "left", "mid", "right");
        }
    }

    private static void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            process(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            process(n - 1, other, to, from);
        }
    }

}
