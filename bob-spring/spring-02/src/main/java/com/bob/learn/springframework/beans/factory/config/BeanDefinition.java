package com.bob.learn.springframework.beans.factory.config;

/**
 * Bean定义信息
 *
 * @author Bob
 * @date 2022/7/13 15:24
 */
public class BeanDefinition {

    private Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
