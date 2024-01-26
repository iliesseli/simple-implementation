/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.bloomfilter;

/**
 * @author iliesse
 * @version MyBloomFilter.java, v 0.1 2024年01月26日 14:29 iliesse
 */
public interface BloomFilter<T> {
    boolean mightContain(T t);
    void put(T t);
    void clear();
}