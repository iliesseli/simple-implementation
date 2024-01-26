/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package org.example;

import java.util.Random;
import org.example.bloomfilter.BloomFilter;
import org.example.bloomfilter.impl.SimpleBloomFilter;
import org.junit.Test;

/**
 * @author iliesse
 * @version TestSimpleBloomFilter.java, v 0.1 2024年01月26日 15:37 iliesse
 */
public class SimpleBloomFilterTest {
    @Test
    public void putTest() {
        BloomFilter<String> bloomFilter = SimpleBloomFilter.create();
        for (int i = 0; i < 100; i++) {
            bloomFilter.put("haishe" + new Random().nextDouble());
        }
    }

    @Test
    public void mightContainTest() {
        BloomFilter<String> bloomFilter = SimpleBloomFilter.create();
        int falseNum = 0;
        for (int i = 0; i < 100; i++) {
            String value = "haishe" + new Random().nextDouble();
            bloomFilter.put(value);
        }
        for (int i = 0; i < 100000; i++) {
            String value = "haihai" + new Random().nextDouble();
            if (bloomFilter.mightContain(value)) {
                falseNum ++;
            }
        }

        System.out.println(falseNum);
    }
}