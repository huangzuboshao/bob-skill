package com.bob.learn.springframework.test.juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Bob
 * @date 2022/8/1 9:07
 */
public class CasLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
            System.out.println(Thread.currentThread().getName() + "尝试中");
        }
        System.out.println(Thread.currentThread().getName() + "获取锁成功");
    }

    public void unlock() {
        atomicReference.compareAndSet( Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName() + "解锁");
    }
}
