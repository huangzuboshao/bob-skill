package com.bob.learn.springframework.context.support;

import com.bob.learn.springframework.beans.BeansException;
import com.bob.learn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.bob.learn.springframework.context.ConfigurableApplicationContext;
import com.bob.learn.springframework.core.io.DefaultResourceLoader;

/**
 * 抽象的ApplicationContext
 * <p>
 * 继承 DefaultResourceLoader 是为了处理 spring.xml 配置资源的加载
 *
 * @author Bob
 * @date 2022/8/25 15:12
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {

        // 1.告诉子类刷新内部 bean 工厂
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

        // 2.在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);
    }

    /**
     * 调用在上下文中注册为 bean 的工厂处理器
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
    }

    protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        System.out.println("BeanFactory name for" + beanFactory);
        return beanFactory;
    }

    /**
     * d
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException ;

    /**
     * 获取BeanFactory
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();
}
