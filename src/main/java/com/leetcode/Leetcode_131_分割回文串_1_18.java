package com.leetcode;

import com.hyx.graph.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyx
 * @version 0.1
 * @className Leetcode_131_分割回文串_1_18
 * @date 2026/1/18 16:45
 * @description
 * @since jdk 11
 */
public class Leetcode_131_分割回文串_1_18 {

    /*
        给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
        示例 1：
            输入：s = "aab"
            输出：[["a","a","b"],["aa","b"]]
        示例 2：
            输入：s = "a"
            输出：[["a"]]
     */

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtracking(0, s, path, res);
        return res;
    }

    private void backtracking(int start, String s, List<String> path, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                path.add(s.substring(start, end + 1));
                backtracking(end + 1, s, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    static class Preview_2_1 {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) {
                return res;
            }
            List<String> path = new ArrayList<>();
            backtrack(0, s, path, res);
            return res;
        }

        private void backtrack(int start, String s, List<String> path, List<List<String>> res) {
            if (start == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int end = start; end < s.length(); end++) {
                if (isHuiWen(start, end, s)) {
                    path.add(s.substring(start, end + 1));
                    backtrack(start + 1, s, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isHuiWen(int start, int end, String s) {
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class Preview_2_27 {
        /*
            给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
            示例 1：
                输入：s = "aab"
                输出：[["a","a","b"],["aa","b"]]
            示例 2：
                输入：s = "a"
                输出：[["a"]]
         */
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) {
                return res;
            }
            List<String> path = new ArrayList<>();
            backtracking(0, s, path, res);
            return res;
        }

        private void backtracking(int start, String s, List<String> path, List<List<String>> res) {
            if (start == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int end = start; end < s.length(); end++) {
                if (isHuiWen(start, end, s)) {
                    path.add(s.substring(start, end + 1));
                    backtracking(end + 1, s, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isHuiWen(int start, int end, String s) {
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class Preview_3_9 {
        /*
            给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
            示例 1：
                输入：s = "aab"
                输出：[["a","a","b"],["aa","b"]]
            示例 2：
                输入：s = "a"
                输出：[["a"]]
         */
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) {
                return res;
            }
            List<String> path = new ArrayList<>();
            backtrack(0, s, path, res);
            return res;
        }

        private void backtrack(int start, String s, List<String> path, List<List<String>> res) {
            if (start == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int end = start; end < s.length(); end++) {
                if (isHuiWen(s, start, end)) {
                    path.add(s.substring(start, end + 1));
                    backtrack(end + 1, s, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isHuiWen(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class Preview_3_19 {
        /*
            给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
            示例 1：
                输入：s = "aab"
                输出：[["a","a","b"],["aa","b"]]
            示例 2：
                输入：s = "a"
                输出：[["a"]]
        */
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) {
                return res;
            }
            List<String> path = new ArrayList<>();
            barktracking(0, s, path, res);
            return res;
        }

        private void barktracking(int index, String s, List<String> path, List<List<String>> res) {
            if (index == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = index; i < s.length(); i++) {
                if (isHuiWen(s, index, i)) {
                    path.add(s.substring(index, i + 1));
                    barktracking(i + 1, s, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isHuiWen(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class Preview_4_11 {
        /*
            给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
            示例 1：
                输入：s = "aab"
                输出：[["a","a","b"],["aa","b"]]
            示例 2：
                输入：s = "a"
                输出：[["a"]]
        */
        public List<List<String>> partition(String s) {
            if (s == null || s.isEmpty()) {
                return new ArrayList<>();
            }
            List<List<String>> res = new ArrayList<>();
            List<String> path = new ArrayList<>();
            backtrack(0, s, path, res);
            return res;
        }

        private void backtrack(int index, String s, List<String> path, List<List<String>> res) {
            if (index == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = index; i < s.length(); i++) {
                if (isHuiwen(s, index, i)) {
                    path.add(s.substring(index, i + 1));
                    backtrack(i + 1, s, path, res);
                    path.remove(path.size() - 1);
                }
            }
        }

        private boolean isHuiwen(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }
    }
}
