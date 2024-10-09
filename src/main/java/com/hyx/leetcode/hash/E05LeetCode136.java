package com.hyx.leetcode.hash;

import java.util.HashSet;

/**
 * @version 0.1
 * @Author hyx
 * @className E05LeetCode136
 * @Date 2024/10/9  21:11
 * @description 只出现一次的数字
 * @since jdk 11
 */
public class E05LeetCode136 {

    /*
        输入：nums = [2,2,1]
        输出：1

        输入：nums = [4,1,2,1,2]
        输出：4
     */


    /*
        方法2：
        1.任何相同的数字异或，结果都是0
        2.任何数字与 0 异或，结果是数字本身
     */
    public int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }

    /*
        方法1：
        思路：
            1.准备一个 Set 集合，注意放入数组元素
            2.遇到重复的，则删除
            3.最后留下来的，就是那个没有重复的数组
     */
/*    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                set.remove(num);
            }
        }
        return set.toArray(new Integer[0])[0];
    }*/

}
