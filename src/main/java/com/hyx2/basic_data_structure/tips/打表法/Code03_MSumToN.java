package com.hyx2.basic_data_structure.tips.打表法;

/**
 * @version 0.1
 * @Author hyx
 * @className Code03_MSumToN
 * @Date 2024/11/6  22:37
 * @description
 * @since jdk 11
 */
public class Code03_MSumToN {

    public static boolean isMSum1(int num) {
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    // num 是不是2的某次方，
    // (num & (num - 1)) != 0 不是2的某次方
    public static boolean isMSum2(int num) {
        if (num < 3) {
            return false;
        }
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            System.out.println(i + " : " + isMSum1(i));
        }
    }

}
