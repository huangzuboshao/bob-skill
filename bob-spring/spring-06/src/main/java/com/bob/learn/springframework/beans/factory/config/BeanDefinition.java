package com.bob.learn.springframework.beans.factory.config;

import com.bob.learn.springframework.beans.MutablePropertyValues;
import com.bob.learn.springframework.beans.PropertyValues;

/**
 * Bean定义信息
 *
 * @author Bob
 * @date 2022/7/13 15:24
 */
public class BeanDefinition {

    private Class<?> beanClass;

    /**
     * Bean属性集合包装
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues pvs) {
        this.beanClass = beanClass;
        this.propertyValues = pvs != null ? pvs : new MutablePropertyValues();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
