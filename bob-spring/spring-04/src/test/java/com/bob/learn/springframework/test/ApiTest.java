package com.bob.learn.springframework.test;

import com.bob.learn.springframework.beans.MutablePropertyValues;
import com.bob.learn.springframework.beans.PropertyValue;
import com.bob.learn.springframework.beans.PropertyValues;
import com.bob.learn.springframework.beans.factory.config.BeanDefinition;
import com.bob.learn.springframework.beans.factory.config.BeanReference;
import com.bob.learn.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.bob.learn.springframework.test.bean.MemberService;
import com.bob.learn.springframework.test.bean.UserDao;
import com.bob.learn.springframework.test.bean.UserService;
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

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 2.注册 bean
        PropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("username", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();


        BeanDefinition memberBeanDefinition = new BeanDefinition(MemberService.class);
        beanFactory.registerBeanDefinition("memberService", memberBeanDefinition);
        // 4.第二次获取 bean from Singleton
        MemberService memberService = (MemberService) beanFactory.getBean("memberService");
        memberService.getMember();
    }

}
