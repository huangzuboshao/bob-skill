package com.bob.learn.springframework.test.juc;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Bob
 * @date 2022/7/29 11:27
 */
public class Task1 implements Runnable{

    private final AtomicLong counter;

    public Task1(AtomicLong counter) {
        this.counter = counter;
    }

    /**
     * 每个线程执行N次+1操作
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+counter.longValue());
        for (int i = 0; i < 10000; i++) {
            counter.incrementAndGet();
        }
    }
}
