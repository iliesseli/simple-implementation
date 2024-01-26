/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example.bloomfilter.impl;

import org.example.bloomfilter.BloomFilter;

/**
 * @author iliesse
 * @version SimpleBloomFilter.java, v 0.1 2024年01月26日 14:30 iliesse
 */
public class SimpleBloomFilter<T> implements BloomFilter<T> {
    private static final int DEFAULT_BIT_LENGTH = 1024;
    private static final int DEFAULT_HASH_FUNCTIONS_NUM = 3;
    private  byte[] array;
    private int hashFunctionsNum;

    public static <T> BloomFilter<T> create() {
        SimpleBloomFilter<T> bloomFilter = new SimpleBloomFilter<>();
        bloomFilter.array = new byte[DEFAULT_BIT_LENGTH];
        bloomFilter.hashFunctionsNum = DEFAULT_HASH_FUNCTIONS_NUM;
        return bloomFilter;
    }

    @Override
    public boolean mightContain(T t) {
        int hash1 = t.hashCode();
        int hash2 = hash1 >> 16;
        for (int i = 0; i < hashFunctionsNum; i++) {
            int combineHash = hash1 + (i * hash2);
            if (combineHash < 0) {
                combineHash = ~combineHash;
            }
            if (!isBitSet(combineHash % getBitLength())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void put(T t) {
        int hash1 = t.hashCode();
        int hash2 = hash1 >> 16;
        for (int i = 0; i < hashFunctionsNum; i++) {
            int combineHash = hash1 + (i * hash2);
            if (combineHash < 0) {
                combineHash = ~combineHash;
            }
            setBit(combineHash % getBitLength());
        }
    }

    @Override
    public void clear() {
        array = new byte[DEFAULT_BIT_LENGTH];
    }

    private int getBitLength() {
        return array.length * 8;
    }

    private boolean isBitSet(int index) {
        int arrayIndex = index / 8;
        int bitIndex = index % 8;
        return (array[arrayIndex] & (1 << bitIndex)) != 0;
    }

    private void setBit(int index) {
        int arrayIndex = index / 8;
        int bitIndex = index % 8;
        array[arrayIndex] |=  (1 << bitIndex);
    }
}