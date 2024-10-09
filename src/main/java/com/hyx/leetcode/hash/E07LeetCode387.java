package com.hyx.leetcode.hash;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className E07LeetCode387
 * @Date 2024/10/9  22:12
 * @description 字符串中的第一个唯一字符
 * @since jdk 11
 */
public class E07LeetCode387 {

    /*
        输入: s = "leetcode"
        输出: 0

        输入: s = "loveleetcode"
        输出: 2

        输入: s = "aabb"
        输出: -1
     */
    // 思路：字符打散放入 int[26]，获取符合条件的最小的索引
    public int firstUniqChar(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray(); // leetcode
        for (char ch : chars) {
            array[ch - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (array[ch - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new E07LeetCode387().firstUniqChar("leetcode"));
    }

}
