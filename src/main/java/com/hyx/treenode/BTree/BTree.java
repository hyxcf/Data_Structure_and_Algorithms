package com.hyx.treenode.BTree;

import java.util.Arrays;
import java.util.jar.JarEntry;

/**
 * @author hyx
 * @Date 2024/10/3 16:21
 * @description B-树
 * <p>
 * B-树的特性
 * 1. 每个节点最多有m个孩子，其中m称为B-树的阶
 * 2. 除根节点和叶子节点，其他每个节点至少有ceil(m/2)个孩子
 * 3. 若根节点不是叶子节点，则至少有两个孩子
 * 4. 所有叶子节点都在同一层
 * 5. 每个非叶子节点由 n 个关键字和 n+1 个指针组成，其中 ceil(m/2)-1 <= n <= m-1
 * 6. 关键字按非降序怕排列，即节点中的第 i 个关键字大于等于第 i - 1 关键字
 * 7. 指针P[i]指向关键字位于第 i 个关键字和第 i+1 个关键字的之间的子树
 * 孩子的最大数目 = 2t ，key的最大数目 = 2t - 1
 */
public class BTree {

    static class Node {
        int[] keys; // 关键字
        Node[] children; // 孩子
        int keyNumber; // 有效关键字数目
        boolean leaf = true; // 是否是叶子节点
        int t; // 最小度数（最小孩子数）

        public Node(int t) { // t >= 2
            this.t = t;
            this.children = new Node[2 * t]; // 最多孩子数 = 最小度数 * 2
            this.keys = new int[2 * t - 1];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        // 多路查找
        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                // 找到了就直接返回
                if (keys[i] == key) {
                    return this;
                }
                // 判断 关键字 > 查找的key
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            // 代码执行到这里
            // keys[i] > key 或者 i == keyNumber
            if (leaf) { // 判断如果是叶子节点，就直接返回 null，下面已经没有节点了
                return null;
            }
            // 非叶子情况
            return children[i].get(key);

        }

        // 向指定索引处插入 key
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        // 向指定索引处插入 child
        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        // 移除指定 index 处的 key
        int removeKey(int index) {
            int t = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        // 移除最左边的 key
        int removeLeftmostKey() {
            return removeKey(0);
        }

        // 移除最右边的 key
        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        // 移除指定 index 处的 child
        Node removeChild(int index) {
            Node removed = children[index];
            System.arraycopy(children, index + 1, children, index, --keyNumber - index);
            return removed;
        }

        // 移除最左边的 child
        Node removeLeftmostChild() {
            return removeChild(0);
        }

        // 移除最右边的 child
        Node removeRightmostChild() {
            return removeChild(keyNumber);
        }

        // index 孩子处左边的兄弟
        Node childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        // index 孩子处右边的兄弟
        Node childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

        // 复制当前节点的所有 key 和 child 到 target
        void moveToTarget(Node target) {
            int start = target.keyNumber;
            if (!leaf) {
                for (int i = 0; i <= keyNumber; i++) {
                    target.children[start + i] = children[i]; // 向后追加
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber++] = keys[i]; // 向后追加
            }
        }


    }

    Node root;
    int t; // 树中节点最小度数
    final int MIN_KEY_NUMBER; // 最小 key 数目
    final int MAX_KEY_NUMBER; // 最大 key 数目

    public BTree() {
        this(2);
    }

    public BTree(int t) { // t >= 2
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    // 1.是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    // 2.新增
    /*
        - 首先查找本节点中的插入位置i，如果没有空位（key 被找到），应该走更新操作
        - 接下来分两种情况：
            1.如果节点是叶子节点，可以直接插入了
            2.如果节点是非叶子节点，需要再 children[i] 处继续递归插入
        - 无论那种情况，插入完成后都可能超过节点 keys 数目限制，此时应当执行节点分裂
     */
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    private void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            // 判断如果找到了重复的key
            if (node.keys[i] == key) {
                return;
            }
            // 待插入的 key < 遍历的节点 key
            if (key < node.keys[i]) {
                break; // 此时的 i 就是待插入的节点位置，
            }
            i++;
        }
        if (node.leaf) {
            node.insertKey(key, i); // 是叶子节点，直接插入
            // 上限
        } else {
            doPut(node.children[i], key, node, i); // 不是叶子节点，递归遍历孩子节点进行插入
            // 上限
        }
        // todo 考虑页分裂
        if (node.keyNumber == MAX_KEY_NUMBER) {
            spilt(node, parent, index);
        }
    }

