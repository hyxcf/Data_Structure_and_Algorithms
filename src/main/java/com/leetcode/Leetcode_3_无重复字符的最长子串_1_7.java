package com.leetcode;

import org.checkerframework.checker.units.qual.A;

import javax.swing.text.html.CSS;
import java.util.HashMap;
import java.util.Map;

public class Leetcode_3_无重复字符的最长子串_1_7 {

    /*
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
        示例 1:
            输入: s = "abcabcbb"
            输出: 3 
            解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
        示例 2:
            输入: s = "bbbbb"
            输出: 1
            解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        示例 3:
            输入: s = "pwwkew"
            输出: 3
            解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
                 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
    */
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        // 滑动窗口
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }
            map.put(c, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    // 无重复字符的最长子串
    private static class Preview_2_11 {
        public int lengthOfLongestSubstring(String s) {
            if (s.isEmpty()) return 0;
            Map<Character, Integer> map = new HashMap<>();
            int maxLength = 0;
            for (int start = 0, end = 0; end < s.length(); end++) {
                char c = s.charAt(end);
                if (map.containsKey(c)) {
                    start = Math.max(start, map.get(c) + 1);
                }
                map.put(c, end);
                maxLength = Math.max(maxLength, end - start + 1);
            }
            return maxLength;
        }
    }

    private static class Preview_3_14 {
        /*
            给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
                示例 1:
                    输入: s = "abcabcbb"
                    输出: 3
                    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
                示例 2:
                    输入: s = "bbbbb"
                    输出: 1
                    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
                示例 3:
                    输入: s = "pwwkew"
                    输出: 3
                    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
                         请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
        */
        public int lengthOfLongestSubstring(String s) {
            if (s.isEmpty()) return 0;
            Map<Character, Integer> map = new HashMap<>();
            int maxLength = 0;
            for (int start = 0, end = 0; end < s.length(); end++) {
                char c = s.charAt(end);
                if (map.containsKey(c)) {
                    start = Math.max(start, map.get(c) + 1);
                }
                map.put(c, end);
                maxLength = Math.max(maxLength, end - start + 1);
            }
            return maxLength;
        }

    }

    /*
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
        示例 1:
            输入: s = "abcabcbb"
            输出: 3
            解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
        示例 2:
            输入: s = "bbbbb"
            输出: 1
            解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        示例 3:
            输入: s = "pwwkew"
            输出: 3
            解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
                 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
    */
    private static class Preview_6_29 {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            Map<Character, Integer> map = new HashMap<>();
            int max = 0;
            for (int start = 0, end = 0; end < s.length(); end++) {
                char charEnd = s.charAt(end);
                if (map.containsKey(charEnd)) {
                    start = Math.max(start, map.get(charEnd) + 1);
                }
                map.put(charEnd, end);
                max = Math.max(max, end - start + 1);
            }
            return max;
        }
    }
}
