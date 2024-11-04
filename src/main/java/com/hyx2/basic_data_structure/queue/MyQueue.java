package com.hyx2.basic_data_structure.queue;

/**
 * @version 0.1
 * @Author hyx
 * @className MyQueue
 * @Date 2024/10/23  22:51
 * @description 用数组实现队列
 * @since jdk 11
 */
public class MyQueue {

    public static class MyQueue2 {
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private final int limit;

        public MyQueue2(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int pop(){
            if (size == 0){
                throw new RuntimeException("队列空了，不能在拿了");
            }
            size--;
            int ans = arr[polli];
            polli = nextIndex(polli);
            return ans;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

    }

}