    /**
     * 页分裂
     * - 1.创建 right 节点 （分裂后大于当前 left 节点的），把 t 以后的 key 和 child 都拷贝过去
     * - 2.t-1 处的 key 插入到 parent 的 index 处，index 指 left 作为孩子的索引
     * - 3.right 节点作为 parent 的孩子插入到 index + 1 处
     *
     * @param left   待分裂的节点
     * @param parent 父亲节点
     * @param index  待分裂的节点 是 第几个孩子
     */
    private void spilt(Node left, Node parent, int index) {
        // 如果 parent == null 表示要分裂的是根节点，此时需要创建新根，原来的根节点作为新根的 0 孩子
        if (parent == null) { // 表示要分裂的是根节点
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }

        // 1.创建 right 节点 （分裂后大于当前 left 节点的），把 t 以后的 key 和 child 都拷贝过去
        Node right = new Node(t);
        right.leaf = left.leaf; // 根 据叶子节点 left 是否是叶子节点，来判断 新创建的节点 right 是否是叶子节点
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        // 分裂节点是非叶子的情况
        if (!left.leaf) {
            System.arraycopy(left.children, t, right.children, 0, t - 1);
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        // 2.t-1 处的 key 插入到 parent 的 index 处
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        // 3.right 节点作为 parent 的孩子插入到 index + 1 处
        parent.insertChild(right, index + 1);
    }


    // 3.删除
    /*
        case 1:当前节点是叶子节点，没找到
        case 2:当前节点是叶子节点，找到了
        case 3:当前节点是非叶子节点，没找到
        case 4:当前节点是非叶子节点，找到了
        case 5:删除后 key 数目 < 下限（不平衡）
        case 6:根节点
     */
    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    /**
     * 递归删除
     *
     * @param parent 父节点
     * @param node   被调整节点
     * @param index  被调整节点对应的索引位置
     * @param key    要删除的 key
     */
    private void doRemove(Node parent, Node node, int index, int key) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        // 判断是否是叶子节点
        if (node.leaf) {
            if (!found(node, key, i)) { // case 1
                return;
            } else { // case 2
                node.removeKey(i);
            }
        } else {
            if (!found(node, key, i)) { // case 3
                doRemove(node, node.children[i], i, key);
            } else { // case 4
                // 先找到 node 的后继节点进行替换 node 节点，然后在后继节点所在的原来数组中删除后继节点
                // 1.找到后继 key
                Node s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int sKey = s.keys[0];// 后继节点
                // 2.替换待删除 key
                node.keys[i] = sKey;
                // 3.删除后继 key
                doRemove(node, node.children[i + 1], i + 1, sKey);
            }
        }
        if (node.keyNumber < MIN_KEY_NUMBER) {
            // 调整平衡 case 5 case 6
            balance(parent, node, index);
        }
    }

    /**
     * 页合并
     * @param parent 父节点
     * @param x      被调整节点
     * @param i      被调整节点对应的索引
     */
    private void balance(Node parent, Node x, int i) {
        // case 6 根节点
        if (x == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        Node left = parent.childLeftSibling(i);
        Node right = parent.childRightSibling(i);
        if (left != null && left.keyNumber > MIN_KEY_NUMBER) {
            // case 5-1 左边富裕，右旋
            // 1）将父节点中前驱 key 旋转下来
            x.insertKey(parent.keys[i - 1], 0);
            // 判断左边兄弟节点是否是叶子节点
            // 不是叶子节点
            if (!left.leaf) {
                // 2）left 中最大的孩子换爹
                x.insertChild(left.removeRightmostChild(), 0);
            }
            // 3）left 中最大的 key 旋转上去
            parent.keys[i - 1] = left.removeLeftmostKey(); // 直接覆盖掉
            return;
        }
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) {
            // case 5-2 右边富裕，左旋
            // 1) 将父节点中后驱 key 旋转下来
            x.insertKey(parent.keys[i], x.keyNumber);
            if (!right.leaf) {
                // 2) right 中最小的孩子换爹
                x.insertChild(right.removeLeftmostChild(), x.keyNumber + 1); // 孩子的最右侧索引 = keys 的索引 + 1
            }
            // 3) right 中最小的 key 旋转上去
            parent.keys[i] = right.removeLeftmostKey();
            return;
        }
        // case 5-3 两边都不够借，向左合并
        if (left != null) {
            // 向左兄弟合并
            // 1.把被调整节点从父亲节点移除
            parent.removeChild(i);
            // 2.父节点把一个 key 合并到左兄弟
            left.insertKey(parent.removeKey(i - 1), left.keyNumber);
            // 3.把调整节点剩下的 key 和 children 都移动到左兄弟这边
            x.moveToTarget(left);
        } else {
            // 没有左兄弟
            // 1.移除右兄弟
            parent.removeChild(i + 1);
            // 2.父节点把一个 key 合并到 x
            x.insertKey(parent.removeKey(i), x.keyNumber);
            // 3.把右兄弟剩下的 key 和 children 都移动到 x 这边
            right.moveToTarget(x);
        }
    }


