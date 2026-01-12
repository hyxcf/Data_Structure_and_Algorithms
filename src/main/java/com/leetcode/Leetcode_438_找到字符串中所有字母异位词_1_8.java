package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_438_找到字符串中所有字母异位词_1_8 {

/*
    给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

    示例 1:
    输入: s = "cbaebabacd", p = "abc"
    输出: [0,6]
    解释:
        起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
        起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
    示例 2:
    输入: s = "abab", p = "ab"
    输出: [0,1,2]
    解释:
        起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
        起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
        起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */

    public List<Integer> findAnagrams(String s, String p) {
        // 整体思路：就是将p挪进数组中，然后去遍历s，满足长度则比较是否是字母异位词
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        List<Integer> res = new ArrayList<>();
        for (char c : p.toCharArray()) {
            pArr[c - 'a']++;
        }
        char[] sChars = s.toCharArray();
        for (int l = 0, r = 0; r < s.length(); r++) {
            sArr[sChars[r] - 'a']++;
            if (r - l + 1 > p.length()) {
                sArr[sChars[l++] - 'a']--;
            }
            if (r - l + 1 == p.length()) {
                if (isSame(sArr, pArr)) {
                    res.add(l);
                }
            }
        }
        return res;
    }

    private boolean isSame(int[] sArr, int[] pArr) {
        for (int i = 0; i < pArr.length; i++) {
            if (pArr[i] != sArr[i]) {
                return false;
            }
        }
        return true;
    }

    static class previewSolution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            char[] sArr = new char[26];
            char[] pArr = new char[26];
            char[] chars = p.toCharArray();
            for (char c : chars) {
                pArr[c - 'a']++;
            }
            char[] sChars = s.toCharArray();
            for (int start = 0, end = 0; end < sChars.length; end++) {
                sArr[sChars[end] - 'a']++;
                if (end - start + 1 > p.length()) {
                    sArr[sChars[start++] - 'a']--;
                }
                if ((end - start + 1 == p.length())) {
                    if (isSame(sArr, pArr)) {
                        res.add(start);
                    }
                }
            }
            return res;
        }

        private boolean isSame(char[] sArr, char[] pArr) {
            for (int i = 0; i < pArr.length; i++) {
                if (pArr[i] != sArr[i]) {
                    return false;
                }
            }
            return true;
        }
    }

}
