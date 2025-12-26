package com.hyx2.basic_data_structure.backtrack.回溯算法;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code01_全排列问题 {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[len];
        LinkedList<Integer> path = new LinkedList<>();
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, LinkedList<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                System.out.println("递归之前：" + path);
                dfs(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.removeLast();
                System.out.println("递归之后：" + path);
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Code01_全排列问题 solution = new Code01_全排列问题();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }
}
