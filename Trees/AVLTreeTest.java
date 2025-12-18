package com.sophiapriola.dstoolkit.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sophia Priola
 * @version 18 Dec 2025
 * 
 * This test class allows us to run JUnit tests on the AVLTree implementation
 */

class AVLTreeTest {

    @Test
    void newTree_startsEmpty() {
        AVLTree<Integer> tree = new AVLTree<>(Comparator.naturalOrder());
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
        assertEquals(List.of(), tree.inOrder());
    }

    @Test
    void insert_andContains_work() {
        AVLTree<Integer> tree = new AVLTree<>(Comparator.naturalOrder());
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(20));
        assertFalse(tree.contains(999));
    }

    @Test
    void inOrder_returnsSortedOrder() {
        AVLTree<Integer> tree = new AVLTree<>(Comparator.naturalOrder());
        int[] values = { 10, 5, 20, 3, 7, 15, 30 };

        for (int v : values) tree.insert(v);

        assertEquals(Arrays.asList(3, 5, 7, 10, 15, 20, 30), tree.inOrder());
    }

    @Test
    void duplicates_doNotIncreaseSize_orChangeTraversal() {
        AVLTree<Integer> tree = new AVLTree<>(Comparator.naturalOrder());
        tree.insert(10);
        tree.insert(10);
        tree.insert(10);

        assertEquals(1, tree.size());
        assertEquals(List.of(10), tree.inOrder());
        assertTrue(tree.contains(10));
    }

    @Test
    void ascendingInserts_treeStaysBalanced_heightIsSmall() {
        AVLTree<Integer> tree = new AVLTree<>(Comparator.naturalOrder());

        // Worst-case for a normal BST, but AVL should rebalance.
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }

        // For AVL, height grows ~ O(log n). For 100 nodes, height should be well under 20.
        // This threshold is generous so the test is stable.
        assertTrue(tree.height() <= 20, "Height too large: " + tree.height());

        // Also sanity-check ordering and size
        assertEquals(100, tree.size());
        assertEquals(1, tree.inOrder().get(0));
        assertEquals(100, tree.inOrder().get(tree.inOrder().size() - 1));
    }

    @Test
    void customComparator_supported() {
        // Reverse order comparator means inOrder() will return values in descending order
        AVLTree<Integer> tree = new AVLTree<>(Comparator.reverseOrder());
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);

        assertEquals(Arrays.asList(3, 2, 1), tree.inOrder());
    }
}


