package com.bob.learn.springframework.beans.factory.support;

import com.bob.learn.springframework.beans.BeansException;
import com.bob.learn.springframework.beans.factory.BeanFactory;
import com.bob.learn.springframework.beans.factory.NoSuchBeanDefinitionException;
import com.bob.learn.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象Bean工厂
 *
 * @author Bob
 * @date 2022/7/13 15:58
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    /**
     * 返回给定 bean 名称的 bean 定义。子类通常应该实现缓存，因为每次需要 bean 定义元数据时，此类都会调用此方法。
     * @param beanName bean的名称
     * @return 此原型名称的 BeanDefinition（从不为null）
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    /**
     * 为给定的合并 bean 定义（和参数）创建一个 bean 实例。如果是子定义，bean 定义将已经与父定义合并。
     * 所有 bean 检索方法都委托给此方法以进行实际的 bean 创建。
     * @param beanName bean的名称
     * @param beanDefinition bean定义
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
