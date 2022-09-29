package com.bob.learn.springframework.beans.factory.support;

import com.bob.learn.springframework.core.io.DefaultResourceLoader;
import com.bob.learn.springframework.core.io.ResourceLoader;

/**
 * 抽象Bean定义读取器
 *
 * @author Bob
 * @date 2022/8/18 9:27
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * Bean定义注册器
     */
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this(beanDefinitionRegistry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
