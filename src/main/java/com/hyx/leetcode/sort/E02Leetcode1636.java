package com.hyx.leetcode.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className E02Leetcode1636
 * @Date 2024/10/16  13:19
 * @description
 * @since jdk 11
 */
public class E02Leetcode1636 {

    /*
    给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。 请你返回排序后的数组。
        输入：nums = [2,3,1,3,2]
        输出：[1,3,3,2,2]
        解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序
     */

    public static int[] frequencySort(int[] nums) {
        // 创建一个count数组，记录数组中每个元素的出现次数
        int[] count = new int[201];
        // 遍历nums数组，对count数组进行赋值，并且找出最大频率
        int maxcount = 0;
        for (int i : nums) {
            count[i + 100]++;
            if (count[i + 100] > maxcount) {
                maxcount = count[i + 100];
            }
        }
        // 遍历count数组，生成排序好的数组
        int index = 0;
        // 优先按照频率升序
        for (int i = 1; i <= maxcount; i++) {
            // 从后往前遍历，就可以做到频率相同时，值降序排序
            for (int j = count.length - 1; j >= 0; j--) {
                if (count[j] == i) {
                    while (count[j]-- > 0) {
                        nums[index++] = j - 100;
                    }
                }
            }
        }
        return nums;
    }

    public static int[] frequencySort2(int[] nums) {
        int[] count = new int[201];
        // 1.统计出现频率
        for (int num : nums) {
            count[num + 100]++;
        }
        // 2.比较器 按频率升序、再按数值降序
        return Arrays.stream(nums)
                .boxed()
                .sorted((a, b) -> {
                    int af = count[a + 100];
                    int bf = count[b + 100];
                    if (af < bf) {
                        return -1;
                    } else if (af > bf) {
                        return 1;
                    } else {
                        return b - a;
                    }
                }).mapToInt(Integer::intValue)
                .toArray();
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};

        int[] result = frequencySort(arr1);
        System.out.println(Arrays.toString(result));
    }
}
