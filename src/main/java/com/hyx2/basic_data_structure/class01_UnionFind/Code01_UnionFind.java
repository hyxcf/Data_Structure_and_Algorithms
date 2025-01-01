package com.hyx2.basic_data_structure.class01_UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author hyx
 * @version 0.1
 * @className Code01_UnionFind
 * @date 2024/12/28 15:19
 * @description 并查集
 * @since jdk 11
 */
public class Code01_UnionFind {
    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        // 节点的对应关系
        public HashMap<V, Node<V>> nodes;
        // 该节点和父节点的对应关系
        public HashMap<Node<V>, Node<V>> parents;
        // 只有一个点，它是代表点,才有记录
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> values) {
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 从点 cur 开始，一直往上找，找到不能再往上的代表点，返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // 扁平化，此时 cur 就是头节点
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur); // 将栈中所有的元素的父亲都指向 cur
            }
            return cur;
        }

        /**
         * 查看 a 和 b 是否属于同一个集合
         *
         * @param a a
         * @param b b
         * @return boolean
         */
        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }


        /**
         * 小的集合跟大的集合
         *
         * @param a a
         * @param b b
         */
        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                // 获取 a 头节点下面子节点的数量
                Integer aSetSize = sizeMap.get(aHead);
                Integer bSetSize = sizeMap.get(bHead);
                // 小挂大
                if (aSetSize >= bSetSize) {
                    parents.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                    sizeMap.remove(bHead);
                } else {
                    parents.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

    }
}
