package com.hyx.hash;

//import com.google.common.hash.Hashing;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className HashTable
 * @Date 2024/10/8  9:49
 * @description
 * @since jdk 11
 *
 * <h2>哈希表</h2>
 * 给每份数据分配一个编号，放入表格（数组）
 * 建立编号与表格索引的关系，将来就可以通过编号快速查找数据
 * - 1. 理想情况编号当唯一，表格能容纳所有数据
 * - 2. 现实是不能说为了容纳所有数据造一个超大表格，编号也有可能重复
 * <br/>
 * 解决
 * - 1. 有限长度的数组，以 [拉链] 方式存储数据
 * - 2. 允许编号适当重复，通过数据自身来进行区分
 */
public class HashTable {

    // 摘要算法
    // 散列算法


    // 节点类
    static class Entry {
        int hash; // 哈希码
        Object key; // 键
        Object value; // 值
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];
    int size = 0; // 元素个数
    float loadFactor = 0.75f; // 12 阈值
    int threshold = (int) (loadFactor * table.length);


    /*
        求模运算替换为位运算
            - 前提：数组长度是 2 的 n 次方
            - hash % 数组长度 等价于 hash & (数组长度 - 1)
            8 & 7 = 0 （妙）
            0000 1000
            0000 0111
            100 & 7 = 4
            0110 0100
            0000 0111
          = 0000 0100
     */

    // 根据 hash 码🐎获取 value
    Object get(int hash, Object key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        // 遍历链表
        Entry p = table[idx];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    // 向 hash 表存入新 key value，如果 key 重复，则更新 value
    void put(int hash, Object key, Object value) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            // 如果这个位置没有节点，直接做新增操作
            table[idx] = new Entry(hash, key, value);
//            size++;
        } else {
            // 如果这个位置有节点，沿着链表进行查找，有重复 key 更新，否则新增
            Entry p = table[idx];
            while (true) {
                if (p.key.equals(key)) {
                    p.value = value;
                    return;
                }
                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            p.next = new Entry(hash, key, value); // 尾插法
//            size++;
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }

    // 扩容方法
    private void resize() {
        // 创建一个长度为旧数组长度 2 倍的新数组
        Entry[] newTable = new Entry[table.length << 1];
        // 遍历旧数组
        for (int i = 0; i < table.length; i++) {
            // 拿到每个链表头
            Entry p = table[i];
            if (p != null) {
                // 拆分链表，移动到新数组中
                /*
                    拆分规律
                    - 1.一个链表最多拆成两个
                    - 2.hash & table.length == 0 的一组
                    - 3.hash & table.length ！= 0 的一组
                 */
                Entry a = null;
                Entry b = null;
                // 定义两个变量来记录链表的头指针
                Entry aHead = null;
                Entry bHead = null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        if (a != null) {
                            a.next = p;
                        } else {
                            aHead = p;
                        }
                        // 分配到 a
                        a = p;
                    } else {
                        if (b != null) {
                            b.next = p;
                        } else {
                            bHead = p;
                        }
                        // 分配到 b
                        b = p;
                    }
                    p = p.next;
                }
                // 收尾工作
                // 规律：a 链表保持索引位置不变，b 链表索引位置 + table.length
                if (a != null) {
                    a.next = null;
                    newTable[i] = aHead;
                }
                if (b != null) {
                    b.next = null;
                    newTable[i + table.length] = bHead;
                }

            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    // 根据 hash 🐎 删除，返回删除的 value
    Object remove(int hash, Object key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry p = table[idx];
        Entry prev = null;
        while (p != null) {
            if (p.key.equals(key)) {
                // 找到了，进行删除
                if (prev == null) {
                    table[idx] = p.next;
                } else {
                    prev.next = p.next;
                }
                size--;
                return p.value;
            }
            prev = p;
            p = p.next;
        }
        return null;
    }

    public Object get(Object key) {
        int hash = getHash(key);
        return get(hash, key);
    }

    public void put(Object key, Object value) {
        int hash = getHash(key);
        put(hash, key, value);
    }

    public Object remove(Object key) {
        int hash = getHash(key);
        return remove(hash, key);
    }

    private static int getHash(Object key) {
        // 1.使用 Object.hashCode 计算哈希码
        return key.hashCode();
    }

    public void print(){
        int[] sums = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];
            while (p != null){
                sums[i] ++;
                p = p.next;
            }
        }
        System.out.println(Arrays.toString(sums));
    }


    public static void main(String[] args) {
        String s1 = "bac";
        String s2 = new String("abc");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        // 原则：值相同的字符串生成相同的 hash 码，尽量让值不同的字符串生成不同的 hash 码
        /*
            对于 abc a * 100 + b * 10 + c
            对于 bac b * 100 + a * 10 + c
         */
        int hash = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            System.out.println((int) c);
            // * 31 ，是因为他是一个质数，哈希冲突的概率较低
            // hash * 32 等价于 hash * 2^5 等价于 hash << 5
            hash = (hash << 5) - hash + c; // ((0+a) * 10 + b)*10+c = (a*10+b)*10+c = a * 100 + b * 10 + c
        }
        System.out.println(hash);

        // 测试 hash 算法的优劣
        HashTable table = new HashTable();
        for (int i = 0; i < 20; i++) {
            Object o = new Object();
            table.put(o,o);
        }
        table.print();
        
        // MurmurHash 哈希算法
//        int hash2 = Hashing.murmur3_32().hashString("abc", StandardCharsets.UTF_8).asInt();
//        System.out.println(hash2);
    }

}

/*
    解释 & （按位与）
    为什么计算索引位置用式子：
            hash & (数组长度 - 1)   等价于    hash % 数组长度
    为什么旧链表会拆分成两条，一条 hash & 旧数组长度==0，另一条 != 0
    为什么拆分后的两条链表，一个原索引不变，另一个是原索引 + 旧数组长度

    它们都有个共同的前提：数组长度是 2 的 n 次方

    10 进制中去除以 10，100，1000时，余数就是被除数的后 1，2，3 位
                  10^1,10^2,10^3
    2 进制中去除以 10，100，1000时，余数也是被除数的后 1，2，3 位
                  2^1 2^2 2^3
                  1    3   7

    p36
    JDK 的 HashMap 中采用了将对象 hashcode 高低位相互异或的方式减少冲突。
 */









