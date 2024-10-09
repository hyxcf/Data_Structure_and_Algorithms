package com.hyx.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @version 0.1
 * @Author hyx
 * @className E06LeetCode242
 * @Date 2024/10/9  21:38
 * @description 有效的字母异位词
 * @since jdk 11
 */
public class E06LeetCode242 {

    /*
    输入: s = "anagram", t = "nagaram"
    输出: true

    输入: s = "rat", t = "car"
    输出: false
     */

    /*
        方法2. 字符打散放入 int[26]，比较数组
     */
    public boolean isAnagram(String s, String t) {
        return Arrays.equals(getKey(s),getKey(t));
    }

    private int[] getKey(String s) { // 1ms
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            array[ch - 97] ++;
        }
        return array;
    }

    private int[] getKey1(String s) { // 3ms
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // 'a' - 97 = 0
            array[ch - 97]++;
        }
        return array;
    }



    /*
        方法1，拿到字符数组，排序后比较字符数字是否相同
     */
/*    public boolean isAnagram(String s, String t) { // 2ms
        return extracted(s).equals(extracted(t));
    }

    private String extracted(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }*/

}
