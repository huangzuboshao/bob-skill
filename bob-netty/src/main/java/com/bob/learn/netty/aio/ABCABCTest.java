package com.bob.learn.netty.aio;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Bob
 * @date 2022/9/19 17:06
 */
public class ABCABCTest {

    // 以A开始的信号量,初始信号量数量为1
    private static Semaphore A = new Semaphore(0);
    // B、C信号量,A完成后开始,初始信号数量为0
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(1);

    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    private static int state = 0;

    public static int size = 10;

    public static void main(String[] args) {
        semaphoreTest();
        //conditionTest();

    }

    private static void conditionTest() {
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < size; i++) {
                    while (state % 3 == 0) {
                        System.out.println("A");
                        state++;
                    }
                    conditionB.signalAll();
                    conditionA.await();
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < size; i++) {
                    while (state % 3 == 1) {
                        System.out.println("B");
                        state++;
                    }
                    conditionC.signalAll();
                    conditionB.await();
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "B").start();
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < size; i++) {
                    while (state % 3 == 2) {
                        System.out.println("C");
                        state++;
                    }
                    conditionA.signalAll();
                    conditionC.await();
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "C").start();
    }

    private static void semaphoreTest() {
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    A.acquire();// A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    System.out.print("A");
                    B.release();// B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    B.acquire();// A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    System.out.print("B");
                    C.release();// B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    C.acquire();// A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    System.out.print("C");
                    A.release();// B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
