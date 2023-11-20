package com.bob.learn.juc;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 *
 * @author huangzuboshao
 * @date 2023/2/17 16:32
 */
@Slf4j(topic = "e")
public class MyBlockingDeque<T> {

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private int count;

    private Deque<T> deque = new ArrayDeque<>();


    public MyBlockingDeque(int count) {
        this.count = count;
    }

    /**
     *
     * @param task
     */
    public void put(T task) {
        lock.lock();
        try {
            while (count == deque.size()) {
                log.debug("[put][{}]阻塞..",task);
                notFull.await();
            }
            log.debug("[{}]添加到队列中",task);
            deque.addLast(task);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void tryOffer(T task,MyRejectPolicyHandler rejectPolicyHandler) {
        lock.lock();
        try {
            if (count == deque.size()) {
                log.debug("[tryOffer][{}]队列已满,执行拒绝策略..",task);
                rejectPolicyHandler.reject(task,this);
            }else{
                log.debug("[{}]添加到队列中",task);
                deque.addLast(task);
                notEmpty.signal();
            }
        } finally {
            lock.unlock();
        }

    }

    /**
     * 从队列中获取一个任务
     * 线程 阻塞获取元素
     * take 如果获取不到元素队列size为0 --阻塞
     * 如果获取不到一直阻塞
     * @return
     */
    public T take() {
        lock.lock();
        try {

            //判断队列是否为空
            while (deque.isEmpty()) {
                log.debug("【take】队列为空，阻塞..");
                notEmpty.await();
            }
            //从队列中获取并删除
            T t = deque.removeFirst();
            //唤醒一个put的线程
            notFull.signal();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public T take(long timeOut, TimeUnit timeUnit) {
        long l = timeUnit.toNanos(timeOut);
        lock.lock();
        try {
            //判断队列是否为空
            while (deque.isEmpty()) {
                log.debug("【take】队列为空，阻塞---超时");
                if (l < 0) {
                    log.debug("【take】队列为空，阻塞---超时结束");
                    return null;
                }
                l = notEmpty.awaitNanos(l);
            }
            //从队列中获取并删除
            T t = deque.removeFirst();
            //唤醒一个put的线程
            notFull.signal();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

}
