package com.bob.learn.springframework.beans.factory.support;

import com.bob.learn.springframework.beans.BeansException;
import com.bob.learn.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.util.Collections;

/**
 * cglib实例化策略
 * @author Bob
 * @date 2022/7/14 17:27
 */
public class CglibSubclassingInstantiationStrategy extends SimpleInstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallbacks(Collections.singletonList(NoOp.INSTANCE).toArray(new Callback[1]));
        if (ctor == null) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
