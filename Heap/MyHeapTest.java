package com.sophiapriola.dstoolkit.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapTest {

    @Test
    void constructor_nullComparator_throws() {
        assertThrows(IllegalArgumentException.class, () -> new MyHeap<Integer>(null));
    }

    @Test
    void newHeap_startsEmpty() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void add_increasesSize_andPeekShowsBest_minHeap() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());

        heap.add(5);
        assertEquals(1, heap.size());
        assertEquals(5, heap.peek());

        heap.add(2);
        assertEquals(2, heap.size());
        assertEquals(2, heap.peek()); // min-heap => smallest at root

        heap.add(8);
        assertEquals(3, heap.size());
        assertEquals(2, heap.peek());
    }

    @Test
    void add_nullValue_throws() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());
        assertThrows(IllegalArgumentException.class, () -> heap.add(null));
    }

    @Test
    void peek_emptyHeap_throws() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void delete_emptyHeap_throws() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::delete);
    }

    @Test
    void delete_removesRootAndReturnsInSortedOrder_minHeap() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());
        heap.add(4);
        heap.add(1);
        heap.add(3);
        heap.add(2);

        assertEquals(1, heap.delete());
        assertEquals(2, heap.delete());
        assertEquals(3, heap.delete());
        assertEquals(4, heap.delete());

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void delete_singleElement_becomesEmpty() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());
        heap.add(42);

        assertEquals(42, heap.delete());
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void maxHeap_usingReverseComparator() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.reverseOrder());
        heap.add(1);
        heap.add(10);
        heap.add(3);

        assertEquals(10, heap.peek());
        assertEquals(10, heap.delete());
        assertEquals(3, heap.delete());
        assertEquals(1, heap.delete());
        assertTrue(heap.isEmpty());
    }

    @Test
    void handlesDuplicates_correctly() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());
        heap.add(2);
        heap.add(2);
        heap.add(1);
        heap.add(1);

        assertEquals(1, heap.delete());
        assertEquals(1, heap.delete());
        assertEquals(2, heap.delete());
        assertEquals(2, heap.delete());
        assertTrue(heap.isEmpty());
    }

    @Test
    void mixedOperations_stillMaintainsOrder_minHeap() {
        MyHeap<Integer> heap = new MyHeap<>(Comparator.naturalOrder());

        heap.add(5);
        heap.add(2);
        heap.add(8);

        assertEquals(2, heap.delete()); // remove smallest
        heap.add(1);
        heap.add(3);

        assertEquals(1, heap.delete());
        assertEquals(3, heap.delete());
        assertEquals(5, heap.delete());
        assertEquals(8, heap.delete());
        assertTrue(heap.isEmpty());
    }
}
