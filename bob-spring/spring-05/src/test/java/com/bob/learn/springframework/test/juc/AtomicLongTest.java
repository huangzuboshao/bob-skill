package com.bob.learn.springframework.test.juc;

import cn.hutool.core.thread.NamedThreadFactory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Bob
 * @date 2022/7/29 11:05
 */
public class AtomicLongTest {

    /**
     * 线程池内线程数
     */
    final static int POOL_SIZE = 1000;

    public static void main(String[] args) throws InterruptedException {
        atomicLongTest();
    }

    private static void atomicLongTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicLong counter = new AtomicLong(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),new NamedThreadFactory("owner-thread-pool-",false));

        List<Future<?>> futures = new ArrayList<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE * 100; i++) {
            futures.add(executor.submit(new Task1(counter)));
        }

        // 等待所有线程执行完
        for (Future<?> future : futures) {
            try {
                future.get();
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        NumberFormat numberFormat = NumberFormat.getInstance();
        System.out.printf("AtomicLong 统计结果为：[%s]\n", numberFormat.format(counter.get()));
        System.out.printf("AtomicLong 耗时：[%d]毫秒", (System.currentTimeMillis() - start));
        // 关闭线程池
        executor.shutdown();
    }
}
