package com.bob.learn.springframework.beans;

/**
 * Bean定义信息
 *
 * @author Bob
 * @date 2022/7/13 15:24
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
