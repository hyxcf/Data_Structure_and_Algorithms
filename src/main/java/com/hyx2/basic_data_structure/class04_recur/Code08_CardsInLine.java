package com.hyx2.basic_data_structure.class04_recur;

/**
 * @author hyx
 * @version 0.1
 * @className Code08_CardsInLine
 * @date 2025/2/5 22:25
 * @description
 * 10.2 范围上的尝试模型
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 * @since jdk 11
 */
public class Code08_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(
                f(arr, 0, arr.length - 1),
                s(arr, 0, arr.length - 1));
    }


    // 假设我先手，我一定是拿最好的
    private static int f(int[] arr, int l, int r) {
        if (l == r) { // 就剩一张牌，我先手直接拿走
            return arr[l];
        }
        return Math.max(arr[l] + s(arr, l + 1, r), arr[r] + s(arr, l, r - 1));
    }

    // 我后手拿，对手一定让我拿最坏的
    private static int s(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        return Math.min(
                f(arr, l + 1, r),   // 100 1 7 假设我后手，对手先手，
                f(arr, l, r - 1)    // 有两种情况：1、对手选100 留下 1 7   2、对手选7 留下 100 1
        );
    }

    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 5};
        System.out.println(f(arr, 0, arr.length - 1));
        System.out.println(s(arr, 0, arr.length - 1));
    }

}
