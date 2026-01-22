package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_22_括号生成_1_21 {

    /*
        数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
        示例 1：
            输入：n = 3
            输出：["((()))","(()())","(())()","()(())","()()()"]
        示例 2：   
            输入：n = 1
            输出：["()"]
     */
    // 当 ) 的个数 大于 ( 的个数，直接剪枝
    // 当 ) 的个数 等于 ( 的个数 = n，则直接添加
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return List.of();
        }
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtracking(sb, 0, 0, n, ans);
        return ans;
    }

    private void backtracking(StringBuilder sb, int leftCount, int rightCount, int n, List<String> ans) {
        if (rightCount > leftCount) {
            return;
        }
        // 终止条件
        if (leftCount == n && rightCount == n) {
            ans.add(sb.toString());
            return;
        }
        // 尝试添加左括号
        if (leftCount < n) {
            sb.append("(");
            backtracking(sb, leftCount + 1, rightCount, n, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rightCount < leftCount) {
            sb.append(")");
            backtracking(sb, leftCount, rightCount + 1, n, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


}
