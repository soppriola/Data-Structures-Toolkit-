package com.sophiapriola.dstoolkit.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyBSTTest {

    @Test
    void newTree_startsEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
        assertEquals(List.of(), tree.inOrder());
    }

    @Test
    void insert_andContains_work() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
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
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        int[] values = {10, 5, 20, 3, 7, 15, 30};

        for (int v : values) tree.insert(v);

        assertEquals(Arrays.asList(3, 5, 7, 10, 15, 20, 30), tree.inOrder());
    }

    @Test
    void duplicates_areIgnored_sizeDoesNotIncrease() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        tree.insert(10);
        tree.insert(10);
        tree.insert(10);

        assertEquals(1, tree.size());
        assertEquals(List.of(10), tree.inOrder());
    }

    @Test
    void min_andMax_work() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(3);
        tree.insert(7);

        assertEquals(3, tree.min());
        assertEquals(20, tree.max());
    }

    @Test
    void min_onEmpty_throws() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, tree::min);
    }

    @Test
    void max_onEmpty_throws() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, tree::max);
    }

    @Test
    void height_matchesExpectedForSimpleShape() {
        // Insert in an order that forms a small balanced-ish tree
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);

        // Height should be 3: 10 -> 5 -> 3 (or 7)
        assertEquals(3, tree.height());
    }

    @Test
    void customComparator_supported() {
        // Reverse comparator means "inOrder" will be descending according to that comparator
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Comparator.reverseOrder());
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);

        assertEquals(Arrays.asList(3, 2, 1), tree.inOrder());
        assertTrue(tree.contains(2));
    }
}
