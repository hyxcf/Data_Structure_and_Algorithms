package com.hyx.leetcode.sort;

/**
 * @version 0.1
 * @Author hyx
 * @className E03Leetcode164
 * @Date 2024/10/16  13:42
 * @description
 * @since jdk 11
 */
public class E03Leetcode164 {


    /*
        给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
        您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
            输入: nums = [3,6,9,1]
            输出: 3
            解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
             */
    public static int maximumGap(int[] nums) {
        // 如果数组元素个数小于 2 ，则返回 0
        if (nums.length < 2) {
            return 0;
        }
        // 对数组进行排序,必须是线性时间
        // 求得最大值和最小值
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
            if (max < nums[i]) {
                max = nums[i];
            }
        }

        // 1、创建桶
        /*
            计算桶个数                   期望桶个数
            (max - min) / range + 1 = nums.length
            (max - min) / (nums.length - 1) = range
            出现空桶，为了让桶间元素 都大于 桶内元素
            (max - min) / range + 1 = nums.length + 1
            (max - min) / nums.length = range
         */
        // 桶的个数比元素多一个
        int range = Math.max((max - min) / nums.length, 1); // 预防最大值和最小值是一样的情况，range = 0，下面会报除0异常
        Pair[] buckets = new Pair[(max - min) / range + 1];
        // 2、放入数据
        for (int num : nums) {
            int index = (num - min) / range;
            if (buckets[index] == null) {
                buckets[index] = new Pair();
            }
            buckets[index].add(num);
        }
        for (Pair bucket : buckets) {
            System.out.println(bucket);
        }
        // 3、寻找最大差值
        int r = 0;
        int lastMax = buckets[0].max;
        for (int i = 1; i < buckets.length; i++) {
            Pair bucket = buckets[i];
            if (bucket != null) {
                r = Math.max(r, bucket.min - lastMax);
                lastMax = bucket.max;
            }
        }
        return r;
    }


    static class Pair {
        int max = 0;
        int min = Integer.MAX_VALUE;

        void add(int v) { // 桶内的最大最小值
            max = Math.max(v, max);
            min = Math.min(v, min);
        }

        @Override
        public String toString() {
            return "[" +
                    max +
                    "," + min +
                    ']';
        }
    }


    public static void main(String[] args) {
        int[] arr1 = {10, 20, 30, 40, 50, 60, 70, 90, 80, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 11, 12, 21, 22};
//        int[] arr1 = {1, 1000000};

        int result = maximumGap(arr1);
        System.out.println(result);
    }

}

