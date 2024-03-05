/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import org.example.multithreading.PrintOddEvenNumbers;
import org.junit.Test;

/**
 * @author iliesse
 * @version MultiThreadingTest.java, v 0.1 2024年02月29日 13:43 iliesse
 */
public class MultiThreadingTest {
    @Test
    public void testPrintOddEvenNumber() throws InterruptedException {
        PrintOddEvenNumbers printOddEvenNumbers = new PrintOddEvenNumbers();
        new Thread(() -> {
            try {
                printOddEvenNumbers.printNumbers();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                printOddEvenNumbers.printNumbers();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        Thread.sleep(20000);
    }

    @Test
    public void test() throws InterruptedException {
        Thread
    }
}