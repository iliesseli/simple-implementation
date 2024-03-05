/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.priorityqueue;

import java.util.Arrays;

/**
 * @author iliesse
 * @version SimplePriorityQueue.java, v 0.1 2024年02月27日 19:13 iliesse
 */
public class SimplePriorityQueue <E extends Comparable<E>>  {
    private E[] elements;
    private int cap;
    private int size;
    public SimplePriorityQueue(int cap) {
        elements = (E[]) new Comparable[cap + 1];
        this.cap = cap;
    }

    public int size() {
        return size;
    }

    public void add(E e) {
        size ++;
        if (size > cap) {
            throw new RuntimeException("size exceed capacity");
        }
        elements[size] = e;
        swim(size);
    }

    public E pop() {
        if (size < 0) {
            return null;
        }
        swap(1, size);
        size --;
        sink(1);
        E res = elements[size + 1];
        elements[size + 1] = null;
        return res;
    }

    private void swim(int idx) {
        while (idx > 1 && less(idx, parentIdx(idx))) {
            swap(idx , parentIdx(idx));
            idx = parentIdx(idx);
        }
    }

    private void sink(int idx) {
        while (leftIdx(idx) < size) {
            int minIdx = leftIdx(idx);
            if (rightIdx(idx) <= size && less(rightIdx(idx), minIdx)) {
                minIdx = rightIdx(idx);
            }

            if (less(idx, minIdx)) {
                return;
            }

            swap(idx,  minIdx);
            idx = minIdx;
        }

    }

    private void swap(int i , int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private int leftIdx (int idx) {
        return 2 * idx;
    }

    private int rightIdx (int idx) {
        return 2 * idx + 1;
    }

    private int parentIdx(int idx) {
        return idx / 2;
    }

    private boolean less(int i, int j) {
        return elements[i].compareTo(elements[j]) < 0;
    }
}