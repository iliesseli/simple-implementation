/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.multithreading;

import java.util.HashMap;

/**
 * @author iliesse
 * @version PrintNumbers.java, v 0.1 2024年02月28日 17:42 iliesse
 */
public class PrintOddEvenNumbers {
    private int  number    = 0;
    private int  maxNumber = 100;

    public void printNumbers() throws InterruptedException {
        synchronized (this) {
            while (number < maxNumber) {
                System.out.println(Thread.currentThread().getId() + ": " + number);
                number++;
                Thread.sleep(1000);
                this.notify();
                this.wait();
            }
        }
    }
}