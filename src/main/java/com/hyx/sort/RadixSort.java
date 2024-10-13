package com.hyx.sort;

import java.util.ArrayList;

/**
 * @author hyx
 * @version 0.1
 * @className RadixSort
 * @date 2024/10/13 16:39
 * @description 基数排序 最低有效数字 LSD（least significant digit）
 * @since jdk 11
 */
public class RadixSort {

    // 按位来排序（个位、十位……）
    /*
    要点： 轮数越靠后，优先级越高。
          只能从个位开始排，不能从高位开始排，不然个位的优先级会高于高位的优先级
          基数排序是稳定排序，因此先排个位、再排十位，十位的排序不会打乱个位取值相等的元素顺序
          数字必须位数一样。
        [158,151,136,138,139,159]
        按个位来排
        0
        1   151
        2
        3
        4
        5
        6   136
        7
        8   158 138
        9   139 159
        151,136,158,138,139,159
        按十位来排
        0
        1
        2
        3   136 138 139
        4
        5   151 158 159
        6
        7
        8
        9
        136 138 139 151 158 159
        按百位来排（规律是一样的）
     */

    public static void radixSort(String[] a,int length){
        // 1.准备桶
        ArrayList<String>[] buckets = new ArrayList[10]; // 优化：可以将桶的容量订大，以应对更多字符的排序
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 2.进行多轮按位桶排序
        for (int i = length - 1; i >= 0 ; i--) {
            for (String s : a) {
                // 把 字符串 转为 int 类型 作为索引
                buckets[s.charAt(i) - '0'].add(s); // 注：s.charAt(2) 转为 int 要遵循 ASCII 码表
            }
            int k = 0;
            // 将个位排序好的放回原数组
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k++] = s;
                }
                System.out.println(bucket);
                // 清空 buckets 桶中的数据
                bucket.clear();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] phoneNumbers = new String[10];
        phoneNumbers[0] = "13812345678";
        phoneNumbers[1] = "13912345678";
        phoneNumbers[2] = "13612345678";
        phoneNumbers[3] = "13712345678";
        phoneNumbers[4] = "13512345678";
        phoneNumbers[5] = "13412345678";
        phoneNumbers[6] = "15012345678";
        phoneNumbers[7] = "15112345678";
        phoneNumbers[8] = "15212345678";
        phoneNumbers[9] = "15712345678";

/*        String[] phoneNumbers = new String[6];
        phoneNumbers[0] = "158";
        phoneNumbers[1] = "151";
        phoneNumbers[2] = "136";
        phoneNumbers[3] = "138";
        phoneNumbers[4] = "139";
        phoneNumbers[5] = "159";*/
        RadixSort.radixSort(phoneNumbers, phoneNumbers.length);
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }

}
