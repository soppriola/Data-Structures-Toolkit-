package com.sophiapriola.dstoolkit.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sophia Priola
 * @version 10 Oct 2025
 * 
 * This class implements a generic doubly linked list data structure
 */

public class MyLinkedList<T> implements Iterable<T> {

    /** Node class for the doubly linked list */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /** Creates an empty doubly linked list */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Returns the number of elements in the list */
    public int size() {
        return size;
    }

    /** Returns true if the list is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Adds a new element to the front of the list */
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /** Adds a new element to the end of the list */
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /** Removes element from the front of the list */
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        T data = head.data;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        size--;
        return data;
    }

    /** Removes element from the end of the list */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        T data = tail.data;
        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        size--;
        return data;
    }

    /** Clears the list */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Checks if the list contains a specific value */
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (value == null) {
                if (current.data == null) return true;
            } else {
                if (value.equals(current.data)) return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Returns an iterator over the elements in the list */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T val = current.data;
                current = current.next;
                return val;
            }
        };
    }
}
