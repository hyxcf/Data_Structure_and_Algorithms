package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyx
 * @version 0.1
 * @className Leetcode_93_复原IP地址_1_18
 * @date 2026/1/18 22:29
 * @description
 * @since jdk 11
 */
public class Leetcode_93_复原IP地址_1_18 {

    /*
        有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
        例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
        给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
        示例 1：
            输入：s = "25525511135"
            输出：["255.255.11.135","255.255.111.35"]
        示例 2：
            输入：s = "0000"
            输出：["0.0.0.0"]
        示例 3：
            输入：s = "101023"
            输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     */

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(s), 0, 0);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder s, int startIndex, int pointNum) {
        if (pointNum == 3) {
            if (!isValid(s, startIndex, s.length() - 1)) {
                return;
            } else {
                res.add(s.toString());
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s.insert(i + 1, ".");
                pointNum++;
                backtrack(res, s, i + 2, pointNum);
                pointNum--;
                s.deleteCharAt(i + 1);
            } else {
                break;
            }
        }
    }

    private boolean isValid(StringBuilder s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) {
                return false;
            }
        }
        return true;
    }


    private static class Preview_3_18 {
        /*
            有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
            例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
            给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
            示例 1：
                输入：s = "25525511135"
                输出：["255.255.11.135","255.255.111.35"]
            示例 2：
                输入：s = "0000"
                输出：["0.0.0.0"]
            示例 3：
                输入：s = "101023"
                输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
        */

        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            backtracking(0, 0, new StringBuilder(s), res);
            return res;
        }

        private void backtracking(int startIndex, int pointNum, StringBuilder s, List<String> res) {
            if (pointNum == 3) {
                if (!isValid(s, startIndex, s.length() - 1)) {
                    return;
                } else {
                    res.add(s.toString());
                }
                return;
            }
            for (int i = startIndex; i < s.length(); i++) {
                if (isValid(s, startIndex, i)) {
                    s.insert(i + 1, ".");
                    pointNum++;
                    backtracking(i + 2, pointNum, s, res);
                    pointNum--;
                    s.deleteCharAt(i + 1);
                } else {
                    break;
                }
            }
        }

        private boolean isValid(StringBuilder s, int start, int end) {
            if (start > end) {
                return false;
            }
            if (s.charAt(start) == '0' && start != end) {
                return false;
            }
            int num = 0;
            for (int i = start; i <= end; i++) {
                if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                    return false;
                }
                num = num * 10 + (s.charAt(i) - '0');
                if (num > 255) {
                    return false;
                }
            }
            return true;
        }

    }

    private static class Preview_4_22 {

        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            backtracking(0, 0, new StringBuilder(s), res);
            return res;
        }

        private void backtracking(int startIndex, int point, StringBuilder sb, List<String> res) {
            if (point == 3) {
                if (!isValid(sb, startIndex, sb.length() - 1)) {
                    return;
                } else {
                    res.add(sb.toString());
                }
                return;
            }
            for (int i = startIndex; i < sb.length(); i++) {
                if (isValid(sb, startIndex, i)) {
                    sb.insert(i + 1, ".");
                    point++;
                    backtracking(i + 2, point, sb, res);
                    point--;
                    sb.deleteCharAt(i + 1);
                } else {
                    break;
                }
            }

        }

        private boolean isValid(StringBuilder sb, int start, int end) {
            if (start > end) {
                return false;
            }
            if (sb.charAt(start) == '0' && start != end) {
                return false;
            }
            int num = 0;
            for (int i = start; i <= end; i++) {
                if (sb.charAt(i) < '0' || sb.charAt(i) > '9') {
                    return false;
                }
                num = num * 10 + (sb.charAt(i) - '0');
                if (num > 255) {
                    return false;
                }
            }
            return true;
        }


    }

    /*
        有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
        例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
        给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
        示例 1：
            输入：s = "25525511135"
            输出：["255.255.11.135","255.255.111.35"]
        示例 2：
            输入：s = "0000"
            输出：["0.0.0.0"]
        示例 3：
            输入：s = "101023"
            输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     */
    private static class Preview_7_14 {

        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            backtrack(0, 0, new StringBuilder(s), res);
            return res;
        }

        private void backtrack(int startIndex, int point, StringBuilder sb, List<String> res) {
            if (point == 3) {
                if (!isValided(sb, startIndex, sb.length() - 1)) {
                    return;
                } else {
                    res.add(sb.toString());
                }
                return;
            }
            for (int i = startIndex; i < sb.length(); i++) {
                if (isValided(sb, startIndex, i)) {
                    sb.insert(i + 1, ".");
                    point++;
                    backtrack(i + 2, point, sb, res);
                    point--;
                    sb.deleteCharAt(i + 1);
                } else {
                    break;
                }
            }
        }

        private boolean isValided(StringBuilder sb, int start, int end) {
            if (start > end) {
                return false;
            }
            if (sb.charAt(start) == '0' && start != end) {
                return false;
            }
            int num = 0;
            for (int i = start; i <= end; i++) {
                if (sb.charAt(i) < '0' || sb.charAt(i) > '9') {
                    return false;
                }
                num = num * 10 + (sb.charAt(i) - '0');
                if (num > 255) {
                    return false;
                }
            }
            return true;
        }

    }
}
