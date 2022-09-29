package com.bob.learn.springframework.test;

import com.bob.learn.springframework.beans.factory.config.BeanDefinition;
import com.bob.learn.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.bob.learn.springframework.test.bean.MemberService;
import com.bob.learn.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author bob
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService","liao");
        userService.queryUserInfo();

        BeanDefinition memberBeanDefinition = new BeanDefinition(MemberService.class);
        beanFactory.registerBeanDefinition("memberService", memberBeanDefinition);
        // 4.第二次获取 bean from Singleton
        MemberService memberService = (MemberService) beanFactory.getBean("memberService");
        memberService.getMember();
    }

}
