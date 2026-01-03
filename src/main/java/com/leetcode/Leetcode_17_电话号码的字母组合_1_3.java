package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyx
 * @version 0.1
 * @className Leetcode_17_电话号码的字母组合_1_3
 * @date 2026/1/3 17:51
 * @description
 * @since jdk 11
 */
public class Leetcode_17_电话号码的字母组合_1_3 {

    /*
                (i=0)
               /   |   \
             a     b     c        ← path[0]
            /|\   /|\   /|\
           d e f d e f d e f      ← path[1]
          / / / / / / / / /
        ad ae af bd be bf cd ce cf
*/

    private static final String[] MAPPING = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if (n == 0) {
            return List.of();
        }
        List<String> ans = new ArrayList<>();
        char[] path = new char[n]; // 注意 path 长度一开始就是 n，不是空数组
        dfs(0, ans, path, digits.toCharArray());
        return ans;
    }

    private void dfs(int index, List<String> ans, char[] path, char[] digits) {
        if (index == digits.length) {
            ans.add(new String(path));
            return;
        }
        String letters = MAPPING[digits[index] - '0'];
        for (char c : letters.toCharArray()) {
            path[index] = c; // 直接覆盖
            dfs(index + 1, ans, path, digits);
        }
    }

}
