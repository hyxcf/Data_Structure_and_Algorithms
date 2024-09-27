package com.hyx.treenode.avltree;

import com.hyx.leetcode.treenode.ercha_search.TreeNode;

/**
 * @Author：hyx
 * @Date：2024/9/26 21:15
 * AVL树
 * - 二叉搜索树在插入和删除时，节点可能失衡
 * - 如果在插入和删除时通过旋转，始终让二叉搜索树保持平衡，称为自平衡的二叉搜索树
 * - AVL 是自平衡二叉搜索树的实现之一
 */
public class AVLTree {

    /**
     * 求节点的高度
     *
     * @param node
     * @return 节点的高度
     */
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 更新节点高度（新增、删除、旋转）
     *
     * @param node
     */
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right));
    }

    /**
     * 平衡因子（balance factor） = 左子树高度 - 右子树高度 <br/>
     * - bf = 0，1，-1时，表示左右平衡 <br/>
     * - bf > 1，表示左边太高 <br/>
     * - bf < -1，表示右边太高 <br/>
     *
     * @param node 节点
     * @return
     */
    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }

/*
旋转时，要考虑四种情况：
失衡的四种情况
    LL
        * 失衡节点（图中 8 红色）的 bf > 1，即左边更高
        * 失衡节点的左孩子（图中 6）的 bf >= 0 即左孩子这边也是左边更高或等高
    LR
        * 失衡节点（图中 8）的 bf > 1，即左边更高
        * 失衡节点的左孩子（图中 6 红色）的 bf < 0 即左孩子这边是右边更高
    对称的还有两种情况
    RL
        * 失衡节点（图中 3）的 bf <-1，即右边更高
        * 失衡节点的右孩子（图中 6 红色）的 bf > 0，即右孩子这边左边更高

    RR
        * 失衡节点（图中 3）的 bf <-1，即右边更高
        * 失衡节点的右孩子（图中 6 红色）的 bf <= 0，即右孩子这边右边更高或等高
 */


    /**
     * 右旋
     * red
     * /
     * yellow
     * \
     * green
     *
     * @param red 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        AVLNode green = yellow.right;
        yellow.right = red; // 上位
        red.left = green; // 换爹
        // 更新树的高度
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     * 左旋
     * red                                      -- yellow --
     * /      \                                /               \
     * 1        yellow            ->           red               2
     * /       \                       /   \               \
     * green      2                     1   green             3
     * \
     * 3
     *
     * @param red 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode leftRotate(AVLNode red) {
        // 先获得要升上来的节点
        AVLNode yellow = red.right;
        // 查看 red 要旋转的节点，将它的左孩子换爹
        AVLNode green = yellow.left;
        yellow.left = red; // 上位
        red.right = green; // 换爹
        // 更新树的高度
        updateHeight(red); // 先更新高度较低的red节点
        updateHeight(yellow); // 再更新高度较高的yellow节点
        return yellow;
    }

    // 先左旋左子树，在右旋根节点--针对 LR 失衡的这种情况
    private AVLNode leftRightRotate(AVLNode node) {
        // 先找到失衡节点的左子树，进行左旋
        node.left = leftRotate(node.left);
        // 根节点右旋
        return rightRotate(node);
    }

    // 先右旋右子树，在左旋根节点--针对 RL 失衡的这种情况
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRightRotate(node);
    }

    // 检查节点是否失衡，重新平衡代码
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) { // LL 删除时需要多考虑两个冗余的条件，当删除后，根节点的左子树更高，但左子树的平衡因子如果为0，就需要考虑 bf(node.left) == 0这种情况，再进行右旋一次
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) { // LR
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) { // RL
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) { // RR
            return leftRotate(node);
        } else {
            return node;
        }
    }

    AVLNode root;

    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    // 使用递归实现put
    private AVLNode doPut(AVLNode node, int key, Object value) {
        // 1.找到空位，创建新节点
        if (node == null) {
            return new AVLNode(key, value);
        }
        // 2.key已经存在，更新
        if (key == node.key) {
            node.value = value;
            return node;
        }
        // 3.继续查找
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        // 新增节点，节点的高度需要变化
        updateHeight(node);
        // 新增节点，还要考虑失衡的情况
        return balance(node);
    }

    // remove
    public void remove(AVLNode node, int key) {
        root = doRemove(node, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        // 1.node == null
        if (node == null) {
            return null;
        }
        // 2.没找到key
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (node.key < key) {
            node.right = doRemove(node.right, key);
        } else {
            // 3.找到key 1）没有孩子 2）只有一个孩子 3）有两个孩子
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                // 有两个孩子
                AVLNode s = node.right; // s 后继节点
                while (s.left != null){
                    s = s.left;
                }
                // 处理后继节点的后事
                s.right = doRemove(node.right,s.key); // fixme:这步不太理解
                s.left = node.left;
                node = s;
            }
        }
        // 4.更新高度
        updateHeight(node);
        // 5.balance
        return balance(node);
    }


}

