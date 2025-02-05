package com.hyx2.basic_data_structure.class04_recur;

/**
 * @author hyx
 * @version 0.1
 * @className Code06_ConvertToLetterString
 * @date 2025/2/5 19:45
 * @description 从左往右的尝试模型1
 * 规定1和A对应、2和B对应、3和C对应
 * 那么一个数字字符串比如"111"就可以转化为:
 * "AAA”、“KA”和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * @since jdk 11
 */
public class Code06_ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] str, int i) {
        if (i == str.length) { // 转化到底了
            return 1;
        }
        // i 没有到终止位置，从0开始
        if (str[i] == '0') {
            return 0; // 从 1~9 ，如果出现0了代表无法转换
        }
        if (str[i] == '1') {
            int res = process(str, i + 1);// 将 i 自己作为单独的一部分，得到后续有多少种方法
            if (i + 1 < str.length) {
                res += process(str, i + 2);// 将 (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);// 将 i 自己作为单独的一部分，得到后续有多少种方法
            // (i和i+1) 作为单独的部分并且没有超过26，后续有多少种方法
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] = '3'~'9l'
        return process(str, i + 1);
    }

}
