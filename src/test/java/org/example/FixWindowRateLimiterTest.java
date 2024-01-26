/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example;

import org.example.ratelimiter.impl.FixWindowRateLimiter;
import org.example.ratelimiter.RateLimiter;
import org.junit.Test;

/**
 * @author iliesse
 * @version FixWindowRateLimiterTest.java, v 0.1 2024年01月26日 16:14 iliesse
 */
public class FixWindowRateLimiterTest {
    @Test
    public void acquireTest() throws InterruptedException {
        RateLimiter rateLimiter = FixWindowRateLimiter.create(100);
        for (int i = 0; i < 200; i++) {
            if (i < 100) {
                assert rateLimiter.tryAcquire();
            } else {
                assert !rateLimiter.tryAcquire();
            }
        }
        Thread.sleep(1000);
        assert rateLimiter.tryAcquire();
    }

    /**
     * 流量突增模拟，滚动窗口存在问题
     * @throws InterruptedException
     */
    @Test
    public void acquireTest2() throws InterruptedException {
        RateLimiter rateLimiter = FixWindowRateLimiter.create(100);
        // 前半段sleep
        Thread.sleep(500);
        for (int i = 0; i < 100; i++) {
            assert rateLimiter.tryAcquire();
        }
        Thread.sleep(500);
        for (int i = 0; i < 100; i++) {
            assert rateLimiter.tryAcquire();
        }
    }
}