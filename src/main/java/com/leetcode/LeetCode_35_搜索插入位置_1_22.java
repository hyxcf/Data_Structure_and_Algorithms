package com.leetcode;

public class LeetCode_35_搜索插入位置_1_22 {

    /*
        给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
        请必须使用时间复杂度为 O(log n) 的算法。
        示例 1:
            输入: nums = [1,3,5,6], target = 5
            输出: 2
        示例 2:
            输入: nums = [1,3,5,6], target = 2
            输出: 1
        示例 3:
            输入: nums = [1,3,5,6], target = 7
            输出: 4   
     */
    public int searchInsert(int[] nums, int target) {
        int pre = 0;
        int last = nums.length - 1;
        while (pre <= last) {
            int mid = (pre + last) >>> 1;
            if (nums[mid] < target) {
                pre = mid + 1;
            } else if (nums[mid] > target) {
                last = mid - 1;
            } else {
                return mid;
            }
        }
        return pre;
    }

    static class Preview_1_30 {
        /*
            分析过程：[1,3,5,6] target:2
            mid : 1  nums[mid]:3    nums[mid] > target pre=0 last=mid-1=0
            mid : 0  nums[mid]:1    nums[mid] < target pre=1 last=0
            return 1;
            分析过程：[1,3,5,6] target:7
            mid : 1  nums[mid]:3    nums[mid] < target pre=2 last=3
            mid : 2  nums[mid]:5    nums[mid] < target pre=3 last=3
            mid : 3  nums[mid]:6    nums[mid] < target pre=4 last=3
            return 4;
         */
        public int searchInsert(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int pre = 0;
            int last = nums.length - 1;
            while (pre <= last) {
                int mid = (pre + last) >>> 1;
                if (nums[mid] > target) {
                    last = mid - 1;
                } else if (nums[mid] < target) {
                    pre = mid + 1;
                } else {
                    return mid;
                }
            }
            return pre;
        }
    }

    private static class Preview_2_13 {
        public int searchInsert(int[] nums, int target) {
            int pre = 0, last = nums.length; // 注意：last = n
            while (pre < last) {
                int mid = (pre + last) >>> 1;
                if (nums[mid] < target) {
                    pre = mid + 1;
                } else {
                    last = mid; // nums[mid] >= target
                }
            }
            return pre; // 第一个 >= target 的下标
        }
    }

    private static class Preview_2_28 {
        /*
            fixme:
                循环结束时，pre == last。
                这个重合点 pre 恰好就是：数组中第一个大于等于 target 的元素的下标。
                如果 target 存在于数组中，pre 就是它的下标。
                如果 target 不存在，pre 就是它应该插入的位置（所有比它小的数都在左边，所有比它大的数都在右边）。
         */
        public int searchInsert(int[] nums, int target) {
            int pre = 0, last = nums.length;
            while (pre < last) {
                int mid = (pre + last) >>> 1;
                if (nums[mid] < target) {
                    pre = mid + 1;
                } else {
                    last = mid;
                }
            }
            return pre;
        }
    }

    private static class Preview_3_3 {
        public int searchInsert(int[] nums, int target) {
            int pre = 0, last = nums.length;
            while (pre < last) {
                int mid = (pre + last) >>> 1;
                if (nums[mid] < target) {
                    pre = mid + 1;
                } else {
                    last = mid;
                }
            }
            return pre;
        }
    }

}
