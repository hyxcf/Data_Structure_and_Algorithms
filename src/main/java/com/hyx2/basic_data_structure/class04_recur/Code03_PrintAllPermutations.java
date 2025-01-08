package com.hyx2.basic_data_structure.class04_recur;

import java.util.ArrayList;

/**
 * @author hyx
 * @version 0.1
 * @className Code03_PringAllPermutations
 * @date 2025/1/8 21:28
 * @description
 * @since jdk 11
 */
public class Code03_PrintAllPermutations {

    public static ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs, 0, res);
        return res;
    }

    // 打印一个字符串的全部排列
    // str[0...i-1] 已经做好决定的
    // str[i...] 都有机会来到i位置
    // i 终止位置，str当前的样子，就是一种结果 -> ans
    private static void process(char[] str, int i, ArrayList<String> ans) {
        if (i == str.length) {
            ans.add(String.valueOf(str));
        }
        // 如果 i 没有终止 i... 都可以来到i位置
        for (int j = i; j < str.length; j++) { // j i 后面的字符都有机会
            swap(str, i, j);
            process(str, i + 1, ans);
            swap(str, i, j);
        }
    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    // 打印一个字符串的全部排列，要求不要出现重复的排列
    public static ArrayList<String> permutationNoRepeat(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return res;
        }
        char[] chs = str.toCharArray();
        process2(chs, 0, res);
        return res;
    }

    // 剪支
    private static void process2(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26];
        for (int j = i; j < str.length; j++) {
            // 每次进入循环时都会判断当前字符是否出现过
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }
}
