package com.sophiapriola.dstoolkit.list;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Sophia Priola
 * @version 18 Dec 2025
 * 
 * Testing class for MyLinkedList data structure
 * This will demonstrate the functionality of the linked list implementation
 */
class MyLinkedListTest {

    @Test
    void newList_startsEmpty() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void addFirst_onEmpty_setsHeadAndTail() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        // removeFirst should return the same element
        assertEquals(10, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void addLast_onEmpty_setsHeadAndTail() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(20);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        // removeLast should return the same element
        assertEquals(20, list.removeLast());
        assertTrue(list.isEmpty());
    }

    @Test
    void addFirst_thenRemoveFirst_returnsInCorrectOrder() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3); // list is [3,2,1]

        assertEquals(3, list.removeFirst());
        assertEquals(2, list.removeFirst());
        assertEquals(1, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void addLast_thenRemoveFirst_returnsInCorrectOrder() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3); // list is [1,2,3]

        assertEquals(1, list.removeFirst());
        assertEquals(2, list.removeFirst());
        assertEquals(3, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void addFirst_andAddLast_mixWorks() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(2);   // [2]
        list.addFirst(1);  // [1,2]
        list.addLast(3);   // [1,2,3]

        assertEquals(1, list.removeFirst());
        assertEquals(3, list.removeLast());
        assertEquals(2, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void removeFirst_onEmpty_throws() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    void removeLast_onEmpty_throws() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    void clear_emptiesList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    void contains_findsValues_andHandlesMissing() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");

        assertTrue(list.contains("a"));
        assertTrue(list.contains("b"));
        assertTrue(list.contains("c"));
        assertFalse(list.contains("z"));
    }

    @Test
    void contains_handlesNull() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addLast(null);
        list.addLast("x");

        assertTrue(list.contains(null));
        assertTrue(list.contains("x"));
        assertFalse(list.contains("y"));
    }

    @Test
    void iterator_iteratesInOrder() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void iterator_nextPastEnd_throws() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);

        Iterator<Integer> it = list.iterator();
        assertEquals(1, it.next());
        assertThrows(NoSuchElementException.class, it::next);
    }
}
