package com.hyx;


import java.util.Arrays;

/**
 * hyx
 *
 * @author 32596
 */
public class BinarySearch {
    /*
    二分查找基础版
        Params: a-待查找的升序数组
                target-待查找的目标值
        Returns:
                 找到则返回索引
                 找不到返回 -1
       i 和 j 是左闭右闭的边界
     */
    public static int binarySearchBasic(int[] a, int target) {
        int i = 0, j = a.length - 1;    // 设置指针和初值
        // L次 元素在最左边 L次，  元素在最右边 2*L 次
        while (i <= j) {                // 范围内有东西
            int m = (i + j) >>> 1;
            if (target < a[m]) {        // 目标在左边
                j = m - 1;
            } else if (a[m] < target) { // 目标在右边
                i = m + 1;
            } else {                    // 找到
                return m;
            }
        }
        return -1;
    }
        /*
        循环次数：floor(log_2(n)) + 1

        i <= j  L+1次
        最差的情况：(floor(log_2(n)) + 1) * 5 + 4
     */

/*
    问题1:为什么是i<=j意味着区问内有未比较的元素，而不是i<j ?
      i,j 它们指向的元素也会参与比较

    问题2：(i+j) / 2 有没有问题?
    同一个二进制数
    1011_1111_1111_1111_1111_1111_1111_1110
    不把最高位视为符号位，代表3221225470
    把最高位视为符号位，代表-1073741826（java中经常把最后一位当作符号位）

    问题3∶都写成小于号有啥好处?
*/


    /*
    二分查找改动版
    Params: a-待查找的升序数组
            target-待查找的目标值
    Returns:
             找到则返回索引
             找不到返回 -1
   i 和 j 是左闭右开的边界
   j 所指代的元素不会是 target
 */
    public static int binarySearchAlternative(int[] a, int target) {
        int i = 0, j = a.length;      //  第一处
        while (i < j) {                // 第二处
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;                // 第三处
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /*
        二分查找平衡版
        1. 左闭右开的区间，$i$ 指向的可能是目标，而 $j$ 指向的不是目标
        2. 不奢望循环内通过 $m$ 找出目标, 缩小区间直至剩 1 个, 剩下的这个可能就是要找的（通过 $i$）
           * $j - i > 1$ 的含义是，在范围内待比较的元素个数 > 1
        3. 改变 $i$ 边界时，它指向的可能是目标，因此不能 $m+1$
        4. 循环内的平均比较次数减少了
        5. 时间复杂度 $\Theta(log(n))$
    */

    public static int binarySearch3(int[] a, int target) {
        int i = 0, j = a.length;
        while (1 < j - i) {
            int m = i + j >>> 1;
            if (a[i] < target) {
                i = m;
            } else {
                j = m;
            }
        }
        if (a[i] == target) {
            return i;
        } else {
            return -1;
        }
    }

    /*
        二分查找 java源码：为什么要区别 return -(low + 1); 要加上 -1
        因为要处理索引为0的情况，当要插入的数据索引为 0 时，此时 正0和负0 区分不出来
        为了区分这一情况，就加上 -1。
    */
    // Like public version, but without range checks.
    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if
            (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }

    public void test08() {
        int[] a = {2, 6, 9};
        int target = 4;
        int i = Arrays.binarySearch(a, target);
        // -2 = - 插入点 -1
        // -2+1 = -插入点
        if (i < 0) {
            int insertIndex = Math.abs(i + 1);// 插入点索引
            int[] b = new int[a.length + 1];
            System.arraycopy(a, 0, b, 0, insertIndex);
            b[insertIndex] = target;
            System.arraycopy(a, insertIndex, b, insertIndex + 1, a.length - insertIndex);
            System.out.println(Arrays.toString(b));
        }
    }

    /*
        二分查找 Leftmost
        Params: a-待查找的升序数组
                target-待查找的目标值
        Returns:
                 找到则返回索引
                 找不到返回 -1
     */
    public static int binarySearchLeftmost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candidate = m; // 记录候选位置
                j = m - 1;     // 继续向左
            }
        }
        return candidate;
    }

    /*
        二分查找 Rightmost
        Params: a-待查找的升序数组
                target-待查找的目标值
        Returns:
                 找到则返回索引
                 找不到返回 -1
     */
    public static int binarySearchRightmost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candidate = m; // 记录候选位置
                i = m + 1;       // 继续向右
            }
        }
        return candidate;
    }

    // 对于 Leftmost 与 Rightmost，可以返回一个比 -1 更有用的值

    /**
     * 二分查找 Leftmost
     *
     * @param a      待查找的升序数组
     * @param target 待查找的目标值
     * @return 返回 >= target 的最靠左索引
     */
    public static int binarySearchLeftmost(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    /*
        二分查找 Rightmost
        Params: a-待查找的升序数组
                target-待查找的目标值
        Returns:
                 返回 <= target 的最靠右索引
     */
    public static int binarySearchRightmost(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i - 1;
    }
}
