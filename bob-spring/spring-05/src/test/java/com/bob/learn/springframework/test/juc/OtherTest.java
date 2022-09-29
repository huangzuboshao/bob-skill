package com.bob.learn.springframework.test.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Bob
 * @date 2022/7/29 10:47
 */
public class OtherTest {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        CompletableFuture.runAsync(()->{
            longAdder.add(4);
        });
        CompletableFuture.runAsync(()-> longAdder.add(3));
        longAdder.add(3);
        System.out.println(longAdder.intValue());
    }
}
