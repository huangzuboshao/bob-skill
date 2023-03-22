package com.bob.learn.netty.spring.test;

import com.bob.learn.netty.spring.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author bob
 * @date 2022/10/21
 */
@SpringBootTest(classes = UnitTest.class)
@RunWith(SpringRunner.class)
@ComponentScans(@ComponentScan("com.bob.learn.netty.spring.util"))
//@SpringBootApplication(scanBasePackages = "com.bob.learn.netty.spring.util")
public class UnitTest {
    volatile long a = 40964096L;

    @Autowired
    private TestUtils testUtils;

    @Test
    public void test01() {
        //TestUtils.test();
        testUtils.test();
        //System.out.println("123");
    }

    public static void main(String[] args) {
    }
}
