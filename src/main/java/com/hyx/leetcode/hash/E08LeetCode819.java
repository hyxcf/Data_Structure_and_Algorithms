package com.hyx.leetcode.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @version 0.1
 * @Author hyx
 * @className E08LeetCode819
 * @Date 2024/10/9  22:34
 * @description 最常见的单词
 * @since jdk 11
 */
public class E08LeetCode819 {

    /*
        给你一个字符串 paragraph 和一个表示禁用词的字符串数组 banned ，返回出现频率最高的非禁用词。
        题目数据 保证 至少存在一个非禁用词，且答案 唯一 。
        paragraph 中的单词 不区分大小写 ，答案应以 小写 形式返回。

        输入：paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
        输出："ball"
        解释：
        "hit" 出现了 3 次，但它是禁用词。
        "ball" 出现了两次（没有其他单词出现这么多次），因此它是段落中出现频率最高的非禁用词。
        请注意，段落中的单词不区分大小写，
        标点符号会被忽略（即使它们紧挨着单词，如 "ball,"），
        并且尽管 "hit" 出现的次数更多，但它不能作为答案，因为它是禁用词。
     */
    /*
        思路；
            1、将 paragraph 截取为一个个单词
            2、将单词加入 map 集合，单词本身作为 key，出现次数作为 value，避免禁用词加入
            3、在 map 集合中找到 value 最大的，返回它对应的 key 即可
     */

    public String mostCommonWord1(String paragraph, String[] banned) {
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        Set<String> set = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();
        for (String key : split) {
            if (!set.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
//            Integer value = map.get(key);
//            if (value == null) {
//                value = 0;
//            }
//            map.put(key, value + 1);
        }
        Optional<Map.Entry<String, Integer>> optional = map.entrySet().stream().max(Map.Entry.comparingByValue());
        System.out.println(optional);
        return optional.map(Map.Entry::getKey).orElse(null);
    }

    public String mostCommonWord2(String paragraph, String[] banned) {
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        Set<String> set = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();
        for (String key : split) {
            if (!set.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
//            Integer value = map.get(key);
//            if (value == null) {
//                value = 0;
//            }
//            map.put(key, value + 1);
        }
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            Integer value = e.getValue();
            if (value > max) {
                max = value;
                maxKey = e.getKey();
            }
        }
        return maxKey;
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();
        char[] chars = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);
            } else {
                String key = sb.toString();
                if (!set.contains(key)) {
                    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
                sb = new StringBuilder(); // 重新拼接
            }
        }
        // 防止 “Bob” 也就是一个字符串结束的情况，要做一个收尾操作
        if (sb.length() > 0) {
            String key = sb.toString();
            if (!set.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        System.out.println(map);
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            Integer value = e.getValue();
            if (value > max) {
                max = value;
                maxKey = e.getKey();
            }
        }

        return maxKey;
    }

    public static void main(String[] args) {
        E08LeetCode819 e08 = new E08LeetCode819();
        String key = e08.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
//        String key = e08.mostCommonWord("Bob. hIt, baLl", new String[]{"bob", "hit"});
        System.out.println(key);
    }

}
