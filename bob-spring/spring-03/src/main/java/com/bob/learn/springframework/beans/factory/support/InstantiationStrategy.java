package com.bob.learn.springframework.beans.factory.support;

import com.bob.learn.springframework.beans.BeansException;
import com.bob.learn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略
 *
 * @author Bob
 * @date 2022/7/14 16:21
 */
public interface InstantiationStrategy {
    /**
     * @param beanDefinition
     * @param beanName
     * @param ctor
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) throws BeansException;
}
