package com.bob.learn.springframework.beans.factory.config;

/**
 * 单例注册接口
 * @author Bob
 * @date 2022/7/13 15:47
 */
public interface SingletonBeanRegistry {

    /**
     * 获取以及实例化完成的 singleton
     * 1）手动 registerSingleton 注册的
     * 2）容器基于 BeanDefinition 实例化完成后注册的
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
