package com.hyx;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * hyx
 * 动态数组
 */
public class DynamicArray implements Iterable<Integer> {
    private int size = 0; // 逻辑大小
    private int capacity = 10;// 容量
    private int[] array = {}; // 懒加载

    /**
     * 向最后位置[size]添加元素
     *
     * @param element-待添加元素
     */
    public void addLast(int element) {
        add(size, element);
    }

    /**
     * 向[0 ..size]位置添加元素
     *
     * @param index-索引位置
     * @param element-待添加元素
     */
    public void add(int index, int element) {
        // 容量检查
        if (size == 0) {
            array = new int[capacity];
        }
        checkAndGrow();
        // 添加逻辑
        if (index >= 0 && index < size) {// 考虑 index 的范围
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size++;
    }

    // 容量扩容
    private void checkAndGrow() {
        if (size == capacity) {
            // 进行扩容,1.5,2
            capacity += capacity >> 1; // 扩容为1.5倍
            int[] newArr = new int[capacity];
            System.arraycopy(array, 0, newArr, 0, size);
            array = newArr;
        }
    }

    public int get(int index) { // [0...size)
        return array[index];
    }

    // 遍历方法1：Params: consumer―遍历要执行的操作,入参:每个元素
    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            // 提供 array[i]
            // 返回 void
            consumer.accept(array[i]);
        }
    }

    // 遍历方法2–迭代器遍历
    int i = 0;

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }

    // 遍历方法3–使用流遍历

    /**
     * IntStream、LongStream 和 DoubleStream 分别表示原始 int 流、 原始 long 流 和 原始 double 流。
     */
    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }

    // 数组中删除元素
    public int remove(int index) {
        int removed = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removed;
    }



}
