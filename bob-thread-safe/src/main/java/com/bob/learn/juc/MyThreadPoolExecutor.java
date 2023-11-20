package com.bob.learn.juc;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author huangzuboshao
 * @date 2023/2/17 16:29
 */
@Slf4j(topic = "e")
@Data
public class MyThreadPoolExecutor {
    final Object lock = new Object();

    /**
     * 核心线程
     */
    private final int coreSize;
    /**
     * 最大线程数
     */
    private final int maxSize;
    /**
     * 阻塞超时
     */
    private final long timeOut;
    private final TimeUnit timeUnit;
    /**
     * 阻塞队列
     */
    private final MyBlockingDeque<Runnable> workQueue;
    private ThreadFactory threadFactory;
    private MyRejectPolicyHandler rejectPolicyHandler;
    /**
     * 工作的线程
     */
    private final Set<Worker> workers = new HashSet<>();

    public MyThreadPoolExecutor(int coreSize, int maxSize, long timeOut, TimeUnit timeUnit, MyBlockingDeque<Runnable> workQueue,ThreadFactory threadFactory,MyRejectPolicyHandler rejectPolicyHandler) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
        this.rejectPolicyHandler = rejectPolicyHandler;
    }

    public void execute(Runnable task) {
        synchronized (lock) {
            //如果工作的线程数小于核心线程数
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("{}直接分配给了{}",task,worker.getName());
                workers.add(worker);
                worker.start();
            } else {
                //放到队列中
                workQueue.tryOffer(task,rejectPolicyHandler);
            }
        }
    }

    class Worker extends Thread {
        private Runnable firstTask;

        public Worker(Runnable task) {
            this.firstTask = task;
        }

        @Override
        public void run() {
            while (firstTask != null || (firstTask = workQueue.take(timeOut,timeUnit)) != null) {
                firstTask.run();
                firstTask = null;
            }
        }
    }

    static class Task implements Runnable {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            log.debug("执行{}...",name);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
