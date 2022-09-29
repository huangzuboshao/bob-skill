package com.bob.learn.springframework.test.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author Bob
 * @date 2022/8/1 11:04
 */
public class TestMarkedReference {
    public static void main(String[] args) throws InterruptedException {
        //markableReference();
        a();
        Thread.yield();


    }

    private static void a() {
        AtomicLong atomicLong = new AtomicLong();
        System.out.println("getAndIncrement: "+atomicLong.getAndIncrement());
        System.out.println("get: "+atomicLong.longValue());
        System.out.println("incrementAndGet: "+atomicLong.incrementAndGet());
    }

    private static void markableReference() throws InterruptedException {
        AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<>("abc", false);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            atomicMarkableReference.compareAndSet("abc", "bcd", false, true);
            countDownLatch.countDown();
        }, "a").start();

        new Thread(() -> {
            atomicMarkableReference.compareAndSet("bcd", "abc", true, false);
            countDownLatch.countDown();
        }, "b").start();
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"atomicMarkableReference:  "+atomicMarkableReference.isMarked());
        System.out.println(Thread.currentThread().getName()+"atomicMarkableReference:  "+atomicMarkableReference.get(new boolean[2]));
    }
}
