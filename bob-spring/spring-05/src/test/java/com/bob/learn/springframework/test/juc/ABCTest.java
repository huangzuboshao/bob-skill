package com.bob.learn.springframework.test.juc;

import java.util.concurrent.*;

/**
 * @author Bob
 * @date 2022/8/1 16:56
 */
public class ABCTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int POOL_SIZE = 3;
        //ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(POOL_SIZE, new NamedThreadFactory("owner-thread-pool-", false));
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("running。。。");
        }, 1, 1, TimeUnit.SECONDS);
        Future<?> aaa = scheduledExecutorService.submit(() -> {
            System.out.println("aaa");

        });
        System.out.println(aaa.get());
   /*     for (int i = 0; i < 6; i++) {
            Future<?> fu = executor.submit(() -> {
                System.out.println(Thread.currentThread().getName()+"000");
                return "123";
            });
            System.out.println(fu.get());
        }*/
        scheduledExecutorService.shutdown();
    }
}
