package com.leetcode;

public class LeetCode_33_搜索旋转排序数组_1_23 {

    /*
        整数数组 nums 按升序排列，数组中的值 互不相同 。
        
        在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，
        使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
        例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
        
        给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
        你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
        示例 1：
        输入：nums = [4,5,6,7,0,1,2], target = 0
        输出：4
        示例 2：
        输入：nums = [4,5,6,7,0,1,2], target = 3
        输出：-1
        示例 3：
        输入：nums = [1], target = 0
        输出：-1
     */
    /*
         核心思想：
            “虽然数组被旋转了，但我每次都能找出一段‘干净的、有序的区间’，
             然后问：target 在不在这个干净区间里？在，我就进去找；不在，我就去‘脏的那边’——而脏的那边，
             下一轮又会变成新的‘整体问题’，我照样能从中找出一段干净的！”
             
        本质：旋转数组的二分查找，本质是“利用局部有序性来恢复二分的决策能力”。

         我的理解：
         1、第一个if块如果命中，说明是有序的，判断第二个if块，如果命中，说明在target在有序的这部分，如果没有命中，则pre=mid+1，到无序的部分。
         2、如果第一个if块没有命中，说明是无序的，判断第二个if块，如果target命中，说明在target在无序的这部分，如果没有命中，则last=mid-1，到有序的部分。
         总结：不管第一个if块命中的是哪个，里面的内容都在进行处理，不断缩小边界
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int pre = 0;
        int last = nums.length - 1;
        while (pre <= last) {
            int mid = pre + (last - pre) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            // 判断左半部分是否有序
            if (nums[pre] <= nums[mid]) { // fixme:这里是 <= 
                if (nums[pre] <= target && target < nums[mid]) {
                    last = mid - 1;
                } else {
                    pre = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[last]) {
                    pre = mid + 1;
                } else {
                    last = mid - 1;
                }
            }
        }
        return -1;
    }
    /*
        关键点：nums[pre] < nums[mid] 是 严格小于
            但在 [3,1] 中：
            nums[pre] = 3, nums[mid] = 3
            3 < 3 → false → 进入 else 分支
            但此时：
            左半部分 [3] 是有序的（单元素）
            右半部分 [1] 也是有序的
            但你因为 nums[pre] < nums[mid] 不成立，就认为“左半无序”，于是判断右半是否有序
            然而：
            nums[mid] = 3, target = 1
            3 < 1？❌ 不成立 → 认为不在右半 → 去左半 → 但 left=0, right=-1 → 溢出
            👉 根本原因：你在判断“左半是否有序”时用了 nums[pre] < nums[mid]，但当 nums[pre] == nums[mid] 时，左半仍然可能是有序的！
     */
}
