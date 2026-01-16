package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Leetcode_56_合并区间_1_13 {
    
    
    /*
        以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。       
        示例 1：
            输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
            输出：[[1,6],[8,10],[15,18]]
            解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
        示例 2： 
            输入：intervals = [[1,4],[4,5]]
            输出：[[1,5]]
            解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
        示例 3：       
            输入：intervals = [[4,7],[1,4]]
            输出：[[1,7]]
            解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }
        // 1.首先对数组进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 2.三种情况
        List<int[]> res = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                // 不在一个合并区间
                res.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                // 在一个合并区间
                end = Math.max(intervals[i][1], end);
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[res.size()][]);
    }
}
