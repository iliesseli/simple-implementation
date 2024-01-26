/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.ratelimiter;

/**
 * @author iliesse
 * @version RateLimiter.java, v 0.1 2024年01月26日 15:50 iliesse
 */
public interface RateLimiter {
    boolean tryAcquire();
}