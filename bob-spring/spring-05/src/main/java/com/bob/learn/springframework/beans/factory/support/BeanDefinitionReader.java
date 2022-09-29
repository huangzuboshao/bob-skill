package com.bob.learn.springframework.beans.factory.support;

import com.bob.learn.springframework.beans.BeansException;
import com.bob.learn.springframework.core.io.Resource;
import com.bob.learn.springframework.core.io.ResourceLoader;

/**
 * @author Bob
 * @date 2022/8/17 18:11
 */
public interface BeanDefinitionReader {

    /**
     * 获取Bean定义注册器
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载Bean定义
     * @param resource 资源
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 加载Bean定义
     * @param resources 多资源
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 加载Bean定义
     * @param location 资源地址
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;
}
