package com.bob.learn.springframework.test.juc.chatmethod;

import cn.hutool.core.util.ClassUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 线程减通信
 *
 * @author Bob
 * @date 2022/8/3 11:11
 */
public class ThreadChatMethodTest {
    private int i;

    public static Unsafe getUnsafe()
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");


        // 第一种方式：通过构造器获取Unsafe实例
        Constructor<Unsafe> declaredConstructor = Unsafe.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance();

/*        // 第二种方法：通过字段获取Unsafe实例
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe)theUnsafe.get(null);*/
    }

    ThreadChatMethodTest(String a) {
    }

    public ThreadChatMethodTest() {
        System.out.println("钩爪");
    }

    public ThreadChatMethodTest(int i) {
        this.i = i;
        System.out.println("钩爪");
    }

    void m1() {
        System.out.println("m1: i->" + i);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        ThreadChatMethodTest o = (ThreadChatMethodTest) getUnsafe().allocateInstance(ThreadChatMethodTest.class);
        Constructor<?>[] constructors = ThreadChatMethodTest.class.getConstructors();
        Constructor<?>[] declaredConstructors = ThreadChatMethodTest.class.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(System.out::println);
        System.out.println("--------------------------");

        Optional<Constructor<?>> any = Arrays.stream(declaredConstructors).filter(t -> t.getParameterTypes().length > 0).filter(t -> ClassUtil.isAssignable(Integer.class, t.getParameterTypes()[0])).findAny();
        ThreadChatMethodTest o1 = (ThreadChatMethodTest)any.get().newInstance(8);
        o1.m1();

    }
}
