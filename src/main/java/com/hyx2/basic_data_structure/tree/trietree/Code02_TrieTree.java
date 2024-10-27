package com.hyx2.basic_data_structure.tree.trietree;

import java.util.HashMap;

/**
 * @version 0.1
 * @Author hyx
 * @className Code02_TrieTree
 * @Date 2024/10/27  17:32
 * @description 前缀树 02 - 使用 hash 表存储节点下标
 * @since jdk 11
 */
public class Code02_TrieTree {

    public static class Node {
        public int pass;
        public int end;
        public HashMap<Integer, Node> nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie {

        private Node root;

        public Trie() {
            root = new Node();
        }

        // 插入
        public void insert(String word) {
            if (word == null) return;
            char[] chs = word.toCharArray();
            int index = 0;
            Node node = root;
            node.pass++;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        // word 这个单词之前加入过几次
        public int search(String word) {
            if (word == null) return 0;
            char[] chs = word.toCharArray();
            int index = 0;
            Node node = root;
            node.pass++;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个以 pre 前缀的字符串
        public int prefixNumber(String pre) {
            if (pre == null) return 0;
            char[] chs = pre.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }

        // 删除
        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node node = root;
                int index = 0;
                node.pass--;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i];
                    if (!node.nexts.containsKey(index)) {
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }
    }
}
