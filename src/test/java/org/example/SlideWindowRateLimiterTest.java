/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example;

import org.example.ratelimiter.RateLimiter;
import org.example.ratelimiter.impl.SlideWindowRateLimiter;
import org.junit.Test;

/**
 * @author iliesse
 * @version SlideWindowRateLimiterTest.java, v 0.1 2024年01月26日 17:11 iliesse
 */
public class SlideWindowRateLimiterTest {
    @Test
    public void acquireTest() throws InterruptedException {
        RateLimiter rateLimiter = SlideWindowRateLimiter.create(100);
        // 前半段sleep
        Thread.sleep(500);
        for (int i = 0; i < 100; i++) {
            assert rateLimiter.tryAcquire();
        }
        Thread.sleep(500);
        for (int i = 0; i < 1000; i++) {
            assert  !rateLimiter.tryAcquire();
        }
        Thread.sleep(500);
        for (int i = 0; i < 100; i++) {
            assert rateLimiter.tryAcquire();
        }
    }
}