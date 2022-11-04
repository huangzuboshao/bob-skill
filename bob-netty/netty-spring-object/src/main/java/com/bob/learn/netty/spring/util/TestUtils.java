package com.bob.learn.netty.spring.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author bob
 * @date 2022/10/21
 */
@Component
public class TestUtils {

    private static String staticKey;

    @Value("${test.staticKey}")
    public void setStaticKey(String staticKey) {
        TestUtils.staticKey = staticKey;
    }

    public  void test() {
        System.out.println("测试静态注入："+staticKey);
    }

}
