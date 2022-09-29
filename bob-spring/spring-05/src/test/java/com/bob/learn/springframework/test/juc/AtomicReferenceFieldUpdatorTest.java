package com.bob.learn.springframework.test.juc;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author Bob
 * @date 2022/8/2 14:39
 */
class Resource{
    private volatile String abc = "abc";
    AtomicReferenceFieldUpdater<Resource, String> atomicReferenceFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(Resource.class, String.class, "abc");

    public void init() {
        if (atomicReferenceFieldUpdater.compareAndSet(this, "abc", "xyz")) {
            System.out.println(Thread.currentThread().getName() + "init");
        }else {
            System.out.println(Thread.currentThread().getName() + "已经有线程init了");
        }
    }
}

public class AtomicReferenceFieldUpdatorTest {

    public static void main(String[] args) {
        Resource resource = new Resource();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                resource.init();
            }, "thread-"+i).start();
        }
    }
}
