package com.leetcode;

public class Leetcode_208_实现Trie前缀树_12_25 {


    private static class Trie {

        private class Node {
            public int pass;
            public int end;
            public Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node node = root;
            node.pass++;
            int index = 0;
            for (char c : word.toCharArray()) {
                index = c - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;

        }

        public boolean search(String word) {
            if (word == null) {
                return false;
            }
            Node node = root;
            int index = 0;
            for (char c : word.toCharArray()) {
                index = c - 'a';
                if (node.nexts[index] == null) {
                    return false;
                }
                node = node.nexts[index];
            }
            return node.end != 0;
        }

        public boolean startsWith(String prefix) {
            if (prefix == null) {
                return false;
            }
            Node node = root;
            int index = 0;
            for (char c : prefix.toCharArray()) {
                index = c - 'a';
                if (node.nexts[index] == null) {
                    return false;
                }
                node = node.nexts[index];
            }
            return true;
        }
    }


    private static class Node {
        public int pass;   // 有多少个单词经过我？
        public int end;    // 有多少个单词在我这里结束？
        public Node[] nexts; // 我的26个孩子（a~z）

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    private Node root;

    public Leetcode_208_实现Trie前缀树_12_25() {
        root = new Node();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chs = word.toCharArray();
        Node node = root;
        node.pass++;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new Node();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        char[] chs = word.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return false;
            }
            node = node.nexts[index];
        }
        if (node.end == 0) {
            return false;
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }
        char[] chs = prefix.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return false;
            }
            node = node.nexts[index];
        }
        return true;
    }

    private static class Preview_2_3 {
        class Trie {

            private Node root;

            public Trie() {
                root = new Node();
            }

            public void insert(String word) {
                if (word == null || word.isEmpty()) {
                    return;
                }
                char[] chs = word.toCharArray();
                Node node = root;
                node.pass++;
                int index = 0;
                for (char ch : chs) {
                    index = ch - 'a';
                    if (node.nexts[index] == null) {
                        node.nexts[index] = new Node();
                    }
                    node = node.nexts[index];
                    node.pass++;
                }
                node.end++;
            }

            public boolean search(String word) {
                if (word == null || word.isEmpty()) {
                    return false;
                }
                char[] chs = word.toCharArray();
                Node node = root;
                int index = 0;
                for (char ch : chs) {
                    index = ch - 'a';
                    if (node.nexts[index] == null) {
                        return false;
                    }
                    node = node.nexts[index];
                }
                if (node.end == 0) {
                    return false;
                }
                return true;
            }

            public boolean startsWith(String prefix) {
                if (prefix == null || prefix.isEmpty()) {
                    return false;
                }
                char[] chs = prefix.toCharArray();
                Node node = root;
                int index = 0;
                for (char ch : chs) {
                    index = ch - 'a';
                    if (node.nexts[index] == null) {
                        return false;
                    }
                    node = node.nexts[index];
                }
                return true;
            }
        }
    }

    private static class Preview_2_27 {
        class Trie {

            private Node root;

            public Trie() {
                root = new Node();
            }

            public void insert(String word) {
                if (word == null || word.isEmpty()) {
                    return;
                }
                Node node = root;
                node.pass++;
                int index = 0;
                for (char c : word.toCharArray()) {
                    index = c - 'a';
                    if (node.nexts[index] == null) {
                        node.nexts[index] = new Node();
                    }
                    node = node.nexts[index];
                    node.pass++;
                }
                node.end++;
            }

            public boolean search(String word) {
                if (word == null || word.isEmpty()) {
                    return false;
                }
                char[] charArray = word.toCharArray();
                Node node = root;
                int index = 0;
                for (char c : charArray) {
                    index = c - 'a';
                    if (node.nexts[index] == null) {
                        return false;
                    }
                    node = node.nexts[index];
                }
                if (node.end == 0) {
                    return false;
                }
                return true;
            }

            public boolean startsWith(String prefix) {
                if (prefix == null || prefix.isEmpty()) {
                    return false;
                }
                char[] chs = prefix.toCharArray();
                Node node = root;
                int index = 0;
                for (char ch : chs) {
                    index = ch - 'a';
                    if (node.nexts[index] == null) {
                        return false;
                    }
                    node = node.nexts[index];
                }
                return true;
            }

        }
    }

    private static class Preview_3_10 {

        class Node {
            public int pass;
            public int end;
            public Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        class Trie {

            private Node root;

            public Trie() {
                root = new Node();
            }

            public void insert(String word) {
                if (word == null || word.isEmpty()) {
                    return;
                }
                char[] charArray = word.toCharArray();
                Node node = root;
                node.pass++;
                for (char c : charArray) {
                    int index = c - 'a';
                    if (node.nexts[index] == null) {
                        node.nexts[index] = new Node();
                    }
                    node = node.nexts[index];
                    node.pass++;
                }
                node.end++;
            }

            public boolean search(String word) {
                if (word == null || word.isEmpty()) {
                    return false;
                }
                char[] charArray = word.toCharArray();
                Node node = root;
                for (char c : charArray) {
                    int index = c - 'a';
                    if (node.nexts[index] == null) {
                        return false;
                    }
                    node = node.nexts[index];
                }
                if (node.end == 0) {
                    return false;
                }
                return true;
            }

            public boolean startsWith(String prefix) {
                if (prefix == null || prefix.isEmpty()) {
                    return false;
                }
                char[] charArray = prefix.toCharArray();
                Node node = root;
                for (char c : charArray) {
                    int index = c - 'a';
                    if (node.nexts[index] == null) {
                        return false;
                    }
                    node = node.nexts[index];
                }
                return true;
            }
        }
    }

}
