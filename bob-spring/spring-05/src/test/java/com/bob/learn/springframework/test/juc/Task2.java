package com.bob.learn.springframework.test.juc;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Bob
 * @date 2022/7/29 11:27
 */
public class Task2 implements Runnable{

    private final LongAdder counter;

    public Task2(LongAdder counter) {
        this.counter = counter;
    }

    /**
     * 每个线程执行N次+1操作
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+counter.longValue());
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
