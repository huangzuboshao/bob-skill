package com.bob.learn.springframework.test.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Bob
 * @date 2022/8/1 16:34
 */
public class YieldTest {
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increment() {
        race.incrementAndGet();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increment();
                }
            },"thread_"+i);
            threads[i].start();
        }
        System.out.println("threadActive: "+Thread.activeCount());
        while (Thread.activeCount() > 1) {
            System.out.println(Thread.activeCount());
            Thread.yield();
        }

        System.out.println(race);
    }
}