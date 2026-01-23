package com.leetcode;

public class LeetCode_34_在排序数组中查找第一个和最后一个元素_1_23 {

    /*
        给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
        如果数组中不存在目标值 target，返回 [-1, -1]。
        你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
        示例 1：
            输入：nums = [5,7,7,8,8,10], target = 8
            输出：[3,4]
        示例 2：
            输入：nums = [5,7,7,8,8,10], target = 6
            输出：[-1,-1]
        示例 3：
            输入：nums = [], target = 0
            输出：[-1,-1]      
     */

    // 这个解法思路可以的，用了两次二分，思路：第一次二分求出target最左边的边界；第二次二分求出target+1最左边的边界 - 1，就是target最右边的边界
    public int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = lowerBound(nums, target + 1) - 1;
        return new int[]{left, right};
    }
    
    /*
        | 情况 | `lowerBound` 返回值 | 原因 |
        |------|---------------------|------|
        | `target` 比所有元素小 | `0` | `nums[0]` 是第一个 ≥ target 的元素 |
        | `target` 比所有元素大 | `n` | 没有元素 ≥ target，返回插入位置 |
        | `target` 存在 | 第一个等于 target 的位置 | 正常查找 |
        | `target` 不存在（中间值） | 第一个大于 target 的位置 | 仍满足 ≥ target |
     */
    private int lowerBound(int[] nums, int target) {
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


    // 这种解法并不好，极端情况下，时间复杂度会变成O(n)
    public int[] searchRange2(int[] nums, int target) {
        int pre = 0;
        int last = nums.length - 1;
        int foundIndex = -1;

        while (pre <= last) {
            int mid = (pre + last) >>> 1;
            if (nums[mid] < target) {
                pre = mid + 1;
            } else if (nums[mid] > target) {
                last = mid - 1;
            } else {
                foundIndex = mid;
                break;
            }
        }
        // 第二步：向左找左边界
        int left = foundIndex;
        while (left > 0 && nums[left - 1] == target) {
            left--;
        }

        // 第三步：向右找右边界
        int right = foundIndex;
        while (right < nums.length - 1 && nums[right + 1] == target) {
            right++;
        }
        return new int[]{left, right};
    }
}
