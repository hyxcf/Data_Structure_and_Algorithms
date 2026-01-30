package com.leetcode;

public class LeetCode_153_寻找旋转排序数组中的最小值_1_23 {

    /*
        已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
        若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
        若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
        注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
        
        给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
        你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

        示例 1：
            输入：nums = [3,4,5,1,2]
            输出：1
            解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
        示例 2：
            输入：nums = [4,5,6,7,0,1,2]
            输出：0
            解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
        示例 3：
            输入：nums = [11,13,15,17]
            输出：11
            解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
     */
    
    /*
        这题目的思路是找到旋转点，不是单调递增的说明一定有一个拐点，最小值（数学的魅力！）
        fixme: 1、找目标值（是否等于某数）→ 用 left <= right    2、找极值（最小/最大值）→ 用 left < right
     */

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int pre = 0;
        int last = nums.length - 1;
        while (pre < last) {
            int mid = pre + (last - pre) / 2;
            if (nums[mid] < nums[last]) {
                // 说明右半部分单调递增，最小值肯定在左半部分
                last = mid;
            } else {
                // 反之 说明左半部分单调递增，最小值肯定在右半部分
                pre = mid + 1;
            }
        }
        return nums[pre];
    }

    static class Preview_1_30 {
        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int pre = 0, last = nums.length - 1;
            while (pre < last) {
                int mid = pre + (last - pre) / 2;
                if (nums[mid] < nums[last]) {
                    last = mid; // 说明右半部分单调递增，最小值肯定在左半部分
                } else {
                    pre = mid + 1; // // 反之 说明左半部分单调递增，最小值肯定在右半部分
                }
            }
            return nums[pre];
        }
    }

}
