/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.ratelimiter.impl;

import java.util.Arrays;
import org.example.ratelimiter.RateLimiter;

/**
 * @author iliesse
 * @version SlideWindowRateLimiter.java, v 0.1 2024年01月26日 16:20 iliesse
 */
public class SlideWindowRateLimiter implements RateLimiter {
    // 默认滑动间隔10ms
    private static final int   DEFAULT_SLIDE_INTERVAL_MS = 10;
    private              int[] buckets;
    private              int   head;
    private              int   count;
    private              int   qps;
    private              long  windowStartTime;

    public static RateLimiter create(int qps) {
        SlideWindowRateLimiter rateLimiter = new SlideWindowRateLimiter();
        rateLimiter.qps = qps;
        rateLimiter.windowStartTime = System.currentTimeMillis();
        rateLimiter.buckets = new int[rateLimiter.getBucketsLength()];
        return rateLimiter;
    }

    private int getBucketsLength() {
        return 1000 / DEFAULT_SLIDE_INTERVAL_MS;
    }

    @Override
    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - windowStartTime <= 1000) {
            if (count < qps) {
                count++;
                int bucketOffset = (int) ((now - windowStartTime) / DEFAULT_SLIDE_INTERVAL_MS);
                buckets[(head + bucketOffset) % getBucketsLength()]++;
            } else {
                return false;
            }
        } else if (now - windowStartTime >= 2000) {
            Arrays.fill(buckets, 0);
            windowStartTime = now;
            count = 1;
            head = 0;
            buckets[0] = 1;
        } else {
            // 滑动窗口
            int slideBucks = (int) ((now - 1000 - windowStartTime) / DEFAULT_SLIDE_INTERVAL_MS + 1);
            windowStartTime += (long) slideBucks * DEFAULT_SLIDE_INTERVAL_MS;
            for (int i = 0; i < slideBucks; i++) {
                count -= buckets[(head + i) % getBucketsLength()];
                buckets[(head + i) % getBucketsLength()] = 0;
            }
            head = head + slideBucks;
            if (count < qps) {
                count ++;
                buckets[(head + getBucketsLength() - 1) % getBucketsLength()] ++;
            } else {
                return false;
            }
        }

        return true;
    }
}