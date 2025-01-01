package com.hyx2.basic_data_structure.class01_UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author hyx
 * @version 0.1
 * @className Code02_MergeUsers
 * @date 2025/1/1 20:41
 * @description
 * @since jdk 11
 */
public class Code02_MergeUsers {


    // (1,10,12) (2,10,32) (2,23,21)
    // 如果两个User，a字段一样、或者b字段一样、或者c字段一样，就认为是一个人
    // 请合并users，返回合并之后的用户数量
    public static int mergeUsers(List<User> users){
        UnionSet<User> unionSet = new UnionSet<>();
        HashMap<String,User> mapA = new HashMap<>();
        HashMap<String,User> mapB = new HashMap<>();
        HashMap<String,User> mapC = new HashMap<>();

        for (User user : users) {
            // 如果 mapA 中包含 user.a 字段，则进行合并，否则添加 user.a 字段
            if (mapA.containsKey(user.a)){
                unionSet.union(user,mapA.get(user.a));
            }else {
                mapA.put(user.a, user);
            }
            if (mapB.containsKey(user.b)){
                unionSet.union(user,mapB.get(user.b));
            }else {
                mapB.put(user.b, user);
            }
            if (mapC.containsKey(user.c)){
                unionSet.union(user,mapC.get(user.c));
            }else {
                mapC.put(user.c, user);
            }
        }
        // 合并之后，头节点的数量就是用户数量
        return unionSet.getSizeMapNum();
    }




    public static class User{
        private String a;
        private String b;
        private String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }








    public static class Node<V> {
        V node;

        public Node(V node) {
            this.node = node;
        }
    }

    public static class UnionSet<V> {
        // 节点与节点之间的对应关系
        public HashMap<V, Node<V>> nodes;
        // 节点与父节点的对应关系
        public HashMap<Node<V>, Node<V>> parentNodes;
        // 父节点的数量
        public HashMap<Node<V>, Integer> sizeMap;

        /**
         * 找父节点
         *
         * @param cur cur
         * @return parent
         */
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            // 这个循环的意思是 查看该节点是否是头节点，如果不是往上找
            while (cur != parentNodes.get(cur)) {
                stack.push(cur);
                cur = parentNodes.get(cur);
            }
            // 此时 cur 就是头节点
            // 扁平化。将路径中的节点的头节点都指向cur，好处是只需要查询一次后可以简化后续的操作
            while (!stack.isEmpty()) {
                parentNodes.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead){
                // 获取 a 头节点下面子节点的数量
                Integer aSetSize = sizeMap.get(aHead);
                Integer bSetSize = sizeMap.get(bHead);
                // 小挂大
                if (aSetSize >= bSetSize) {
                    parentNodes.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                    sizeMap.remove(bHead);
                } else {
                    parentNodes.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

        // 获取 集合中头节点的数量
        public int getSizeMapNum(){
            return sizeMap.size();
        }

    }




}
