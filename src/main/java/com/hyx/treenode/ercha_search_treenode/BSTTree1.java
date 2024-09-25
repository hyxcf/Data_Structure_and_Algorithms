package com.hyx.treenode.ercha_search_treenode;

import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author：hyx
 * @Date：2024/9/24 15:10
 * Binary Search Tree 二叉搜索树
 */

public class BSTTree1 {

    BSTNode root; // 根节点

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 查找最小关键字的对应值
     *
     * @return 关键字对应的值
     */
    public Object min() { // 一直向左走
        return min(root);
//        return doMin(root); 递归版实现
    }

    // 非递归实现 查找最小关键字的对应值
    public Object min(BSTNode node) { // 一直向左走
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    // 递归版实现 查找最小关键字的对应值
    public Object doMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {// 最小的节点
            return node.value;
        }
        return doMin(node.left);
    }

    /**
     * 查找最大关键字对应值
     *
     * @return 关键字对应的值
     */
    public Object max() {
        return max(root);
    }

    public Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    /**
     * 存储关键字和对应值
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(int key, Object value) {
        // 1.key 有 更新
        // 2.key 没有 新增
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                // 1. 找到了，进行更新
                node.value = value;
                return;
            }
        }
        // 2. key没有找到，新增
        // 新增的要点：是要找到新增节点的父节点，确实父节点的位置，然后判断是小于还是大于来判断左右节点
        BSTNode newNode = new BSTNode(key, value);
        if (parent == null) { // 说明当前没有节点，我们的节点就是新节点
            root = newNode;
            return;
        }
        if (key < parent.key) { // 说明是在左子树
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    /**
     * 查找关键字的前驱值
     * 如何去求前驱值？？？
     * - 节点有左子树，此时前驱节点就是左子树的最大值，
     * - 节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱。
     *
     * @param key 关键字
     * @return 前驱值
     */
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                // 记录向右的，反过来它的祖先就是自左而来的
                ancestorFromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }
        // 表示当前节点没有找到
        if (p == null) {
            return null;
        }
        // 找到的情况
        /*
            情况1：节点有左子树，此时前驱节点就是左子树的最大值，
            情况2：节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱。
         */
        //  情况1：节点有左子树，此时前驱节点就是左子树的最大值，
        if (p.left != null) {
            return max(p.left);
        }
        // 情况2：节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱。如果没有自左而来的，表示它没有祖先
        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    /**
     * 查找关键字的后继值
     * 如何去求后继值？？？
     * - 节点有右子树，此时前驱节点就是右子树的最小值，
     * - 节点没有右子树，若离它最近的祖先自从右而来，此祖先即为后继。
     *
     * @param key 关键字
     * @return 后继值
     */
    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                // 找到
                break;
            }
        }
        // 表示当前节点没有找到
        if (p == null) {
            return null;
        }
        // 1. 节点有右子树，此时前驱节点就是右子树的最小值，
        if (p.right != null) {
            return min(p.right);
        }
        // 2. 节点没有右子树，若离它最近的祖先自从右而来，此祖先即为后继。
        return ancestorFromRight != null ? ancestorFromRight.value : null;

    }

    /**
     * 根据关键字删除
     * 1.删除节点没有左孩子，将右孩子托孤给Parent
     * 2.删除节点没有右孩子，将左孩子托孤给Parent
     * 3.删除节点左右孩子都没有，已经被涵盖在情况1、情况2当中，把null托孤给parent
     * 4.删除节点左右孩子都有，可以将它的后继节点（称为S）托孤给Parent，再称S的父亲为SP，又分两种情况：
     * - （1）SP就是被删除节点，此时D与S紧邻，只需将S托孤给Parent
     * - （2）SP不是被删除节点，此时D与S不相邻，此时需要将S的后代托孤给SP，再将S托孤给Parent
     *
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public Object delete(int key) {
        // 在树中查找要遍历的 key
        BSTNode p = root;
        // 使用parent来记录删除的父节点
        BSTNode parent = null;
        while (p != null) {
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else if (p.key < key) {
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {
            return null;
        }
        // 删除操作
        if (p.left == null) {
            // 情况1
            shift(parent, p, p.right);
        } else if (p.right == null) {
            // 情况2
            shift(parent, p.left, p);
        } else {
            // 情况4  (复杂)！！！！！！ 不懂了看书，书上画的有图
            // 4.1 被删除节点找后继
            BSTNode s = p.right; // 找p的后继节点
            BSTNode sParent = p;// 后继父亲
            if (s.left != null) {
                sParent = s; // 这样就可以找到一个节点的父亲
                s = s.left; // 后继节点为 s 后继节点的父亲为 sParent
            }
            if (sParent != p) { // 说明他们不相邻了
                // 4.2 如果后继节点和被删除节点如果不相邻，需要去处理后继节点之后的节点
                shift(sParent, s, s.right); // 这里为啥顶的是右孩子呢？ 因为找的后继节点已经是左子树中最小的，不存在还有左子树更小的情况；也就是说它只有右孩子，不可能有左孩子。所以顶上去的是右孩子
                s.right = p.right; // 将顶上去的右节点指向被删除节点指向的右节点
            }
            // 4.3 后继节点取代被删除节点
            shift(parent, p, s);
            s.left = p.left; // 后记节点和待删除节点相邻，向顶上去的节点的左指向 指向 被删除节点指向的左节点
        }
        return p.value;
    }

    /**
     * 删除 - 递归版
     *
     * @param node 起点
     * @param key  待删除的值
     * @return 删剩下的孩子
     */
    private BSTNode doDelete(BSTNode node, int key) {
        return null;
    }

    /**
     * 托孤方法
     * 2
     * \
     * 6
     * \
     * 7
     *
     * @param parent  被删除节点的父亲
     * @param deleted 被删除的节点
     * @param child   被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        // 如果删除的节点没有父节点
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) { // 判断删除的节点是父节点的左节点还是右节点
            parent.left = child;
        } else {
            parent.right = child;
        }
    }


/*    private Object doGet(BSTNode node, int key) {
        if (node == null){
            return null; // 没找到
        }

        if (key < node.key) {
            return doGet(node.left, key); // 向左找
        } else if (key > node.key) {
            return doGet(node.right, key); //向右找
        } else {
            return node.value; // 找到了
        }
    }*/
    // 以非递归方法实现get方法

    /**
     * 查找关键字对应的值
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get(int key) {
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    // 在中序遍历 左值右
    /*
            4
          /   \
         2     6
        / \   / \
       1   3 5   7
     */

    // 找 < key 的所有 value
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                // 压栈
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    // 找 > key 的所有 value
    public List<Object> greater(int key) {
        /*ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                }
                p = pop.right;
            }
        }
        return result;*/
        // 反向中序遍历
        ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break; // 反向中序遍历
                }
                p = pop.left;
            }
        }
        return result;
    }

    // 找 >= key1 且 <= key2 的所有 value
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                }
                p = pop.right;
            }
        }
        return result;
    }

}
