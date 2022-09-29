package com.bob.learn.springframework.test.juc;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Bob
 * @date 2022/8/5 10:37
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        //设置参数 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0,关闭延时
        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"bl").start();
    }
}
