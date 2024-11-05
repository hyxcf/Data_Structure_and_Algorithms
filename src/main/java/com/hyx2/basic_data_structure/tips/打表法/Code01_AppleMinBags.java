package com.hyx2.basic_data_structure.tips.打表法;

/**
 * @version 0.1
 * @Author hyx
 * @className Code01_AppleMinBags
 * @Date 2024/11/5  23:59
 * @description 打表法
 * @since jdk 11
 */
/*
小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
    1) 能装下6个苹果的袋子
    2) 能装下8个苹果的袋子
小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1。
 */
public class Code01_AppleMinBags {

    // 打表法的技巧：先用暴力破解解决，然后观察其规律
    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) {
            int resUse6 = rest % 6 == 0 ? (rest / 6) : -1;
            if (resUse6 != -1) {
                bag6 = resUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag8 + bag6;
    }

    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) { // 如果是奇数，返回 -1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int apple = 0; apple <= 100; apple++) {
            System.out.println(apple + " : " + minBags(apple));
        }
    }

}
