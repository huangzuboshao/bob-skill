package com.bob.learn.springframework.beans.factory.config;

/**
 * Bean引用
 * @author Bob
 * @date 2022/7/14 18:25
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
