package com.bob.learn.springframework.test;

import com.bob.learn.springframework.beans.factory.support.BeanDefinitionReader;
import com.bob.learn.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.bob.learn.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.bob.learn.springframework.test.bean.MemberService;
import com.bob.learn.springframework.test.bean.UserService1;
import org.junit.Test;

/**
 * @author bob
 */
public class ApiTest {

    /**
     * <ul>
     *      *<li>实现一个容器</li>
     *      *<li>定义和注册Bean</li>
     *      *<li>实例化Bean </li>
     *      *<li>按照是否包含构造函数实现不同的实例化策略</li>
     * *</ul>
     * 属性填充要在类实例化创建之后，也就是需要在 AbstractAutowireCapableBeanFactory 的 createBean 方法中添加 applyPropertyValues 操作。
     * 由于我们需要在创建Bean时候填充属性操作，那么就需要在 bean 定义 BeanDefinition 类中，添加 PropertyValues 信息。
     * 另外是填充属性信息还包括了 Bean 的对象类型，也就是需要再定义一个 BeanReference，里面其实就是一个简单的 Bean 名称，在具体的实例化操作时进行递归创建和填充，与 Spring 源码实现一样。Spring 源码中 BeanReference 是一个接口
     */
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("spring.xml");

        // 第二次获取 bean from Singleton
        MemberService memberService = (MemberService) beanFactory.getBean("memberService");
        UserService1 userService = (UserService1) beanFactory.getBean("userService");
        memberService.getMember();
        userService.queryUserInfo();
    }

}
