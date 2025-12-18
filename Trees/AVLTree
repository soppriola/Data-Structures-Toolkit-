package com.sophiapriola.dstoolkit.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Generic AVL Tree (self-balancing BST).
 * Balances after insertions using rotations to maintain O(log n) height.
 *
 * @author Sophia Priola
 * @version 07 Nov 2025
 */
public class AVLTree<T> {

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;
        int height; // height of this node (1 for leaf)

        Node(T data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node<T> root;
    private int size;
    private final Comparator<? super T> comparator;

    public AVLTree(Comparator<? super T> comparator) {
        if (comparator == null) throw new IllegalArgumentException("Comparator cannot be null");
        this.comparator = comparator;
    }

    /** Returns the number of elements in the tree */
    public int size() {
        return size;
    }

    /** Returns true if the tree is empty  */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Height of the tree (0 if empty). */
    public int height() {
        return height(root);
    }

    /** Returns true if value exists in the tree. */
    public boolean contains(T value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        Node<T> cur = root;
        while (cur != null) {
            int cmp = comparator.compare(value, cur.data);
            if (cmp == 0) return true;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return false;
    }

    /** Inserts value into the tree. Duplicate values are ignored (no-op). */
    public void insert(T value) {
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        root = insert(root, value);
    }

    /** Returns an in-order traversal (sorted by comparator). */
    public List<T> inOrder() {
        List<T> out = new ArrayList<>();
        inOrder(root, out);
        return out;
    }


    /** Helper method to insert a value into the tree */
    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }

        int cmp = comparator.compare(value, node.data);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        } else {
            // duplicate: do nothing
            return node;
        }

        updateHeight(node);
        return rebalance(node);
    }

    /** Helper method to perform in-order traversal */
    private void inOrder(Node<T> node, List<T> out) {
        if (node == null) return;
        inOrder(node.left, out);
        out.add(node.data);
        inOrder(node.right, out);
    }

    /** Helper method to get the height of a node */
    private int height(Node<T> node) {
        return (node == null) ? 0 : node.height;
    }

    /** Update the height of the tree */
    private void updateHeight(Node<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /** balanceFactor = height(left) - height(right) */
    private int balanceFactor(Node<T> node) {
        return height(node.left) - height(node.right);
    }

    /** Rebalance the tree if necessary  */
    private Node<T> rebalance(Node<T> node) {
        int bf = balanceFactor(node);

        // Left heavy
        if (bf > 1) {
            // LR case: left child is right heavy, rotate left on left child first
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            // LL case
            return rotateRight(node);
        }

        // Right heavy
        if (bf < -1) {
            // RL case: right child is left heavy -> rotate right on right child first
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            // RR case
            return rotateLeft(node);
        }

        return node; // already balanced
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> t2 = x.right;

        // rotation
        x.right = y;
        y.left = t2;

        // update heights (bottom-up)
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> t2 = y.left;

        // rotation
        y.left = x;
        x.right = t2;

        // update heights (bottom-up)
        updateHeight(x);
        updateHeight(y);

        return y;
    }
}
