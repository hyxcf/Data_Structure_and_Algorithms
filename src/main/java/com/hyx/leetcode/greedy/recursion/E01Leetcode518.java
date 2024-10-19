package com.hyx.leetcode.greedy.recursion;

/**
 * @version 0.1
 * @Author hyx
 * @className E01Leetcode518
 * @Date 2024/10/18  22:50
 * @description
 * @since jdk 11
 */
/*
    零钱兑换 II
    可以凑成总金额所需的所有组合可能数
 */
public class E01Leetcode518 {
    public int change(int[] coins, int amount) {
        return rec(0, coins, amount);
    }

    /**
     * 求凑成剩余金额的解的个数
     *
     * @param index     当前硬币索引
     * @param coins     硬币面值数组
     * @param remainder 剩余金额
     * @param stack
     * @param first
     * @return 解的个数
     */
    private int rec(int index, int[] coins, int remainder) {
        // 情况1：剩余金额 < 0 - 无解
        // 情况2：剩余金额 > 0 - 继续递归
        // 情况3：剩余金额 = 0 - 有解
        if (remainder < 0) {
            return 0;
        } else if (remainder == 0) {
            return 1;
        } else {
            int count = 0;
            for (int i = index; i < coins.length; i++) {
                count += rec(i, coins, remainder - coins[i]);
            }
            return count;
        }
    }

    public static void main(String[] args) {
        E01Leetcode518 leetcode518 = new E01Leetcode518();
        int count = leetcode518.change(new int[]{5, 2, 1}, 5);
        System.out.println(count);
    }

}

/*
leetcode518.change(new int[]{5, 2, 1}, 5);
由大到小的递归过程
    rec(5,5)
        rec(5,0)                    1
        rec(2,3)
            rec(2,1)
                rec(2,-1)           0
                rec(1,0)            1
            rec(1,2)
                rec(1,1)
                    rec(1,0)        1
        rec(1,4)
            rec(1,3)
                rec(1,2)
                    rec(1,1)
                        rec(1,0)    1


 */


/*
leetcode518.change(new int[]{1, 2, 5}, 5);
index 设计的很巧妙，每次只遍历 index 以及 之后的值
由小到大的递归过程
    rec(1, 5)  // 处理 1 号硬币，剩余金额 5
        rec(1, 4)
            rec(1, 3)
                rec(1, 2)
                    rec(1,1)
                        rec(1, 0)       1
                        rec(2, -1)      0
                        rec(5, -4)      0
                    rec(2, 0)           1
                    rec(5, -3)          0
                rec(2, 1)
                    rec(2, -1)          0
                    rec(5, -4)          0
                rec(5, -2)              0
            rec(2, 2)
                rec(2, 0)               1
                rec(5, -3)              0
            rec(5, -1)
        rec(2, 3)
            rec(2, 1)
                rec(2, -1)              0
                rec(5, -4)              0
            rec(5, -2)
        rec(5, 0)                       1

 */




































