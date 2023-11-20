package com.bob.learn.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author huangzuboshao
 * @date 2023/2/17 22:23
 */
@Slf4j
public class TestPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThreadPoolExecutor threadPool = new MyThreadPoolExecutor(1, 5, 2, TimeUnit.SECONDS, new MyBlockingDeque<>(2), null,
                (task, queue) -> {
                    Runnable t = (Runnable) task;
                    queue.put(t);

                });
        for (int i = 1; i < 6; i++) {
            int k = i;
            threadPool.execute(new MyThreadPoolExecutor.Task("xxx" + k));
        }
        CountDownLatch count = new CountDownLatch(10);
        count.await();
/*        Callable<String> c = () -> {
            //while (!Thread.currentThread().isInterrupted()) {
            while (!Thread.interrupted() && Thread.currentThread().isInterrupted()) {
                System.out.println("callable");
            }
            return "return callable";
        };
        FutureTask<String> f = new FutureTask<>(c);
        Thread thread = new Thread(f);
        thread.start();
        try {
            Thread. sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            System.out.println(f.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    */
        mainSubThreadTest();

    }

    private static void mainSubThreadTest() {
        Thread mainThread = Thread.currentThread();
        Runnable r = () -> {
            for (int i = 0; i < 500; i++) {
                System.out.println("子线程运行：" + i);
                if (i == 400) {
                    try {
                        Thread.sleep(3000);
                        System.err.println(mainThread.getName() + "运行状态:" + mainThread.getState());

                    } catch (Exception e) {

                    }

                }
            }
        };
        Thread subThread = new Thread(r, "子线程");
        System.err.println(subThread.getName() + "运行状态:" + subThread.getState());

        subThread.start();
        System.err.println(subThread.getName() + "运行状态:" + subThread.getState());

        while (true) {
            try {
                //Thread.sleep(10);
                Thread.State state = subThread.getState();
                System.err.println(subThread.getName() + "运行状态:" + subThread.getState());
                if (state == Thread.State.TIMED_WAITING) {
                    subThread.join();
                }
                if (state == Thread.State.TERMINATED) {
                    break;
                }
            } catch (Exception e) {

            }
        }
    }
}
