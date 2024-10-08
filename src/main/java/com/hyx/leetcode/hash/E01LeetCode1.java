package com.hyx.leetcode.hash;

/**
 * @version 0.1
 * @Author hyx
 * @className E01LeetCode1
 * @Date 2024/10/8  22:04
 * @description
 * @since jdk 11
 */

import java.util.HashMap;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 */
public class E01LeetCode1 {

    /*
        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

        输入：nums = [3,2,4], target = 6
        输出：[1,2]

        输入：nums = [3,3], target = 6
        输出：[0,1]

        思路：
            1、循环遍历数组，拿到每个数字 x
            2、以 target - x 作为 key 到 hash 表查找
                1）若没找到，将 x 作为 key，它的索引作为 value 放入 hash 表
                2）若没找到，返回 x 和它配对数的索引即可
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (map.containsKey(y)) {
                return new int[]{i, map.get(y)};
            } else {
                map.put(x, i);
            }
        }
        return null;
    }

}
