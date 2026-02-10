package com.leetcode;

import java.util.*;

public class LeetCode_347_前K个高频元素_1_27 {

    /*
        给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
        示例 1：
            输入：nums = [1,1,1,2,2,3], k = 2
            输出：[1,2]
        示例 2：
            输入：nums = [1], k = 1
            输出：[1]
        示例 3：
            输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2
            输出：[1,2]
     */
    // 最小堆
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = minHeap.poll().getKey();
        }
        return res;
    }


    // 排序法
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // Step 2: 转为列表并按频率降序排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue()); // 高频在前

        // Step 3: 取前 k 个 key（每个 key 是唯一的，不会重复）
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }

    private static class Preview_2_10 {
        // 最小堆
        public int[] topKFrequent(int[] nums, int k) {
            if (nums.length == 0) {
                return nums;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (minHeap.size() < k) {
                    minHeap.offer(entry);
                } else {
                    if (entry.getValue() > minHeap.peek().getValue()) {
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }
            }
            int[] res = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                res[i] = minHeap.poll().getKey();
            }
            return res;
        }

        // 排序法
        public int[] topKFrequent2(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            // Step 2: 转为列表并按频率降序排序
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
            list.sort((a, b) -> b.getValue() - a.getValue()); // 高频在前

            // Step 3: 取前 k 个 key（每个 key 是唯一的，不会重复）
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = list.get(i).getKey();
            }
            return res;
        }
    }
}
