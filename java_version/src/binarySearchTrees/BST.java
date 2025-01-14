package binarySearchTrees;

import test.TestBST;

import java.util.ArrayList;
import java.util.List;

public class BST<Key extends Comparable<Key>, Value> {
    public Node getRoot() {
        return root;
    }

    /**
     * binary search tree
     */
    private Node root;
    private Node cachedNode;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private Node parent;
        private int N;

        public int getH() {
            return H;
        }

        public void setH(int h) {
            this.H = h;
        }

        private int H;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }

        public Node(Key key, Value val, int N, int H) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.H = H;
        }

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public Value getVal() {
            return val;
        }

        public void setVal(Value val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    ", N=" + N +
                    '}';
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * searching for key from Tree
     *
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public Value getByLoop(Key key) {
        return getByLoop(root, key);
    }

    private Value getByLoop(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    /**
     * searching Node by key, and update Node.val to val, if not found, append new Node with val
     *
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
            x.left.parent = x;
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
            x.right.parent = x;
        } else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(getHeightByH(x.left), getHeightByH(x.right)) + 1;
        return x;
    }

    /**
     * find min key node in binarySearchTrees.BST
     *
     * @return
     */
    public Node min() {
        return min(root);
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * find max key node in binarySearchTrees.BST
     *
     * @return
     */
    public Node max() {
        return max(root);
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * find the node with the max key less or equal to key
     *
     * @param key
     * @return
     */
    public Node floor(Key key) {
        return floor(root, key);
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        Node res;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor(x.left, key);
        else if (cmp > 0) res = floor(x.right, key);
        else return x;
        if (res == null) return x;
        return res;
    }

    /**
     * find the node with the min key greater or equal to key
     *
     * @param key
     * @return
     */
    public Node ceiling(Key key) {
        return ceiling(root, key);
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        Node res;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) res = ceiling(x.left, key);
        else if (cmp > 0) return ceiling(x.right, key);
        else return x;
        if (res == null) return x;
        return res;
    }

    /**
     * find out the first K node inorder
     * @param k
     * @return
     */
    public Node select(int k) {
        return select(root, k);
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t == k) return x;
        else if (t > k) return select(x.left, k);
        else return select(x.right, k - t - 1);
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return size(x.left);
        else if (cmp < 0) return rank(key, x.left);
        else return rank(key, x.right) + size(x.left) + 1;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        if (x.left != null) x.left.parent = x;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        if (x.right != null) x.right.parent = x;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteBySuccessor(Key key) {
        root = deleteBySuccessor(root, key);
    }

    /**
     * this is the deletion method with the successor Node replacement
     *
     * @param x
     * @param key
     * @return
     */
    private Node deleteBySuccessor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node successor = min(x.right);
            Node t = x;
            x = successor;
            x.right = deleteMin(t.right);
            x.parent = t.parent;
            x.left = t.left;
        } else if (cmp < 0) x.left = deleteBySuccessor(x.left, key);
        else x.right = deleteBySuccessor(x.right, key);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteByPredecessor(Key key) {
        root = deleteByPredecessor(root, key);
    }

    private Node deleteByPredecessor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = deleteByPredecessor(x.left, key);
        else if (cmp > 0) x.right = deleteByPredecessor(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = max(x.left);
            x.left = deleteMax(t);
            x.parent = t.parent;
            x.right = t.right;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * in-order traversal
     *
     * @return
     */
    public Iterable<Key> keys() {
        return keys(min().key, max().key);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> queue = new ArrayList<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, List<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) keys(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.add(x.key);
        if (cmpHi > 0) keys(x.right, queue, lo, hi);
    }

    public int getHeightByIter() {
        return getHeightByIter(root);
    }

    private int getHeightByIter(Node x) {
        if (x == null) return 0;
        int leftH = getHeightByIter(x.left);
        int rightH = getHeightByIter(x.right);
        int maxH = Math.max(leftH, rightH);
        return maxH + 1;
    }

    public int getHeightByH() {
        return getHeightByH(root);
    }

    private int getHeightByH(Node x) {
        if (x == null) return 0;
        return x.H;
    }

    public double avgCompares() {
        return totalCompares(root) / size(root);
    }

    private double totalCompares(Node x) {
        if (x == null) return 0;
        return totalCompares(x.left) + size(x.left) + totalCompares(x.right) + size(x.right) + 1;
    }

    /**
     * get by cache, exercise 3.2.28
     */
    public Value getWithCache(Key k) {
        if (cachedNode != null && k == cachedNode.key) {
            return cachedNode.val;
        } else {
            cachedNode = new Node(k, get(k), 1);
            return cachedNode.val;
        }
    }

    /**
     * exercise 3.2.29, isBinaryTree check
     * check whether a tree is really a BST or not
     * comparing the total Node count with N in root Node
     */
    public boolean isBinaryTree(Node x) {
        if (x == null) return false;
        int total_cnt = countSubTreeNodes(x);
        return total_cnt == size(x);
    }

    private int countSubTreeNodes(Node x) {
        if (x == null) return 0;
        int cnt = 1;
        int left_cnt = countSubTreeNodes(x.left);
        int right_cnt = countSubTreeNodes(x.right);
        cnt += left_cnt + right_cnt;
        return cnt;
    }

    /**
     * check whether a BST is in ordered
     * @param x
     * @param min
     * @param max
     * @return
     */
    public boolean isOrdered(Node x, Key min, Key max) {
        boolean left_flag;
        boolean right_flag;
        if (x == null) return false;
        // iter check left subtree
        if (x.left != null) {
            int cmp = x.key.compareTo(x.left.key);
            if (cmp <= 0) return false;
            left_flag = isOrdered(x.left, min, max);
        } else {
            left_flag = true;
            int cmp = x.key.compareTo(min);
            if (cmp != 0) return false;
        }
        // iter check right subtree
        if (x.right != null) {
            int cmp = x.key.compareTo(x.right.key);
            if (cmp > 0) return false;
            right_flag = isOrdered(x.right, min, max);
        } else {
            right_flag = true;
            int cmp = x.key.compareTo(max);
            if (cmp != 0) return false;
        }
        return left_flag && right_flag;
    }
}
