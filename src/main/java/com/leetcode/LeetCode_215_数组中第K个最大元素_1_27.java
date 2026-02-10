package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class LeetCode_215_数组中第K个最大元素_1_27 {


    /*
        给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
        请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
        你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
        示例 1:
            输入: [3,2,1,5,6,4], k = 2
            输出: 5
        示例 2:
            输入: [3,2,3,1,2,4,5,5,6], k = 4
            输出: 4
     */
    static class Solution {
        /*
         * 解题思路：
         * 1、向小顶堆放入前k个元素
         * 2、剩余元素
         *      若 <= 堆顶元素，则略过
         *      若 > 堆顶元素，则替换堆顶元素
         */
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();// 默认最小堆
            for (int num : nums) {
                minHeap.offer(num);
                if (minHeap.size() > k) {
                    minHeap.poll();// 弹出最小的
                }
            }
            return minHeap.peek();
        }

    }


    static public class Solution2 {
        private int quickSelect(List<Integer> nums, int k) {
            // 随机选择基准数
            Random rand = new Random();
            int pivot = nums.get(rand.nextInt(nums.size()));
            // 将大于、小于、等于 pivot 的元素划分至 big, small, equal 中
            List<Integer> big = new ArrayList<>();
            List<Integer> equal = new ArrayList<>();
            List<Integer> small = new ArrayList<>();
            for (int num : nums) {
                if (num > pivot)
                    big.add(num);
                else if (num < pivot)
                    small.add(num);
                else
                    equal.add(num);
            }
            // 第 k 大元素在 big 中，递归划分
            if (k <= big.size())
                return quickSelect(big, k);
            // 第 k 大元素在 small 中，递归划分
            if (nums.size() - small.size() < k)
                return quickSelect(small, k - nums.size() + small.size());
            // 第 k 大元素在 equal 中，直接返回 pivot
            return pivot;
        }

        public int findKthLargest(int[] nums, int k) {
            List<Integer> numList = new ArrayList<>();
            for (int num : nums) {
                numList.add(num);
            }
            return quickSelect(numList, k);
        }
    }


        private static class Preview_2_10 {
            public int findKthLargest(int[] nums, int k) {
                List<Integer> list = new ArrayList<>();
                for (int num : nums) {
                    list.add(num);
                }
                return quickSelect2(list, k);
            }

            private int quickSelect2(List<Integer> nums, int k) {
                // 找一个随机基准点
                int pivot = nums.get(new Random().nextInt(nums.size()));
                List<Integer> big = new ArrayList<>();
                List<Integer> equal = new ArrayList<>();
                List<Integer> small = new ArrayList<>();
                for (Integer num : nums) {
                    if (num > pivot) {
                        big.add(num);
                    } else if (num < pivot) {
                        small.add(num);
                    } else {
                        equal.add(num);
                    }
                }
                if (big.size() >= k) {
                    return quickSelect2(big, k);
                }
                if (nums.size() - small.size() < k) {
                    return quickSelect2(small, k - nums.size() + small.size());
                }
                return pivot;
            }
        }

}
/*
    情况 2：if (nums.size() - small.size() < k)
    先简化这个条件：
    nums.size() - small.size() = big.size() + equal.size()
    所以条件等价于：如果 k > big.size() + equal.size()
    意思是：第 k 大 不在 big 也不在 equal 里，只能在 small 里。
    那在 small 中要找第几个大呢？
    原来整个数组有 n = nums.size() 个元素。
    small 里有 s = small.size() 个元素，它们是 最小的 s 个，也就是排名从 (n - s + 1) 到 n。
    所以，如果我们要找第 k 大，而它在 small 中，那么它在 small 内部的“第几大”是多少？
    答案是：k - (n - s)
    因为前 (n - s) 个最大值（即 big + equal）已经被排除了。
    而 n - s = big.size() + equal.size()，所以：
    在 small 中找第 k - (big.size() + equal.size()) 大
    但 big.size() + equal.size() = n - small.size()
    所以就是 k - (n - small.size()) = k - n + small.size()
 */
