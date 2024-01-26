/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.ratelimiter.impl;

import org.example.ratelimiter.RateLimiter;

/**
 * @author iliesse
 * @version FixWindowRateLimiter.java, v 0.1 2024年01月26日 16:00 iliesse
 */
public class FixWindowRateLimiter implements RateLimiter {
    private int qps;
    private int count;
    private long lastAcquireTime;
    public static RateLimiter create(int qps) {
        FixWindowRateLimiter rateLimiter = new FixWindowRateLimiter();
        rateLimiter.qps = qps;
        rateLimiter.lastAcquireTime = System.currentTimeMillis();
        return rateLimiter;
    }

    @Override
    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - lastAcquireTime < 1000) {
            if (count < qps) {
                count ++;
            } else {
                return false;
            }
        } else {
            count = 1;
            lastAcquireTime = now;
        }

        return true;
    }
}