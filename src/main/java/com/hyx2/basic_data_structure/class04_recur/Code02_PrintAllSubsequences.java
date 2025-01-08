package com.hyx2.basic_data_structure.class04_recur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author hyx
 * @version 0.1
 * @className Code02_PrintAllSubsequences
 * @date 2025/1/8 20:44
 * @description · 打印一个字符串的全部子序列 abcd
 * @since jdk 11
 */
public class Code02_PrintAllSubsequences {

    public static void main(String[] args) {
        List<String> list = subs("abca");
        HashSet<String> set = subs2("abca");

        System.out.println(list);
        System.out.println(set);
    }

    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    public static HashSet<String> subs2(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> ans = new HashSet<>();
        process2(str, 0, ans, path);
        return ans;
    }

    // · 打印一个字符串的全部子序列
    // str 固定，不变
    // index 此时来到的位置，要 or 不要
    // 如果 index 来到了 str 中的终止位置，把沿途路径所形成的答案，放入ans中
    // 之前做出的选择，就是 path
    private static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        String no = path;
        process1(str, index + 1, ans, no);
        String yes = path + String.valueOf(str[index]);
        process1(str, index + 1, ans, yes);
    }


    // · 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    private static void process2(char[] str, int index, HashSet<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, ans, no);
        String yes = path + str[index];
        process2(str, index + 1, ans, yes);

    }

}
