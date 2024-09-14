package com.hyx.leetcode.stack;

/**
 * @author hyx
 */
public class E04Leetcode232 {

    /*
        队列头        队列尾
        顶   底   底   顶
        s1            s2

        队列尾部 添加元素
        队列头部 移除元素
     */

    ArrayStack<Integer> s1 = new ArrayStack<>(100);
    ArrayStack<Integer> s2 = new ArrayStack<>(100);

    public void push(int x) { // 向队列尾部添加
        s2.push(x);
    }

    public int pop() {  // 从队列头移除
        // 首先判断s1栈内是否为空
        // 为空的话，则将s2中的数据移动到s1
        if (s1.isEmpty()){
            while (!s2.isEmpty()){
                s1.push(s2.pop());
            }
        }
        // 不为空的话，则直接从顶部取出元素
        return s1.pop();
    }

    public int peek() { // 从队列头获取
        // 首先判断s1栈内是否为空
        // 为空的话，则将s2中的数据移动到s1
        if (s1.isEmpty()){
            while (!s2.isEmpty()){
                s1.push(s2.pop());
            }
        }
        // 不为空的话，则直接从顶部取出元素
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }


}
