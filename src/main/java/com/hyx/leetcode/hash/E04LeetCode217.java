package com.hyx.leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @version 0.1
 * @Author hyx
 * @className E04LeetCode217
 * @Date 2024/10/9  20:44
 * @description 存在重复元素
 * @since jdk 11
 */
public class E04LeetCode217 {
    /*
        输入：nums = [1,2,3,1]
        输出：true

        输入：nums = [1,2,3,4]
        输出：false
     */


    // 改进方法1：使用 hashMap 来解决
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Object> map = new HashMap<>(nums.length * 2);
        Object value = new Object();
        for (int key : nums) {
            if (map.put(key, value) != null){
                return true;
            }
        }
        return false;
    }




    /*
        方法2：使用 hashSet 来解决
     */
/*    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)){
                return true;
            }
        }
        return false;
    }*/

/*
    方法1：使用 hashMap 来解决
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                return true;
            }
                map.put(nums[i], nums[i]);
        }
        return false;
    }*/
}
