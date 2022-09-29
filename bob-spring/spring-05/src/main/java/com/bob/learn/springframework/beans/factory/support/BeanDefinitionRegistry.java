package com.bob.learn.springframework.beans.factory.support;

import com.bob.learn.springframework.beans.factory.NoSuchBeanDefinitionException;
import com.bob.learn.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Bob
 * @date 2022/7/13 16:28
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 返回给定 bean 名称的 BeanDefinition。
     * @param beanName – 要为其查找定义的 bean 的名称
     * @return 给定名称的 BeanDefinition（从不为null ）
     * @throws NoSuchBeanDefinitionException – 如果没有这样的 bean 定义
     */
    BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    /**
     * 是否包含BeanDefinition
     * @param beanName bean名称
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
