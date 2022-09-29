package com.bob.learn.springframework.beans.factory;

import com.bob.learn.springframework.beans.BeansException;
import com.sun.istack.internal.Nullable;

/**
 * @author Bob
 * @date 2022/7/13 17:45
 */
public class NoSuchBeanDefinitionException extends BeansException {

    @Nullable
    private String beanName;

    public NoSuchBeanDefinitionException(String beanName) {
        super("No bean named '" + beanName + "' available");
        this.beanName = beanName;
    }
}
