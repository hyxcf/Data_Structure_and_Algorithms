package com.hyx2.basic_data_structure.class02_greedy;

import java.util.HashSet;

/**
 * @author hyx
 * @version 0.1
 * @className GreedyTest02
 * @date 2024/12/27 21:40
 * @description
 * @since jdk 11
 */
/*
给定一个字符串str，只由'x'和'.'两种字符构成。
'X'表示墙，不能放灯，也不需要点亮
'.'表示居民点，可以放灯，需要点亮
如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class GreedyTest02 {


    /**
     * 暴力解法：方法1
     *
     * @param road road
     * @return 最佳方案
     */
    public static int minLight(String road) {
        if (road == null || road.isEmpty()) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index...]位置 自由选择放灯还是不放灯
    // str[0...index-1]位置，已经做完决定了，哪些放了灯的位置，存在lights里
    // 要求选出能照亮所有的方案，并且在这些有效的方案中，返回最少需要几个灯
    private static int process(char[] str, int index, HashSet<Integer> lights) {
        // 说明已经排完了，验证排完的次序是否符合要求
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'x') { // 此时它就是 '.'
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            // 无论是 x 还是 . 都不放灯
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                // 是 . 放灯
                lights.add(index);
                yes = process(str, index + 1, lights);
                // 恢复现场 ！！！！！！！ ，因为 lights 只有一个，每次都用它
                lights.remove(index);
            }
            // 得出最小方案
            return Math.min(no, yes);
        }
    }

    /*
        方法2：贪心算法
        1.  x   ->     x            看i+1
            i       i i+1
        2.  . -> 1)  x          i位置放 . 看i+2    1)步骤一定会在 i 位置放灯
            i       i+1
              -> 2)  .   ->①   x     看i+3       2)步骤一定会在 i+1 位置放灯
                     .   ->②   .     看i+3
                    i+1       i+2
     */
    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int index = 0;
        int lights = 0;
        while (index < str.length) {
            if (str[index] == 'x') {
                index++;
            } else { //str[index] = '.'
                lights++; // 2分支：不管你是 i+1 位置是x还是. ,都要放灯，所以直接 light++
                if (index + 1 == str.length) {
                    break;
                } else {
                    if (str[index + 1] == 'x') {
                        index = index + 2;
                    } else {
                        index = index + 3;
                    }
                }
            }
        }
        return lights;
    }

}


















