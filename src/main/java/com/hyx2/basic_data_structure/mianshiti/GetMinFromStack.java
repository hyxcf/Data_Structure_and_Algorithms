package com.hyx2.basic_data_structure.mianshiti;

import java.util.Stack;

/**
 * @version 0.1
 * @Author hyx
 * @className GetMinFromStack
 * @Date 2024/10/23  23:15
 * @description
 * @since jdk 11
 */
public class GetMinFromStack {
    /*
    实现一个特殊的栈，在基本功能的基础上，在实现返回栈中最小元素的功能
        要求:
        1. pop、push、getMin操作的时间复杂度都是O(1)
        2. 设计的栈类型可以使用现成的栈结构
        思路：新建一个`min`栈，在`Data`栈中放入数据时，同步将`min`栈中插入`Data`栈中的最小值。当每次 pop 的时候，同步进行出栈，此时`min`栈中的每次栈顶元素都是`Data`栈中元素的最小值。
     */

    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(Integer newNum) {
            if (this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            } else if (newNum < this.getMin()) {
                this.stackMin.push(newNum);
            }else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop(){
            if (this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        private Integer getMin() {
            if (this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }

    }

}