    // i 找到了，代表待删除 key 的索引
    // i 没找到，代表到第 i 个孩子继续查找
    private static boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

}
/*
b树删除逻辑

此时删除的都是叶子节点 且 找到的情况
            4 | 8
          /   |   \
         2    6   10
        / \  / \  / \
       1  3 5  7 9  11


1、从大往小进行删除，先删除11
            4 | 8
          /   |   \
   ->    2    6   10
        / \  / \  /           |
       1  3 5  7 9            11

            4 | 8
          /   |   \
   ->    2    6                 再次触发不平衡且左右两边没有富裕的元素，进行合并操作(case 5-3)
        / \  / \  /
       1  3 5  7 9 10

            4 | 8
          /   |
   ->    2    6
        / \  / \            |
       1  3 5  7           9 10

            4
          /   \
   ->    2    6|8
        / \  / | \
       1  3 5  7 9|10
此时 11 元素删除完成，删除10
            4
          /   \
   ->    2    6|8
        / \  / | \
       1  3 5  7  9
此时删除 10 不会影响平衡，可以直接删除，删除9
            4
          /   \
   ->    2    6|8
        / \  / | \                                     |
       1  3 5  7    此处节点不平衡，触发合并操作           9

            4
          /   \
   ->    2     6
        / \   / \
       1  3  5  7|8
此时恢复平衡，9 删除完成，删除8和删除10操作一样
            4
          /   \
   ->    2     6
        / \   / \
       1  3  5   7
此时删除 10 不会影响平衡，可以直接删除，删除 7 和删除 10 的情况一样
            4
          /   \
   ->    2     6
        / \   / \             |
       1  3   5               7  触发不平衡且左右两边没有富裕的元素，进行合并操作(case 5-3)


            4
          /   \
   ->    2                  再次触发不平衡且左右两边没有富裕的元素，进行合并操作(case 5-3)
        / \    |
       1  3   5|6

          null              触发根节点不平衡，case 6
           |
   ->     2 4
        /  |  \
       1   3  5|6

          2 4
   ->   /  |  \
       1   3  5|6
删除7完成，其他操作和上面基本重复，故省略



----------------------------------------------------------------------------------------------------------------------------------------

此时删除的都是非叶子节点 且 找到的情况
            4 | 8
          /   |   \
         2    6   10
        / \  / \  / \
       1  3 5  7 9  11
删除节点 4
删除非叶子节点中的key，首先先找到它的后继key，替换待删除key，删除后继key，最后看是否需要进行调整平衡
            5 | 8
          /   |   \
   ->    2    6   10
        / \  / \  / \
       1  3    7 9  11   此时不平衡了，需要进行平衡操作，右边也不富裕，进行合并操作(case 5-3)

            5 | 8
          /   |   \
   ->    2         10    再次不平衡，需要进行平衡操作，右边也不富裕，进行合并操作(case 5-3)
        / \   \   / \
       1  3  6|7  9  11

             8
          /     \
   ->   2|5     10
        / \    / \                |
       1   3  9  11              6 7

              8
           /     \
   ->    2|5      10
        / | \    / \
       1  3 6|7 9  11
删除 节点 4 完成，删除节点 8
              9
           /     \
   ->    2|5      10
        / | \    / \
       1  3 6|7    11   此时不平衡了，需要进行平衡操作，右边也不富裕，进行合并操作(case 5-3)

              9
           /     \
   ->    2|5                此时再次触发不平衡，但左边富裕，可以进行右旋(case 5-1)
        / | \     \         将父节点中前驱 key 旋转下来；判断左边兄弟节点是否是叶子节点；不是叶子节点则将left中最大的孩子换爹；left中最大的key旋转上去
       1  3 6|7  10|11

              9
           /     \
   ->    2|5      9       将父节点中前驱 key 旋转下来；
        / | \     \
       1  3 6|7  10|11

              9
           /     \
   ->    2|5      9      不是叶子节点则将left中最大的孩子换爹
        /  \    /   \
       1   3  6|7  10|11

              5
           /     \
   ->     2       9      left中最大的key旋转上去，真牛逼！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        / \      /  \
       1   3   6|7 10|11
此时key 9 删除完成，删除 key 5
              6
           /     \
   ->     2       9
        / \      /  \
       1   3   6|7 10|11

              6
           /     \
   ->     2       9
        / \      /  \
       1   3   7  10|11
此时key 5 删除完成，删除 key 6
              7
           /     \
   ->     2       9
        / \      /  \
       1   3   7  10|11

              7
           /     \
   ->     2       9
        / \      /  \
       1   3      10|11   此时触发不平衡，右边有富裕的元素进行左旋，(case 5-2)：将父节点中后驱key旋转下来；判断左边兄弟节点是否是叶子节点，不是叶子节点right中最小的孩子换爹；right中最小的key旋转上去

              7
           /     \
   ->     2       9         是叶子节点，直接换爹
        / \      /  \
       1   3    9  10|11

              7
           /     \
   ->     2      10         换爹成功
        / \      /  \
       1   3    9   11
此时 key 6 删除完成，删除 key 7（简化步骤了）
              9
           /     \
   ->     2      10
        / \      /  \
       1   3        11

              9
           /     \
   ->     2      10
        / \      /  \       case 5-3
       1   3        11

              9
           /     \
   ->     2
        / \        \
       1   3     10|11

            9
           /
   ->     2
        / \                                                               \
       1   3                                                            10|11

          null    case 6 根节点
           |
   ->     2  9
        /  |   \
       1   3  10|11


   ->     2  9
        /  |   \
       1   3  10|11


后面依次类推！！终于拿下！

 */

