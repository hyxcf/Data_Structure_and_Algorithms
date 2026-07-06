package com.leetcode;

import java.util.*;

public class Leetcode_49_字母异位词分组_1_5 {

    /*
        给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    
        示例 1:
            输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
            输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
        解释：
            在 strs 中没有字符串可以通过重新排列来形成 "bat"。
            字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
            字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            char[] temp = new char[26];
            for (char c : chars) {
                temp[c - 'a']++;
            }
            map.computeIfAbsent(String.valueOf(temp), v -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    // 字母异位词分组
    private static class Preview_2_11 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = new char[26];
                for (char c : str.toCharArray()) {
                    chars[c - 'a']++;
                }
                map.computeIfAbsent(Arrays.toString(chars), k -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }

    private static class Preview_3_17 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = new char[26];
                for (char c : str.toCharArray()) {
                    chars[c - 'a']++;
                }
                map.computeIfAbsent(Arrays.toString(chars), k -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }

    private static class Preview_7_6 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = new char[26];
                for (char c : str.toCharArray()) {
                    chars[c - 'a']++;
                }
                map.computeIfAbsent(Arrays.toString(chars), k -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }

}
