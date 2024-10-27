package com.hyx2.basic_data_structure.tree.trietree;

/**
 * @version 0.1
 * @Author hyx
 * @className Code01_TrieTree
 * @Date 2024/10/27  17:00
 * @description 前缀树 - 使用数组存储节点下标
 * @since jdk 11
 */
public class Code01_TrieTree {

    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class Trie {
        private Node1 root;

        public Trie() {
            root = new Node1();
        }

        // 插入
        public void insert(String word) {
            if (word == null) return;
            char[] chs = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        // word 这个单词之前加入过几次
        public int search(String word) {
            if (word == null) return 0;
            char[] chs = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串中, 有几个是以 pre 这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) return 0;
            char[] chs = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }

        // 删除字符串 word
        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    // 判断如果一个节点的 --pass == 0，那么下面的节点也就没有遍历的必要了
                    // eg: "ab","abcdk"
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null; // help gc
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

    }


}
