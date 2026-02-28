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

    static class Preview_1_30 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 1、确保 m < n
            int m = nums1.length;
            int n = nums2.length;
            if (m > n) {
                return findMedianSortedArrays(nums2, nums1);
            }
            // 2、确定区间
            int iMin = 0;
            int iMax = m;
            // 3、循环判断
            while (iMin <= iMax) {
                int i = (iMax + iMin) / 2; // fixme:这里是+
                // j 是希望左边的元素个数 >= 右边的元素个数
                int j = (m + n + 1) / 2 - i;
                if (i > 0 && j < n && nums1[i - 1] > nums2[j]) { // 左上>右下 缩小右区间
                    iMax = i - 1; // fixme：这里和下面的都是对 i 进行操作
                } else if (j > 0 && i < m && nums1[i] < nums2[j - 1]) {// 右上 < 左下 缩小左区间
                    iMin = i + 1;
                } else {
                    // i 刚刚好，求出左右两边的最值
                    int maxLeft = 0;
                    int minRight = 0;
                    // 求左边的最大值
                    if (i == 0) {
                        maxLeft = nums2[j - 1];
                    } else if (j == 0) {
                        maxLeft = nums1[i - 1];
                    } else {
                        maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                    }
                    // 如果是奇数，则直接返回左边的最大值
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }
                    // 偶数，(左边最大值+右边最小值) / 2
                    if (i == m) {
                        minRight = nums2[j];
                    } else if (j == n) {
                        minRight = nums1[i];
                    } else {
                        minRight = (Math.min(nums2[j], nums1[i]));
                    }
                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0d;
        }
    }

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
    private static class Preview_2_1 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 1、确保 m < n
            int m = nums1.length;
            int n = nums2.length;
            if (m > n) {
                return findMedianSortedArrays(nums2, nums1);
            }
            // 2、确定区间
            int iMin = 0;
            int iMax = m;
            // 3、循环判断
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = (m + n + 1) / 2 - i;
                if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                    iMax = i - 1;
                } else if (j > 0 && i < m && nums1[i] < nums2[j - 1]) {
                    iMin = i + 1;
                } else {
                    // i 刚刚好
                    int maxLeft = 0;
                    int minRight = 0;
                    // 求左边的最大值
                    if (i == 0) {
                        maxLeft = nums2[j - 1];
                    } else if (j == 0) {
                        maxLeft = nums1[i - 1];
                    } else {
                        maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                    }
                    // 如果是奇数，则直接返回左边的最大值
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }
                    // 求右边的最小值
                    if (i == m) {
                        minRight = nums2[j];
                    } else if (j == n) {
                        minRight = nums1[i];
                    } else {
                        minRight = Math.min(nums1[i], nums2[j]);
                    }
                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0d;
        }
    }

    private static class Preview_2_28 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 1、确保 m < n
            int m = nums1.length;
            int n = nums2.length;
            if (m > n) {
                return findMedianSortedArrays(nums2, nums1);
            }
            // 2.确定区间
            int iMin = 0;
            int iMax = m;
            while (iMin <= iMax) {
                /*
                    todo:
                     推导公式： 左半部分总长度 = i + j  右半部分总长度 = (m-i)+(n-j)
                     左半部分的长度要 >= 右半部分的长度
                     i+j = (m + n + 1) / 2 -> j = (m + n + 1) / 2 - i
                 */
                int i = (iMin + iMax) / 2;
                int j = (m + n + 1) / 2 - i;
                if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                    iMax = i - 1;
                } else if (j > 0 && i < m && nums1[i] < nums2[j - 1]) {
                    iMin = i + 1;
                } else {
                    // i 刚刚好
                    int maxLeft = 0;
                    int minRight = 0;
                    // 求左边的最大值
                    if (i == 0) {
                        maxLeft = nums2[j - 1];
                    } else if (j == 0) {
                        maxLeft = nums1[i - 1];
                    } else {
                        maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                    }
                    // 如果是奇数，则直接返回左边的最大值
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }
                    // 求右边的最小值
                    if (i == m) {
                        minRight = nums2[j];
                    } else if (j == n) {
                        minRight = nums1[i];
                    } else {
                        minRight = Math.min(nums1[i], nums2[j]);
                    }
                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0d;
        }
    }

}
