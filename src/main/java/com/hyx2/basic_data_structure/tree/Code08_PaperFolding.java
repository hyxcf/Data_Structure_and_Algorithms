package com.hyx2.basic_data_structure.tree;

/**
 * @version 0.1
 * @Author hyx
 * @className Code08_PaperFolding
 * @Date 2024/11/4  17:41
 * @description 写出折纸的折痕方向
 * @since jdk 11
 */
public class Code08_PaperFolding {

    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    // 递归过程，来到了某一个节点
    // i 是节点的层数，N一共的层数，down == true 凹  down == false 凸
    private static void printProcess(int i, int N, boolean down) {
        if (i > N) return;
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);

    }

    public static void main(String[] args) {
        int n = 3;
        printAllFolds(n);
    }

}
