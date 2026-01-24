package com.leetcode;

public class LeetCode_4_寻找两个正序数组的中位数_1_23 {
    
    /*
        给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
        算法的时间复杂度应该为 O(log (m+n)) 。
        
        示例 1：
            输入：nums1 = [1,3], nums2 = [2]
            输出：2.00000
            解释：合并数组 = [1,2,3] ，中位数 2
        示例 2：
            输入：nums1 = [1,2], nums2 = [3,4]
            输出：2.50000
            解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     */

    /*

     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 第一步：m < n
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // 第二步：初始化i的区间
        int iMin = 0;
        int iMax = m;
        // 第三步：循环判断
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (i > 0 && j < n && nums1[i - 1] > nums2[j]) { // 缩小右区间
                iMax = i - 1;
            } else if (j > 0 && i < m && nums2[j - 1] > nums1[i]) { // 增大左区间
                iMin = i + 1;
            } else {
                // i 刚好不大也不小，则求出左右部分的最值
                int maxLeft = 0;
                int minRight = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                // 如果是奇数，则直接返回左边的最大值
                if ((m + n) % 2 == 1) return maxLeft;
                if (j == n) {
                    minRight = nums1[i];
                } else if (i == m) {
                    minRight = nums2[j];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (minRight + maxLeft) / 2.0;
            }
        }
        return 0d;
    }

}
