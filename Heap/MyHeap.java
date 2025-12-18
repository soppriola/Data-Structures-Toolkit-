package com.sophiapriola.dstoolkit.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @author Sophia Priola
 * @version 18 Dec 2025
 *
 * This class implements a generic heap data structure using an ArrayList
 * to store the elements. The heap can be configured as a min-heap or a max-heap
 * depending on the Comparator provided.
 */
public class MyHeap<T> {

    private final ArrayList<T> data = new ArrayList<>();
    private final Comparator<? super T> comparator;

    public MyHeap(Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        this.comparator = comparator;
    }

    /** Returns the number of elements in the heap. */
    public int size() {
        return data.size();
    }

    /** Returns true if the heap has no elements. */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /** Returns the root element without removing it. */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return data.get(0);
    }

    /** Adds a new element and maintains the heap property. */
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        data.add(value);
        siftUp(data.size() - 1);
    }

    /** Deletes and returns the root element, maintaining the heap property. */
    public T delete() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T root = data.get(0);
        T last = data.remove(data.size() - 1);

        if (!isEmpty()) {
            data.set(0, last);
            siftDown(0);
        }

        return root;
    }

    /** Sifts the element at index i up to maintain the heap property. */
    private void siftUp(int i) {
        if (i <= 0) return;

        int parentIndex = (i - 1) / 2;

        if (comparator.compare(data.get(i), data.get(parentIndex)) < 0) {
            T temp = data.get(i);
            data.set(i, data.get(parentIndex));
            data.set(parentIndex, temp);

            siftUp(parentIndex);
        }
    }

    /** Sifts the element at index i down to maintain the heap property. */
    private void siftDown(int i) {
        int leftChild, rightChild, swapIndex;

        while (true) {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            swapIndex = i;

            if (leftChild < data.size()
                    && comparator.compare(data.get(leftChild), data.get(swapIndex)) < 0) {
                swapIndex = leftChild;
            }

            if (rightChild < data.size()
                    && comparator.compare(data.get(rightChild), data.get(swapIndex)) < 0) {
                swapIndex = rightChild;
            }

            if (swapIndex == i) {
                break;
            }

            T temp = data.get(i);
            data.set(i, data.get(swapIndex));
            data.set(swapIndex, temp);
            i = swapIndex;
        }
    }
}
