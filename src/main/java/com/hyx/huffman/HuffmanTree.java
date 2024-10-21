package com.hyx.huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @version 0.1
 * @Author hyx
 * @className HuffmanTree
 * @Date 2024/10/19  20:43
 * @description Huffman树以及编解码
 * @since jdk 11
 */
public class HuffmanTree {

    /*
        Huffman 树的构建过程(满二叉树)
            1、将统计了出现频率的字符，放入优先级队列
            2、每次出入两个频次最低的元素，给它俩找个爹
            3、把爹重新放入队列，重复 2 ~ 3
            4、当队列只剩一个元素时，Huffman 树构建完成
     */

    static class Node {
        Character ch; // 字符
        int freq;     // 频次
        Node left;
        Node right;
        String code;  // 编码

        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        int getFreq() {
            return freq;
        }

        boolean isLeaf() {
            return left == null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    ", left=" + left +
                    ", right=" + right +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

    String str;
    Map<Character, Node> map = new HashMap<>();
    Node root;

    public HuffmanTree(String str) {
        this.str = str;
        // 功能1：统计频率
        char[] chars = str.toCharArray();
        for (char c : chars) {
            /*if (!map.containsKey(c)){
                map.put(c,new Node(c));
            }
            Node node = map.get(c);
            node.freq++;*/
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq++;
        }
/*        for (Node node : map.values()) {
            System.out.println(node);
        }*/
        // 功能2：构造树
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFreq));
        queue.addAll(map.values());
        while (queue.size() >= 2) {
            Node x = queue.poll();
            Node y = queue.poll();
            int freq = x.freq + y.freq;
            queue.offer(new Node(freq, x, y));
        }
        root = queue.poll();

        // 功能3：计算每个字符的编码 功能4：字符串编码后占用 bits
        int sum = dfs(root, new StringBuilder());
        for (Node node : map.values()) {
            System.out.println(node + " " + node.code);
        }
        System.out.println("总共会占用 bits：" + sum);
    }

    private int dfs(Node node, StringBuilder code) {
        int sum = 0;
        if (node.isLeaf()) {
            // 找到编码
            node.code = code.toString();
            sum = node.freq * code.length();
        } else {
            sum += dfs(node.left, code.append("0"));
            sum += dfs(node.right, code.append("1"));
        }
        if (code.length() > 0) {
            code.deleteCharAt(code.length() - 1);
        }
        return sum;
    }

    // 编码
    public String encode() {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(map.get(c).code);
        }
        return sb.toString();
    }

    // 解码
    public String decode(String str) {
        /*
            从根节点，寻找数字对应的字符
                数字是 0 向左走
                数字是 1 向右走
                如果没走到头，每走一步数字的索引 i++
            走到头就可以找到解码字符，再将 node 重置为根节点

            a 00
            b 10
            c 1

            i
            0   0   0   1   0   1   1   1   1   1   1   1   1
         */
        char[] chars = str.toCharArray();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        Node node = root;
        while (i < chars.length) {
            // 如果是非叶子节点
            if (!node.isLeaf()) {
                // 数字是 0 向左走
                if (chars[i] == '0') {
                    node = node.left;
                } else if (chars[i] == '1') {
                    // 数字是 1 向右走
                    node = node.right;
                }
                i++;
            }
            if (node.isLeaf()) {
                // 叶子节点的情况
                sb.append(node.ch);
                node = root; // 重置 根节点
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree("abbccccccc");
        String encode = huffmanTree.encode();
        System.out.println(encode);
        System.out.println(huffmanTree.decode(encode));
    }
}
