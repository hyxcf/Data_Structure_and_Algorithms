package com.hyx2.basic_data_structure.tips.打表法;

/**
 * @version 0.1
 * @Author hyx
 * @className Code02_EatGrass
 * @Date 2024/11/6  22:02
 * @description
 * @since jdk 11
 */
public class Code02_EatGrass {

    // n 份青草放在一堆
    // 先手后手都绝顶聪明
    // string "先手" "后手"
    public static String winner1(int n) { // 先手
        // 0  1  2  3 4
        // 后 先 后 先 先
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        // 当 n >= 5 时
        int base = 1; // 当前先手决定吃的草数
        // 当前是先手在选
        while (base <= n) {
            // 当前一共n份草，先手吃掉的是base份，n-base是留给后手
            if (winner1(n - base).equals("后手")) {
                return "先手";
            }
            if (base > n / 4) {
                break; // 防止 base * 4 溢出 ,Integer.maxValue * 4 = 负数，会出现不断循环的现象
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i + " " + winner2(i));
        }
    }


}
