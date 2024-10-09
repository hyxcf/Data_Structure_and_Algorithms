package com.hyx.leetcode.hash;

import com.google.errorprone.annotations.Var;

import java.util.*;

/**
 * @version 0.1
 * @Author hyx
 * @className E03LeetCode49
 * @Date 2024/10/9  10:38
 * @description 字母异位词分组
 * @since jdk 11
 */
public class E03LeetCode49 {

    /*
    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
        输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */

    /*
        思路2
            题目中有说明：strs[i] 仅包含小写字母
            key = [2,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0] 26
            key = [2,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0] 26
                   a   c
     */
/*
    static class ArrayKey{
        int[] key = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Arrays.equals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }

        public ArrayKey(String str) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i); // 'a':97   'b':98   'c':99
                key[ch - 97] ++;
            }
        }
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<ArrayKey,List<String>> map = new HashMap<>();
        for (String str : strs) {
            ArrayKey key = new ArrayKey(str);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }
*/









/*
        思路1：
            1. 遍历字符串数组，每个字符串中的字符重新排序后作为key
            2. 所谓分组，其实就是准备一个集合，把这些单词加入到 key 相同的集合中
            3. 返回分组结果
*/



    public List<List<String>> groupAnagrams(String[] strs) {

         HashMap<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray(); // 将字符串转化为数组
            Arrays.sort(chars); // 进行排序
            String key = new String(chars);
            List<String> list = map.get(key);
            if (list == null){
                list = new ArrayList<>();
                map.put(key,list);
            }
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

}
