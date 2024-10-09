package com.hyx.leetcode.hash;

import java.util.HashMap;

/**
 * @version 0.1
 * @Author hyx
 * @className E02LeetCode3
 * @Date 2024/10/8  22:42
 * @description 无重复字符的最长子串
 * @since jdk 11
 */
public class E02LeetCode3 {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * a   b   c   a   b   c
     * b
     * e
     * a b b a
     * b
     * e
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        // 定义头尾指针
        int begin = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                begin = Math.max(begin, map.get(c) + 1);
                map.put(c, end);
            } else {
                map.put(c, end);
            }
            maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }
}
