package com.hyx.treenode.redblacktree;

import static com.hyx.treenode.redblacktree.RedBlackTree.Color.BLACK;
import static com.hyx.treenode.redblacktree.RedBlackTree.Color.RED;

/**
 * @Author：hyx
 * @Date：2024/9/27 14:30
 * 红黑树
 */
public class RedBlackTree {

    /*
    红黑树特性
        1. 所有节点都有两种颜色：红、黑
        2. 所有 null 视为黑色
        3. 红色节点不能相邻
        4. 根节点是黑色
        5. 从根到任意一个叶子节点，路径中的黑色节点数一样
     */

    enum Color {
        RED, BLACK;
    }

    private Node root;

    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;        // 父节点
        Color color = RED;  // 颜色

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        // 是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        // 叔叔
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right; // 求叔叔
            } else {
                return parent.parent.left;
            }
        }

        // 兄弟
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

    }

    // 判断红、黑
    boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    boolean isBlack(Node node) {
        return node == null || node.color == BLACK;
    }

    // 右旋 1.parent的处理 2.旋转后新跟的父子关系
    /*
                pink
            /
        yellow
            \
           green
     */
    private void rightRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.left;
        Node green = yellow.right;
        if (green != null) {
            green.parent = pink; // 处理 parent
        }
        yellow.right = pink;
        yellow.parent = parent;// 处理 parent
        pink.left = green;
        pink.parent = yellow;// 处理 parent
        // 判断原来的pink是parent的左孩子还是右孩子
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    // 左旋
    /*
              pink
                  \
                    yellow
                    /
                green
     */
    private void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;
        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    /**
     * 新增或更新
     * 正常增、遇到红红不平衡的进行调整
     *
     * @param key   键
     * @param value 值
     */
    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }
        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    // 当插入的节点是红色的情况下，需要进行调整，有四种情况
    // case1: 插入节点为根节点，将根节点变黑
    // case2: 插入节点的父亲若为黑色，树的红黑性质不变，无需调整；
    // 插入节点的父亲为红色，触发红红相邻
    // case3: 叔叔为红色
    /*
       case3的解决方法：
            - 父亲变为黑色，为了保证黑色平衡，连带的叔叔也变为黑色
            - 祖父如果是黑色不变，会造成这颗子树黑色过多，因此祖父节点变为红色
            - 祖父如果变成红色，可能会接着触发红红相邻，因此对将祖父进行递归调整
     */
    // case4: 叔叔为黑色
    /*
        case4:叔叔为黑色
            1. 父亲为左孩子，插入节点也是左孩子，此时即 LL 不平衡
               * 让父亲变黑，为了保证这颗子树黑色不变，将祖父变成红，但叔叔子树少了一个黑色
               * 祖父右旋，补齐一个黑色给叔叔，父亲旋转上去取代祖父，由于它是黑色，不会再次触发红红相邻
            2. 父亲为左孩子，插入节点是右孩子，此时即 LR 不平衡
               * 父亲左旋，变成 LL 情况，按 1. 来后续处理
            3. 父亲为右孩子，插入节点也是右孩子，此时即 RR 不平衡
               * 让父亲变黑，为了保证这颗子树黑色不变，将祖父变成红，但叔叔子树少了一个黑色
               * 祖父左旋，补齐一个黑色给叔叔，父亲旋转上去取代祖父，由于它是黑色，不会再次触发红红相邻
            4. 父亲为右孩子，插入节点是左孩子，此时即 RL 不平衡
               * 父亲右旋，变成 RR 情况，按 3. 来后续处理
     */
    void fixRedRed(Node x) {
        // case1: 插入节点为根节点，将根节点变黑
        if (x == root) {
            x.color = BLACK;
            return;
        }
        // case2: 插入节点的父亲若为黑色，树的红黑性质不变，无需调整
        if (isBlack(x.parent)) {
            return;
        }
        /*
            case 3 当红红相邻、叔叔为红时
            需要将父亲、叔叔变黑、祖父变红，然后对祖父进行递归处理
         */
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
            return;
        }
        // case 4 当红红相邻，叔叔为黑时
        // 变色 + 旋转
        if (parent.isLeftChild() && x.isLeftChild()) { // LL
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        } else if (parent.isLeftChild()) { // LR
            leftRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        } else if (!x.isLeftChild()) { // RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        } else { // RL
            rightRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    /**
     * 删除
     * 正常删、会用到李代桃僵技巧，遇到黑黑不平衡进行调整
     *
     * @param key 键
     */
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    // 复杂调整(删除的是黑色，剩下的是null也是黑节点)
    // 处理双黑（case3、case4、case5）
    /*
        case3：被调整节点的兄弟为红，此时两个侄子定为黑
        case4：被调整节点的兄弟为黑，两个侄子都为黑
        case5：被调整节点的兄弟为黑，至少一个红侄子
     */
    private void fixDoubleBlack(Node node) {
        if (node == root) {
            return;
        }
        Node parent = node.parent;
        Node sibling = node.sibling();
        // case 3 兄弟节点是红色，需要对父亲进行左旋或者是右旋，然后进行换色
        if (isRed(sibling)) {
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RED;
            sibling.color = BLACK;
            fixDoubleBlack(node);
            return;
        }
        // 兄弟是黑色
        // case 4 ：被调整节点的兄弟为黑，两个侄子都为黑
        /*
            - 将兄弟变红，目的是将删除节点和兄弟那边的黑色高度同时减少1
            - 如果父亲是红，则需将父亲变为黑，避免红红，此时路径黑节点数目不变
            - 如果父亲是黑，说明这条路径还是少黑，再次让父节点触发双黑
         */
        if (sibling != null) {
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            } else {
                // case5：被调整节点的兄弟为黑，至少一个红侄子
                /*
                    - 如果兄弟是左孩子，左侄子是红，LL不平衡
                    - 如果兄弟是左孩子，右侄子是红，LR不平衡
                    - 如果兄弟是右孩子，右侄子是红，RR不平衡
                    - 如果兄弟是右孩子，左侄子是红，RL不平衡
                 */
                // LL 右旋 + 变色
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                // LR 左旋 + 右旋 + 变色
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                // RR
                else if (!sibling.isLeftChild() && isRed(sibling.right)) {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                // RL
                else {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                parent.color = BLACK;

            }
        } else {
            fixDoubleBlack(parent);
        }
    }


    // 删除黑色节点需要考虑平衡，删除红色节点不需要考虑平衡
    private void doRemove(Node deleted) {
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        if (replaced == null) { // 没有孩子
            // case1 : 删除的是根节点
            if (deleted == root) {
                root = null;
            } else { // 不是根节点，且没有孩子，比如说（叶子节点）
                if (isBlack(deleted)) {
                    // 复杂调整(删除的是黑色，剩下的是null也是黑节点)
                    fixDoubleBlack(deleted);
                } else {
                    // 红色叶子，无需任何处理
                }
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null; // help gc
            }
            return;
        }
        // 有一个孩子的情况
        if (deleted.left == null || deleted.right == null) {
            // case1 : 删除的是根节点
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = null;
                root.right = null;
            } else { // 删除的不是根节点，且有一个孩子
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;//  help gc
                if (isBlack(deleted) && isBlack(replaced)) {
                    // 复杂处理(删除的是黑色，剩下的也是黑色)
                    fixDoubleBlack(replaced);
                } else {
                    // case 2: 删除的是黑色，剩下的是红色，剩下的这个红节点变黑
                    replaced.color = BLACK;
                }
            }
            return;
        }
        // case 0 有两个孩子 -> 有一个孩子 或 没有孩子，可以通过交换键值来进行删除操作
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);
    }


    // 查看删除节点
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    // 查找剩余节点
    private Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        // 两个孩子都有，则找他的后继节点
        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }
}