/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example;

import org.example.priorityqueue.SimplePriorityQueue;
import org.junit.Test;

/**
 * @author iliesse
 * @version PriorityQueueTest.java, v 0.1 2024年02月27日 19:30 iliesse
 */
public class PriorityQueueTest {
    @Test
    public void testAdd() {
        SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>(3);
        priorityQueue.add(1);
        assert priorityQueue.size() == 1;
        priorityQueue.add(2);
        assert priorityQueue.size() == 2;
        priorityQueue.add(3);
        assert priorityQueue.size() == 3;
    }

    @Test
    public void testPop() {
        SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>(10);
        priorityQueue.add(100);
        priorityQueue.add(1);
        priorityQueue.add(5);
        priorityQueue.add((-9));
        assert priorityQueue.pop() == -9;
        assert priorityQueue.size() == 3;
        assert priorityQueue.pop() == 1;
        assert priorityQueue.size() == 2;
        assert priorityQueue.pop() == 5;
        assert priorityQueue.size() == 1;
        assert priorityQueue.pop() == 100;
        assert priorityQueue.size() == 0;
    }
}