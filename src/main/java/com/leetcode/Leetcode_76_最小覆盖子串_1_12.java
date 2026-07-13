package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_76_最小覆盖子串_1_12 {

    /*
        给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
        测试用例保证答案唯一。 
        示例 1：
            输入：s = "ADOBECODEBANC", t = "ABC"
            输出："BANC"
            解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
        示例 2：
            输入：s = "a", t = "a"
            输出："a"
            解释：整个字符串 s 是最小覆盖子串。
        示例 3:
            输入: s = "a", t = "aa"
            输出: ""
            解释: t 中两个字符 'a' 均应包含在 s 的子串中，
        因此没有符合条件的子字符串，返回空字符串。
     */
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (s.isEmpty() || t.isEmpty() || sLen < tLen) {
            return "";
        }
        int[] tMap = new int[128];
        int[] sMap = new int[128];
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        for (char c : tCharArray) {
            tMap[c]++;
        }
        int count = 0;
        int minLen = sLen + 1;
        int reStart = 0;
        int left = 0;
        for (int right = 0; right < sLen; right++) {
            char charRight = sCharArray[right];
            if (tMap[charRight] > 0) {
                sMap[charRight]++;
                if (sMap[charRight] <= tMap[charRight]) {
                    count++;
                }
            }
            while (count == tLen) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    reStart = left;
                }
                char charLeft = sCharArray[left];
                if (tMap[charLeft] > 0) {
                    sMap[charLeft]--;
                    if (sMap[charLeft] < tMap[charLeft]) {
                        count--;
                    }
                }
                left++;
            }

        }
        return minLen > sLen ? "" : s.substring(reStart, reStart + minLen);
    }

    // 最小覆盖子串
    private static class Preview_2_11 {
        public String minWindow(String s, String t) {
            int sLen = s.length();
            int tLen = t.length();
            if (sLen == 0 || tLen == 0 || sLen < tLen) {
                return "";
            }
            char[] sMap = new char[128];
            char[] tMap = new char[128];
            char[] sCharArray = s.toCharArray();
            char[] tCharArray = t.toCharArray();
            for (char c : tCharArray) {
                tMap[c]++;
            }
            int count = 0;
            int left = 0;
            int restart = 0;
            int minLen = sLen + 1;
            for (int right = 0; right < sLen; right++) {
                char charRight = sCharArray[right];
                if (tMap[charRight] > 0) {
                    sMap[charRight]++;
                    if (sMap[charRight] <= tMap[charRight]) {
                        count++;
                    }
                }
                while (count == tLen) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        restart = left;
                    }
                    char charLeft = sCharArray[left];
                    if (tMap[charLeft] > 0) {
                        sMap[charLeft]--;
                        if (sMap[charLeft] < tMap[charLeft]) {
                            count--;
                        }
                    }
                    left++;
                }
            }
            return minLen > sLen ? "" : s.substring(restart, restart + minLen);
        }
    }

    private static class Preview_3_17 {
        /*
            给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
            测试用例保证答案唯一。
            示例 1：
                输入：s = "ADOBECODEBANC", t = "ABC"
                输出："BANC"
                解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
            示例 2：
                输入：s = "a", t = "a"
                输出："a"
                解释：整个字符串 s 是最小覆盖子串。
            示例 3:
                输入: s = "a", t = "aa"
                输出: ""
                解释: t 中两个字符 'a' 均应包含在 s 的子串中，
            因此没有符合条件的子字符串，返回空字符串。
        */
        public String minWindow(String s, String t) {
            int sLen = s.length();
            int tLen = t.length();
            if (sLen < tLen || sLen == 0 || tLen == 0) {
                return "";
            }
            char[] sMap = new char[128];
            char[] tMap = new char[128];
            char[] sCharArray = s.toCharArray();
            char[] tCharArray = t.toCharArray();
            for (char c : tCharArray) {
                tMap[c]++;
            }
            int count = 0;
            int left = 0;
            int reStart = 0;
            int minLen = sLen + 1;
            for (int right = 0; right < sLen; right++) {
                char charRight = sCharArray[right];
                if (tMap[charRight] > 0) {
                    sMap[charRight]++;
                    if (sMap[charRight] <= tMap[charRight]) {
                        count++;
                    }
                }
                while (count == tLen) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        reStart = left;
                    }
                    char charLeft = sCharArray[left];
                    if (tMap[charLeft] > 0) {
                        sMap[charLeft]--;
                        if (sMap[charLeft] < tMap[charLeft]) {
                            count--;
                        }
                    }
                    left++;
                }
            }
            return minLen > sLen ? "" : s.substring(reStart, reStart + minLen);
        }
    }

    private static class Preview_4_23 {
        /*
            给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
            测试用例保证答案唯一。
            示例 1：
                输入：s = "ADOBECODEBANC", t = "ABC"
                输出："BANC"
                解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
            示例 2：
                输入：s = "a", t = "a"
                输出："a"
                解释：整个字符串 s 是最小覆盖子串。
            示例 3:
                输入: s = "a", t = "aa"
                输出: ""
                解释: t 中两个字符 'a' 均应包含在 s 的子串中，
            因此没有符合条件的子字符串，返回空字符串。
        */
        public String minWindow(String s, String t) {
            // 滑动窗口
            int sLen = s.length();
            int tLen = t.length();
            if (sLen < tLen || sLen == 0 || tLen == 0) {
                return "";
            }
            char[] sMap = new char[128];
            char[] tMap = new char[128];
            char[] sCharArray = s.toCharArray();
            char[] tCharArray = t.toCharArray();
            for (char c : tCharArray) {
                tMap[c]++;
            }
            int count = 0;
            int left = 0;
            int restart = 0;
            int minLen = sLen + 1;
            for (int right = 0; right < sLen; right++) {
                char charRight = sCharArray[right];
                if (tMap[charRight] > 0) {
                    sMap[charRight]++;
                    if (sMap[charRight] <= tMap[charRight]) {
                        count++;
                    }
                }
                while (count == tLen) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        restart = left;
                    }
                    char charLeft = sCharArray[left];
                    if (tMap[charLeft] > 0) {
                        sMap[charLeft]--;
                        if (sMap[charLeft] < tMap[charLeft]) {
                            count--;
                        }
                    }
                    left++;
                }
            }
            return minLen > sLen ? "" : s.substring(restart, restart + minLen);
        }
    }

    private static class Preview_7_13 {
        /*
            给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
            测试用例保证答案唯一。
            示例 1：
                输入：s = "ADOBECODEBANC", t = "ABC"
                输出："BANC"
                解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
            示例 2：
                输入：s = "a", t = "a"
                输出："a"
                解释：整个字符串 s 是最小覆盖子串。
            示例 3:
                输入: s = "a", t = "aa"
                输出: ""
                解释: t 中两个字符 'a' 均应包含在 s 的子串中，
            因此没有符合条件的子字符串，返回空字符串。
        */
        public String minWindow(String s, String t) {
            // 滑动窗口
            int sLen = s.length();
            int tLen = t.length();
            if (sLen < tLen || sLen == 0 || tLen == 0) {
                return "";
            }
            Map<Character, Integer> tCountMap = new HashMap<>();
            Map<Character, Integer> sCountMap = new HashMap<>();
            for (char c : t.toCharArray()) {
                tCountMap.put(c, tCountMap.getOrDefault(c, 0) + 1);
            }
            int count = 0;
            int left = 0;
            int reStart = 0;
            int minLen = sLen + 1;
            char[] sCharArray = s.toCharArray();
            for (int right = 0; right < sLen; right++) {
                char sChar = sCharArray[right];
                if (tCountMap.containsKey(sChar)) {
                    sCountMap.put(sChar, sCountMap.getOrDefault(sChar, 0) + 1);
                    if (sCountMap.get(sChar) <= tCountMap.get(sChar)) {
                        count++;
                    }
                }
                while (count == tLen) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        reStart = left;
                    }
                    char sCharLeft = sCharArray[left];
                    if (tCountMap.containsKey(sCharLeft)) {
                        sCountMap.put(sCharLeft, sCountMap.getOrDefault(sCharLeft, 0) - 1);
                        if (sCountMap.get(sCharLeft) < tCountMap.get(sCharLeft)) {
                            count--;
                        }
                    }
                    left++;
                }
            }
            return minLen > sLen ? "" : s.substring(reStart, reStart + minLen);
        }

        public String minWindow2(String s, String t) {
            // 滑动窗口
            int sLen = s.length();
            int tLen = t.length();
            if (sLen < tLen || sLen == 0 || tLen == 0) {
                return "";
            }
            char[] sMap = new char[128];
            char[] tMap = new char[128];
            char[] sCharArray = s.toCharArray();
            char[] tCharArray = t.toCharArray();
            for (char c : tCharArray) {
                tMap[c]++;
            }
            int count = 0;
            int left = 0;
            int restart = 0;
            int minLen = sLen + 1;
            for (int right = 0; right < sLen; right++) {
                char charRight = sCharArray[right];
                if (tMap[charRight] > 0) {
                    sMap[charRight]++;
                    if (sMap[charRight] <= tMap[charRight]) {
                        count++;
                    }
                }
                while (count == tLen) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        restart = left;
                    }
                    char charLeft = sCharArray[left];
                    if (tMap[charLeft] > 0) {
                        sMap[charLeft]--;
                        if (sMap[charLeft] < tMap[charLeft]) {
                            count--;
                        }
                    }
                    left++;
                }
            }
            return minLen > sLen ? "" : s.substring(restart, restart + minLen);
        }

    }

}